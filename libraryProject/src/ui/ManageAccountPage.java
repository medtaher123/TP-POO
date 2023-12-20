package ui;

import Managers.PageManager;
import authentification.AuthenticationSystem;
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
				public void execute() {
					//PageManager.callPage(new EditAccountPage());
					PageManager.callPage(new NotFoundPage());
					
				}
			}
	};
	
	@Override
	void printContent() {
		User user = AuthenticationSystem.getActiveUser();
		System.out.println("First Name: "+ user.getFirstName());
		System.out.println("Last Name: "+ user.getLastName());
		System.out.println("Birth Date: "+ DateHelper.format(user.getBirthDate()));
		System.out.println("Gender: "+ user.getGender());
		System.out.println("Email: "+ user.getEmail());
		System.out.println("Company: "+ user.getCompany());
		System.out.println("phone:"+ user.getPhone());
		System.out.println("address: "+ user.getAddress());
		if(user.getType()==User.UserType.MEMBER) {
			if(user.isSubscribed()) {
				System.out.println("subscription ends: " + DateHelper.format(user.getSubscriptionExpirationDate()));
			}
			else {
			 System.out.println("Please proceed to the reception area to renew your subscription");
			}
		}
		System.out.println("PREFERENCES: ");
		System.out.println("Date Format: "+ user.getPreferences().getDateFormat());	
		ConsoleHelper.printNewLines(2);
		
		new ActionMenu(actions,ActionMenu.PREV_PAGE_ACTION).execute();
	}

	@Override
	protected String getTitle() {
		return "Account";
	}

}
