package ui;

import Managers.PageManager;
import authentification.AuthenticationSystem;
import helpers.ConsoleHelper;

public abstract class Page {
	abstract void printContent();
	protected abstract String getTitle();
	
	public void execute() {
		// System.out.println("executePage called");
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
