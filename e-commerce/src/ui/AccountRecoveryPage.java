package ui;

import authentication.AccessLevel;
import helpers.ConsoleHelper;
import models.User;
import services.UsersService;

public class AccountRecoveryPage extends BackOnlyPage {
    @Override
    protected String getTitle() {
        return "Account Recovery";
    }

    @Override
    public int getAccessLevel() {
        return AccessLevel.ADMIN;
    }

    //this method reads user email and then updates the password of that user in the database to "0000"
    @Override
    protected void printContent() {
        String email = ConsoleHelper.input("Enter the email of the user you want to recover the account");
        User user = UsersService.getUserByEmail(email);
        if (user == null) {
            ConsoleHelper.printError("User not found");
        }
        else {
            user.setPassword("0000");
            UsersService.updateUser(user);
            ConsoleHelper.printSuccess("Password updated successfully. The new password is 0000");
        }
    }
}
