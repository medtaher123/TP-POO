package ui;

import authentification.AuthenticationSystem;
import helpers.ConsoleHelper;

public abstract class Page {
	abstract void printContent();
	protected abstract String getTitle();

	public void execute() {
		// System.out.println("executePage called");
		// TODO: add to doc: added an execute method to potentially add some logic
		// before/after displaying the page if needed
		printTitle();
		printContent();
	}

	public void reset() {
	}

	public void onDestroy() {
	}

	protected void logout() {
		AuthenticationSystem.logout();
		PageManager.restartApp();
	}

	protected void close() {
		PageManager.prevPage();
	}
	protected void printTitle() {
		ConsoleHelper.printCenteredString(getTitle(), '*');
		ConsoleHelper.printNewLines(2);
	}

}
