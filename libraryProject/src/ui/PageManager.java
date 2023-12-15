package ui;

import java.util.Stack;

import Managers.SettingsManager;
import authentification.AuthenticationSystem;
import helpers.ConsoleHelper;

public class PageManager {

	private static Stack<Page> stk= new Stack<>();
	
	public static void restartApp() {
		clearPageStack();
		SettingsManager.reloadSettings();
		new LoginPage().printContent();
	}
	public static void callPage(Page page) {
		if(AuthenticationSystem.isLoggedIn()) {
		stk.push(page);
		printPageSeparator();
		page.execute();
		}
		else {
			restartApp();
		}
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
