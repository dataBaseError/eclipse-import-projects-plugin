package com.seeq.eclipse;

import java.awt.Composite;

import javax.annotation.PostConstruct;

import org.eclipse.core.runtime.IProgressMonitor;

public class ImportMonitor implements IProgressMonitor {
	
	private boolean exitOnFinish;
	private int total;
	private int count;
	
	public ImportMonitor(boolean exitOnFinish, int total) {
		this.exitOnFinish = exitOnFinish;
		this.total = total;
		this.count = 0;
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
		
		count++;
		
		if (exitOnFinish && count >= total ) {
			// Kill the jvm once the task is done.
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.exit(0);
		}
		
	}

	@Override
	public void beginTask(String name, int totalWork) {
		// Do nothing
	}
}
