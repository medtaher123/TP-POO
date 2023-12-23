package Managers;

import authentication.AccessLevel;
import authentication.AuthenticationSystem;
import helpers.ConsoleHelper;
import ui.AcessDeniedPage;
import ui.LoginPage;
import ui.Page;
import ui.WelcomePage;

import java.util.Stack;

public class PageManager {

    private static final Stack<Page> stk = new Stack<>();

    public static void restartApp() {
        clearPageStack();
        AuthenticationSystem.logout();
        SettingsManager.reloadSettings();
        callPage(new LoginPage());
    }

    //TODO: add back to home
    public static void callPage(Page page) {
        // Always check for authentication
        if (AccessLevel.isAuthorized(page.getAccessLevel())) {
            stk.push(page);
            printPageSeparator();
            page.execute();
        } else {
            callPage(new AcessDeniedPage(page.getAccessLevel()));
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
    public static void BackTOHomepage() {
        clearPageStack();
        printPageSeparator();
        callPage(new WelcomePage());
    }

    public static void clearPageStack() {
        stk.clear();
    }

    private static void printPageSeparator() {
        ConsoleHelper.printNewLines(3);
    }

}
