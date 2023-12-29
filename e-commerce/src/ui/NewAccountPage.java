package ui;

import Managers.UserManager;
import authentication.AccessLevel;
import authentication.AuthenticationSystem;
import helpers.ActionMenu;
import helpers.ConsoleHelper;
import models.User;

import java.util.Date;
import java.util.regex.Pattern;

public class NewAccountPage extends Page {

    @Override
    protected String getTitle() {
        return "New Account";
    }

    @Override
    public int getAccessLevel() {
        return AccessLevel.ALL;
    }
    @Override
    protected void printContent() {
        String firstName = ConsoleHelper.input("First name");
        String lastName = ConsoleHelper.input("Last name");

        String email = inputValidEmail();
        String password = inputPassword();

        Date birthDate = ConsoleHelper.readDate("Birth date");
        String gender = ConsoleHelper.input("Gender");
        String phone = ConsoleHelper.input("Phone");
        String address = ConsoleHelper.input("Address");
        String company = ConsoleHelper.input("Company");

        try {
            User user = UserManager.createNewUser(firstName,lastName,birthDate, User.UserType.CUSTOMER,gender,email,company,phone,address,password);
        } catch (AuthenticationSystem.AuthenticationException e) {
            System.out.println("New Account created successfully.\n Could not automatically login");
            new ActionMenu(null,ActionMenu.LOGIN_PAGE,"").execute();
            return;
        }

        if(AuthenticationSystem.isLoggedIn()){
            ConsoleHelper.printSuccess( "New Account created successfully.\n");
            new ActionMenu(null,ActionMenu.BACK_TO_HOMEPAGE_ACTION,"").execute();
        }
        else{
            ConsoleHelper.printError("Account creation Failed.");
            System.out.println(" Please try again. If the problem persists please contact your system administrator.");
            new ActionMenu(null,ActionMenu.LOGIN_PAGE,"").execute();
        }
    }

    private String inputPassword(){
        String password;
        while(true){
            password = ConsoleHelper.input("Enter password");
            if (!isStrongPassword(password)) {
                ConsoleHelper.printWarning("Password is not strong enough. It should be at least 8 characters long "
                        + "and contain at least one digit and one special character.\n");
                continue;
            }

            String confirmPassword = ConsoleHelper.input("Confirm password");

            if (!password.equals(confirmPassword)) {
                ConsoleHelper.printError("Passwords do not match. Please try again.");
            } else return password;
        }
    }
    private static boolean isStrongPassword(String password) {
        return password.length() >= 8 && password.matches(".*\\d.*") && password.matches(".*[!@#$%^&*()].*");
    }
    private String inputValidEmail() {
        String email=null;
        while (true) {
            email = ConsoleHelper.input("Email");
            if (!emailIsValid(email)) {
                ConsoleHelper.printError("Email Not Valid!");
                continue;
            }
            if (!UserManager.EmailIsAvailable(email)) {
                ConsoleHelper.printError("Email already used by another account!");
                continue;
            }
            return email;
        }
    }

    private boolean emailIsValid(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

}
