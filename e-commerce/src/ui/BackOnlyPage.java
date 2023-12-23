package ui;

import helpers.ActionMenu;

public abstract class BackOnlyPage extends Page {
	
	
	public void execute() {
		super.execute();
		new ActionMenu(null,ActionMenu.PREV_PAGE_ACTION,"").execute();
	}

}
