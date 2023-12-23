package ui;

import Managers.PageManager;
import authentication.AccessLevel;
import helpers.Action;
import helpers.ActionMenu;

public class AcessDeniedPage extends Page {
    private final int pageAccessLevel;
    public AcessDeniedPage(int pageAccessLevel) {
        super();
        this.pageAccessLevel = pageAccessLevel;
    }

    @Override
    protected void printContent() {
        int userAccessLevel = AccessLevel.getUserAccessLevel();
        Action[] actions = null;
        if (pageAccessLevel == AccessLevel.ADMIN) {
            System.out.println("you are not authorized to access this page");
        } else {
            System.out.println("you have to log in to access this page");
            actions = new Action[]{loginAction};
        }
        new ActionMenu(actions,ActionMenu.PREV_PAGE_ACTION,"").execute();


    }

    @Override
    protected String getTitle() {
        return "Access Denied";
    }

    @Override
    public int getAccessLevel() {
        return AccessLevel.ALL;
    }

    Action loginAction = new Action() {
        @Override
        public String getDescription() {
            return "Login";
        }

        @Override
        public Object execute() {
            PageManager.restartApp();
            return null;
        }
    };
}
