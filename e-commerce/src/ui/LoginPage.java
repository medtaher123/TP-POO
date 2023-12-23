package ui;


import Managers.PageManager;
import authentication.AccessLevel;
import authentication.AuthenticationSystem;
import authentication.AuthenticationSystem.AuthenticationException;
import helpers.ConsoleHelper;

public class LoginPage extends Page {

	@Override
	protected String getTitle() {
		return "Login";
	}

	@Override
	public int getAccessLevel() {
		return AccessLevel.ALL;
	}

	@Override
	public void printContent() {

		boolean UserHaveAccount = ConsoleHelper.readBoolean("Do you already have an account?");

		if(!UserHaveAccount){
			PageManager.callPage(new NewAccountPage());
			return;
		}

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
				System.out.println("if you forgot your email or password, contact an Admin to restore your account");
			}
		}
		System.out.println("sucsesfully logged in!!");
		//ConsoleHelper.sleep(1000);

        PageManager.callPage(new WelcomePage());
	}


}
