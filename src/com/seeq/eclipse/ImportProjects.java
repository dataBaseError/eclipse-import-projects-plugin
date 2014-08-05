package com.seeq.eclipse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.app.IApplicationContext;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class ImportProjects implements org.eclipse.ui.IStartup {
	
	private static boolean exitOnFinish = false;
	
	private String getImportPath() {
		BundleContext context = Activator.getContext();
        ServiceReference<?> ser = context.getServiceReference(IApplicationContext.class);
        IApplicationContext iac = (IApplicationContext)context.getService(ser);
        String[] args = (String[]) iac.getArguments().get(IApplicationContext.APPLICATION_ARGS);
        String importPath = null;
        for (int i = 0; i < args.length; i++) {
        	String arg = args[i];
        	if (arg.compareToIgnoreCase("-import") == 0) {
        		i++;
        		if (i < args.length) {
	        		importPath = args[i];
	        		//break;
        		}
        	}
        	else if(arg.compareToIgnoreCase("-exit_on_finish") == 0) {
        		exitOnFinish = true;
        	}
        }
        
        return importPath;
	}

    private List<File> findFilesRecursively(String path, String pattern) {
    	List<File> returnedList = new ArrayList<File>();
    	return this.findFilesRecursively(path, pattern, returnedList);
    }

    private List<File> findFilesRecursively(String path, String pattern, List<File> returnedList) {
        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null)
        	return returnedList;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                this.findFilesRecursively(f.getAbsolutePath(), pattern, returnedList);
            }
            else {
            	if (Pattern.matches(pattern, f.getName()) == true) {
            		returnedList.add(f);
            	}
            }
        }
        
        return returnedList;
    }

	@Override
	public void earlyStartup() {
		
		String importPath = this.getImportPath();
		
		if (importPath != null) {
			//System.out.println(String.format("Searching for projects in %s", importPath));
			List<File> projectFiles = this.findFilesRecursively(importPath, "\\.project");
			
			IWorkspace workspace = ResourcesPlugin.getWorkspace();

			ImportMonitor monitor = new ImportMonitor(exitOnFinish);
			for (File projectFile : projectFiles) {
				IProjectDescription description = null;
				try {
					
					description = workspace.loadProjectDescription(
							new Path(projectFile.toString()));
					
					IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(description.getName());
					
					project.delete(false, true, null);
					
					project = ResourcesPlugin.getWorkspace().getRoot().getProject(description.getName());
					
					if (project.isOpen() == false) {
						//System.out.println(String.format("Importing project %s", description.getName()));
						
						if(!project.exists()) {
							project.create(description, null);
						}
												
						project.open(null);
					}
					//System.out.println(String.format("Refreshing project %s", description.getName()));
					project.refreshLocal(IResource.DEPTH_INFINITE, null);
					System.out.println(String.format("Project Import %s SUCCESS", description.getName()));
					workspace.save(true, monitor);
				} catch (CoreException e) {
					if(description != null) {
						System.out.println(String.format("Project Import %s FAILED", description.getName()));
					}
					else {
						e.printStackTrace();
					}
					monitor.done();
				}
			}
		}
	}
}
