package ui;

import java.io.File;
import java.nio.file.ClosedFileSystemException;
import java.util.Arrays;

import authentification.AuthenticationSystem;
import authentification.AuthenticationSystem.AuthenticationException;
import helpers.ConsoleHelper;
import models.User;
import services.UsersService;

public class LoginPage extends Page {

	@Override
	protected String getTitle() {
		return "Login";
	}
	
	@Override
	public void printContent() {

		try { //TODO to remove (just for testing)
			System.out.println("automatic login!");
			AuthenticationSystem.login("holtpickett@accupharm.com", "laboreduis");
		} catch (AuthenticationException e1) {
			e1.printStackTrace();
		}
	
		while (!AuthenticationSystem.isLoggedIn()) {//SQL injection vulnerability xd (not really SQL)
			String email = ConsoleHelper.input("email"); 
			String password = ConsoleHelper.input("password"); 
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
