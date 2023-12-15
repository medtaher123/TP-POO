package ui;

import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

import helpers.ConsoleHelper;
import models.User;
import services.UsersService;

public class newMemberPage extends BackOnlyPage {
	// TODO: add to doc: the same email can't be used in 2 different accounts
	@Override
	void printContent() {
		Scanner scanner = new Scanner(System.in);
		String firstName = ConsoleHelper.input("First name");
		String lastName = ConsoleHelper.input("Last name");

		String email = null;
		while (true) {
			email = ConsoleHelper.input("Email");
			if (!emailIsValid(email)) {
				System.out.println("Email Not Valid!");
				continue;
			}
			if (!UsersService.EmailIsAvailable(email)) {
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

	
		User newUser = new User(firstName, lastName, birthDate, User.UserType.MEMBER, new Date(), gender, email, company, phone, address, null,password);
		

		newUser = UsersService.addUser(newUser);

		System.out.println(newUser == null
				? "adding new User Failed. Please try again. If the problem persists please contact your system administrator."
				: "New User created successfully.\nUser must pay " + 5 + "dt\n");

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
