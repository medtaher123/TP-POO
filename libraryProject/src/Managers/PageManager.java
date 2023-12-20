package Managers;

import java.util.Stack;

import authentification.AuthenticationSystem;
import helpers.ConsoleHelper;
import ui.LoginPage;
import ui.Page;

public class PageManager {

	private static Stack<Page> stk = new Stack<>();

	public static void restartApp() {
		clearPageStack();
		SettingsManager.reloadSettings();
		new LoginPage().printContent();
	}

	public static void callPage(Page page) {
		// Always check for authentication
		if (AuthenticationSystem.isLoggedIn()) {
			stk.push(page);
			printPageSeparator();
			page.execute();
		} else {
			restartApp();
		}
	}

	public static void redirect(Page page) {
		stk.pop().onDestroy();
		callPage(page);
	}

	public static void prevPage() {
		stk.pop().onDestroy();
		printPageSeparator();
		stk.peek().reset();
		stk.peek().execute();
	}

	public static void clearPageStack() {
		stk.clear();
	}

	private static void printPageSeparator() {
		ConsoleHelper.printNewLines(3);
	}
}
