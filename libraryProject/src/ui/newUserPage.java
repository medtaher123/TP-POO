package ui;

import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

import Managers.PageManager;
import Managers.SettingsManager;
import Managers.UserManager;
import helpers.ConsoleHelper;
import models.User;
import models.User.UserType;
import services.UsersService;

public class newUserPage extends BackOnlyPage {

	@Override
	void printContent() {
		UserType userType = ConsoleHelper.readUserType();
		if(userType==UserType.MEMBER) {
			PageManager.redirect(new NewMemberPage());
			return;
		}
		
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
		String company = "Library";
		String password = "0000";
		//Password is by default "0000". You have to change it manually (recommended)

		User newUser = UserManager.createNewUser(firstName, lastName, birthDate, userType, gender, email, company, phone, address, password, false);
		

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
