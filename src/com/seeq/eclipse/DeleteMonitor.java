package com.seeq.eclipse;

import java.awt.Composite;
import java.io.File;

import javax.annotation.PostConstruct;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

public class DeleteMonitor implements IProgressMonitor {
	
	private File file;
	private ImportMonitor import_monitor;

	public DeleteMonitor(File file, ImportMonitor import_monitor) {
		this.file = file;
		this.import_monitor = import_monitor;
	}

	@PostConstruct
	public void createControls(Composite parent) {
		// Do nothing
	}

	@Override
	public void worked(final int work) {
		// Do nothing
	}

	@Override
	public void subTask(String name) {
		// Do nothing
	}

	@Override
	public void setTaskName(String name) {
		// Do nothing
	}

	@Override
	public void setCanceled(boolean value) {
		// Do nothing
	}

	@Override
	public boolean isCanceled() {
		return false;
	}

	@Override
	public void internalWorked(double work) {
		// Do nothing
	}

	@Override
	public void done() {
		
		boolean finished = false;
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		
		IProjectDescription description = null;
		try {
			description = workspace.loadProjectDescription(
					new Path(file.toString()));
			
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(description.getName());
			
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
			
			finished = true;
			workspace.save(true, import_monitor);
		} catch (CoreException e) {
			if(description != null) {
				System.out.println(String.format("Project Import %s FAILED", description.getName()));
			}
			else {
				e.printStackTrace();
			}
			
			if (!finished) {
				import_monitor.done();
			}
		}
	}

	@Override
	public void beginTask(String name, int totalWork) {
		// Do nothing
	}
}
