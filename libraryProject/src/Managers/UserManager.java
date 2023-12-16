package Managers;

import java.util.Date;

import helpers.DateHelper;
import models.User;
import models.User.UserType;
import services.UsersService;

public class UserManager {
	//TODO: add to doc: Managers are classes that handle the logic behind Models, since Models are only structures 

	public static User renewSubscription(User user) {
		Date expirationDate = DateHelper.addDays(new Date(), SettingsManager.getSettings().getSubscriptionDuration());
		user.setSubscriptionExpirationDate(expirationDate);
		return UsersService.UpdateUser(user);
	}
	
	public static User createNewUser(String firstName, String lastName, Date birthDate, UserType type, String gender,
			String email, String company, String phone, String address, String password, boolean subscribtionPayed) {
		Date expirationDate = subscribtionPayed? DateHelper.addDays(new Date(), SettingsManager.getSettings().getSubscriptionDuration()) : null;
		User newUser = new User(firstName, lastName, birthDate, type, new Date(), gender, email, company, phone, address, expirationDate,password);
		return UsersService.addUser(newUser);
	}
	public static boolean EmailIsAvailable(String email) {
		return UsersService.getUserByEmail(email)==null;
	}
	
}
