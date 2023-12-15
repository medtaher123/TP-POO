package Managers;

import java.util.Date;

import helpers.DateHelper;
import models.User;
import services.UsersService;

public class UserManager {
	//TODO: add to doc: Managers are classes that handle the logic behind Models, since Models are only structures 

	public static void renewSubscription(User user) {
		Date expirationDate = DateHelper.addDays(new Date(), SettingsManager.getSettings().getSubscriptionDuration());
		user.setSubscriptionExpirationDate(expirationDate);
		user = UsersService.UpdateUser(user);
		
	}
	
	
}
