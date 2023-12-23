package ui;

import Managers.PageManager;
import authentication.AuthenticationSystem;
import helpers.ConsoleHelper;

public abstract class Page {

	protected abstract String getTitle();
	public abstract int getAccessLevel();
	abstract protected void printContent();
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
