package ui;

import Managers.UserManager;
import helpers.BarcodeScannerSimulator;

public class DeleteUserPage extends BackOnlyPage {

	@Override
	void printContent() {
		UserManager.deleteUser(BarcodeScannerSimulator.scanUser());
		System.out.println("User Deleted Succefully (this text is static, there is no verification if it's deleted or not)");
	}

	@Override
	protected String getTitle() {
		return "Delete User";
	}

}
