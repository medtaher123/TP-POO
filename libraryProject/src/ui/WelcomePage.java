package ui;

import authentification.AuthenticationSystem;
import ui.Action;

public class WelcomePage extends Page {

	Action[] actions = { new Action() {
		@Override
		public String getDescription() {
			return "books";
		}

		@Override
		public void execute() {
			PageManager.callPage(new bookListPage());;
		}
	}, new Action() {
		@Override
		public String getDescription() {
			return "Print Goodbye";
		}

		@Override
		public void execute() {
			System.out.println("Goodbye!");
		}
	} };
	Action closingAction = new Action() {

		@Override
		public String getDescription() {
			return "logOut";
		}

		@Override
		public void execute() {
			logout();
		}
		
	};
	
	public void display() {
		ConsoleHelper.printCenteredString("Welcome " + AuthenticationSystem.getActiveUser().getFirstName(), '-');
		System.out.println("what can I do for you today");

		new ActionMenu(actions,closingAction).execute();

	}
}
