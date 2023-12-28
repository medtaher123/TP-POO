package ui;

import Managers.PageManager;
import helpers.ConsoleColors;
import helpers.ConsoleHelper;

public abstract class Page {

    private static final String TITLE_COLOR = ConsoleColors.CYAN_BOLD;
    protected static final String NUMBER_TAG_COLOR = ConsoleColors.BLUE;

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
        ConsoleHelper.printColoredCenteredString(getTitle(), '*', TITLE_COLOR);
        ConsoleHelper.printNewLines(2);
    }

}
