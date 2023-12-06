package ui;

import java.util.Stack;

import authentification.AuthenticationSystem;

public class PageManager {

	private static Stack<Page> stk= new Stack<>();
	
	public static void restartApp() {
		clearPageStack();
		new LoginPage().display();
	}
	public static void callPage(Page page) {
		if(AuthenticationSystem.isLoggedIn()) {
		stk.push(page);
		page.display();
		}
		else {
			restartApp();
		}
	}
	public static void prevPage() {
		stk.pop().onDestroy();
		stk.peek().reset();
		stk.peek().display();
	}
	public static void clearPageStack() {
		stk.clear();
	}
}
