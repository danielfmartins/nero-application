package com.neroapp.console.command;

public class NeroLeafCommand extends NeroCommand {
	
	private Runnable commandDelegate;
	
	public NeroLeafCommand(String description,Runnable commandDelegate) {
		this.description = description;
		this.commandDelegate = commandDelegate;
	}

	@Override
	public void show() {
		console.println(description);
	}

	@Override
	protected void interactWithUser() {
		commandDelegate.run();
	}
}
