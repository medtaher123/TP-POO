package ui;

import java.io.File;
import java.nio.file.ClosedFileSystemException;
import java.util.Arrays;

import authentification.AuthenticationSystem;
import authentification.AuthenticationSystem.AuthenticationException;
import models.User;
import services.UsersService;

public class LoginPage extends Page {
	@Override
	public void display() {

		ConsoleHelper.printCenteredString("Login", '*');
		ConsoleHelper.printNewLines(3);

		try { //TODO to remove (just for testing
			System.out.println("automatic login!");
			AuthenticationSystem.login("holtpickett@accupharm.com", "laboreduis");
		} catch (AuthenticationException e1) {
			e1.printStackTrace();
		}
		
		while (!AuthenticationSystem.isLoggedIn()) {
			System.out.println("email: ");
			String email = ConsoleHelper.scanner.nextLine();
			System.out.println("password: ");
			String password = ConsoleHelper.scanner.nextLine();
			try {
				AuthenticationSystem.login(email, password);
			} catch (AuthenticationException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("sucsesfully logged in!!");
		PageManager.callPage(new WelcomePage());
	}

}
