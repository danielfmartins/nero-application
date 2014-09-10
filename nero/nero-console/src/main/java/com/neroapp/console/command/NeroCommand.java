package com.neroapp.console.command;

import com.neroapp.console.Console;
import com.neroapp.console.command.delegate.NeroCommandDelegate;


public abstract class NeroCommand {

	public static Console console = com.neroapp.console.Console
			.getConsole();

	protected String description;
	protected String requestUserInteractionText;
	// String default de saida e' zero. Cada comando pode definir sua string de saida
	protected String exitResponse = "0"; 
	
	protected NeroCommand previousCommand;
	protected String userResponse;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setPreviousCommand(NeroCommand previous) {
		this.previousCommand = previous;
	}
	
	public NeroCommand getPreviousCommand() {
		return previousCommand;
	}
	
	public String getRequestUserInteractionText() {
		return requestUserInteractionText;
	}

	public void setRequestUserInteractionText(String requestUserInteractionText) {
		this.requestUserInteractionText = requestUserInteractionText;
	}


	/**
	 * O comando se apresenta ao usuário
	 */
	protected abstract void  show() ;
	
	
	/**
	 * Make all the interaction with user
	 */
	protected abstract void interactWithUser();

    /**
     * O comando obtem uma interação com o usuario.
     * 
     * @return
     */
	protected void getUserResponse() {
		userResponse = console.readLine(getRequestUserInteractionText());
		userResponse.trim();
	}
	
	
	public final void run() {
		show();
		interactWithUser();
		previousCommand.run();
	}
	
	
	protected boolean userRequestExit() {
		return exitResponse.equalsIgnoreCase(this.userResponse);
	}

	public static NeroCommand exitCommand() {
		return new NeroLeafCommand("",new NeroCommandDelegate(null) {
			@Override
			public void run() {
				System.exit(0);
			}});
	}

}
