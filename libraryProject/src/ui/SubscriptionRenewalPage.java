package ui;

import java.util.Date;

import Managers.BarcodeScannerSimulator;
import Managers.SettingsManager;
import Managers.UserManager;
import helpers.ConsoleHelper;
import helpers.DateHelper;
import models.User;
import services.UsersService;

public class SubscriptionRenewalPage extends BackOnlyPage {

	@Override
	void printContent() {
		System.out.println("User must pay " + SettingsManager.getSettings().getSubscriptionFee() + " dt to renew subscription.");
		User user = BarcodeScannerSimulator.scanUser();
		if(user.getType()!=User.UserType.MEMBER) {
			System.out.println("User " + user.getShortDisplay() + " is not a member (" + user.getType() + ")");
		}
		else if(user.isSubscribed()) 
			System.out.println("User " + user.getShortDisplay() + " is already subscribed. Expiration date: "+DateHelper.format(user.getSubscriptionExpirationDate()));			
		else {
			UserManager.renewSubscription(user);
			System.out.println("Subscription renewed sucefully.");
		}
		
	}

	@Override
	protected String getTitle() {
		return "Renew Subscription";
	}

	
}
