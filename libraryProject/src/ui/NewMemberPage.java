package ui;

import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

import Managers.SettingsManager;
import Managers.UserManager;
import helpers.ConsoleHelper;
import models.User;
import services.UsersService;

public class NewMemberPage extends BackOnlyPage {
	
	@Override
	void printContent() {
		System.out.println("User must pay " + SettingsManager.getSettings().getSubscriptionFee() + " dt for subscription.");
		String firstName = ConsoleHelper.input("First name");
		String lastName = ConsoleHelper.input("Last name");

		String email = null;
		while (true) {
			email = ConsoleHelper.input("Email");
			if (!emailIsValid(email)) {
				System.out.println("Email Not Valid!");
				continue;
			}
			if (!UserManager.EmailIsAvailable(email)) {
				System.out.println("Email already used by another account!");
				continue;
			}
			break;

		}

		Date birthDate = ConsoleHelper.readDate("Birth date");
		String gender = ConsoleHelper.input("Gender");
		String phone = ConsoleHelper.input("Phone");
		String address = ConsoleHelper.input("Address");
		String company = ConsoleHelper.input("Company");
		String password = "0000";


		boolean subscriptionPayed = ConsoleHelper.readBoolean("Did the user pay the subscription?");
		User newUser = UserManager.createNewUser(firstName, lastName, birthDate, User.UserType.MEMBER, gender, email, company, phone, address, password, subscriptionPayed);
		

		System.out.println(newUser == null
				? "adding new User Failed. Please try again. If the problem persists please contact your system administrator."
				: "New User created successfully. Library Card sent to printer\n");

	}

	private boolean emailIsValid(String email) {
		if (email == null || email.isEmpty()) {
			return false;
		}
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern = Pattern.compile(emailRegex);
		return pattern.matcher(email).matches();
	}

	@Override
	protected String getTitle() {
		return "New Member";
	}

}
