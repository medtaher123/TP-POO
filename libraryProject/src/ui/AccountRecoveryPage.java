package ui;

import helpers.BarcodeScannerSimulator;
import models.User;
import services.UsersService;

public class AccountRecoveryPage extends BackOnlyPage {

	@Override
	void printContent() {
		User user = BarcodeScannerSimulator.scanUser();
		System.out.println("email: "+user.getEmail());
		user.setPassword("0000");
		UsersService.UpdateUser(user);
		System.out.println("Password reset to '0000'");
	}

	@Override
	protected String getTitle() {
		return "Account Recovery";
	}

}
