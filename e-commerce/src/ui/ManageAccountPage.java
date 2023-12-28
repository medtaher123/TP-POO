package ui;

import Managers.PageManager;
import authentication.AccessLevel;
import authentication.AuthenticationSystem;
import helpers.Action;
import helpers.ActionMenu;
import helpers.ConsoleHelper;
import helpers.DateHelper;
import models.User;

public class ManageAccountPage extends Page {

	Action[] actions = {
			new Action() {

				@Override
				public String getDescription() {
					return "Edit Account";
				}

				@Override
				public Object execute() {
					PageManager.callPage(new EditAccountPage());
					return null;
				}
			}
	};
	
	@Override
	protected void printContent() {
		User user = AuthenticationSystem.getActiveUser();
		System.out.println("First Name: "+ user.getFirstName());
		System.out.println("Last Name: "+ user.getLastName());
		System.out.println("Birth Date: "+ DateHelper.format(user.getBirthDate()));
		System.out.println("Gender: "+ user.getGender());
		System.out.println("Email: "+ user.getEmail());
		System.out.println("Company: "+ user.getCompany());
		System.out.println("phone:"+ user.getPhone());
		System.out.println("address: "+ user.getAddress());
		System.out.println("PREFERENCES: ");
		String dateFormat = user.getPreferences().getDateFormat();
		if (dateFormat == null || dateFormat.isEmpty()) {
			System.out.println("Date Format: default (" + DateHelper.getUsersDateFormat() + ")");
		} else {
			System.out.println("Date Format: " + DateHelper.getUsersDateFormat());
		}
		ConsoleHelper.printNewLines(2);
		
		new ActionMenu(actions,ActionMenu.PREV_PAGE_ACTION).execute();
	}

	@Override
	protected String getTitle() {
		return "Account";
	}

	@Override
	public int getAccessLevel() {
		return AccessLevel.ALL_EXCEPT_GUEST;
	}

}
