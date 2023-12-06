package ui;

import java.util.List;
import java.util.Scanner;

interface Action {
	String getDescription();

	void execute();
}

public class ActionMenu {
	private Action[] actions;
	private Action closingAction;
	private String message;
	private Scanner scanner;

	public final static Action PREV_PAGE_ACTION = new Action(){

		@Override
		public String getDescription() {
			return "Go Back";
		}

		@Override
		public void execute() {
			PageManager.prevPage();
			
		}};
	
	public ActionMenu(Action[] actions, Action closingAction, String message) {
		this.actions = actions;
		this.closingAction = closingAction;
		this.message = message;
		this.scanner = new Scanner(System.in);
	}
	public ActionMenu(Action[] actions, Action closingAction) {
		this(actions,closingAction,"Enter the index of the action to perform: ");
	}
	
	public ActionMenu(Action[] actions) {
		this(actions, null);
	}

	private void displayActions() {
		//System.out.println("Available Actions:");
		for (int i = 0; i < actions.length; i++) {
			System.out.println(i + 1 + ": " + actions[i].getDescription());
		}
		if (closingAction != null)
			System.out.println("0: " + closingAction.getDescription());
	}

	private void executeAction(int index) {
		if (choiceIsValid(index)) {
			if (index == 0)
				closingAction.execute();
			else
				actions[index - 1].execute();
		} else {
			System.out.println("Invalid choice. Please select a valid index.");
		}
	}

	private boolean choiceIsValid(int index) {
		return (index > 0 && index < actions.length + 1) || (closingAction!=null && index==0);
	}

	private int getUserChoice() {
		System.out.print(message);
		return scanner.nextInt();
	}

	private void closeScanner() {
		scanner.close();
	}

	public void execute() {
		displayActions();
		int choice = getUserChoice();

		while (!choiceIsValid(choice)) {
			System.out.println("Invalid choice. Please select a valid index.");
			displayActions();
			choice = getUserChoice();
		}
		executeAction(choice);
		closeScanner();
	}

}
