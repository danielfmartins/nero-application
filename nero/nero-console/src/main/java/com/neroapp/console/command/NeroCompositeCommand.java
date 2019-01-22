package com.neroapp.console.command;

import java.util.HashMap;

public class NeroCompositeCommand extends NeroCommand {

	private HashMap<Integer, NeroCommand> menuOptions = new HashMap<Integer, NeroCommand>();
	private int menuOptionsCount = 0;
	private int selectedOption = -999;

	public NeroCompositeCommand(String description) {
		this.description = description;
	}

	public void addCommand(NeroCommand command) {
		command.setPreviousCommand(this);
		menuOptionsCount++;
		menuOptions.put(menuOptionsCount, command);
	}

	@Override
	public void setPreviousCommand(NeroCommand neroCommand) {
		super.setPreviousCommand(neroCommand);
		menuOptions.put(0, neroCommand);
	}

	private void showHeader() {
		console.println("");
		console.println(super.getDescription());
		console.println("");
	}

	@Override
	public void show() {
		showHeader();
		int menuSize = menuOptions.keySet().size();
		for (int i = 1; i <= menuSize; i++) {
			console.println(i + ". " + menuOptions.get(i).description);
		}
	}

	@Override
	protected void interactWithUser() {
		// sets up the exit command if previous command was not defined;
		/*
		if (this.previousCommand==null) {
			this.setPreviousCommand(NeroCommand.exitCommand());
		}
		*/
		selectedOption = -1;
		while (selectedOption == -1) {
			String option = console.readLine("Escolha uma opcao:");
			try {
				selectedOption = Integer.parseInt(option);
				if (selectedOption<0||selectedOption>menuOptions.size()) selectedOption = -1;
			} catch (NumberFormatException nfe) {
				selectedOption = -1;
			}
			switch (selectedOption) {
			case -1: // do nothing. The menu will be shown again.
				break;
			default:
				NeroCommand cmd = menuOptions.get(selectedOption);
				cmd.run();
			}
		}
	}
}
