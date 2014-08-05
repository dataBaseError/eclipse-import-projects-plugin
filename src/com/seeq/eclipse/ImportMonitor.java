package com.seeq.eclipse;

import java.awt.Composite;

import javax.annotation.PostConstruct;

import org.eclipse.core.runtime.IProgressMonitor;

public class ImportMonitor implements IProgressMonitor {
	
	private boolean exitOnFinish;

	public ImportMonitor(boolean exitOnFinish) {
		this.exitOnFinish = exitOnFinish;
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
		if (exitOnFinish) {
			// Kill the jvm once the task is done.
			System.exit(0);
		}
	}

	@Override
	public void beginTask(String name, int totalWork) {
		// Do nothing
	}
}
