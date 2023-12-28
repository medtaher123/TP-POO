package ui;

import Managers.PageManager;
import Managers.UserManager;
import authentication.AccessLevel;
import authentication.AuthenticationSystem;
import helpers.*;
import models.User;

import java.util.Date;
import java.util.regex.Pattern;

public class EditAccountPage extends Page {


    @Override
    protected String getTitle() {
        return "Edit Account";
    }

    @Override
    public int getAccessLevel() {
        return AccessLevel.ADMIN;
    }

    @Override
    protected void printContent() {
        User user = AuthenticationSystem.getActiveUser();
        System.out.println("First Name: " + user.getFirstName());
        System.out.println("Last Name: " + user.getLastName());
        System.out.println("Birth Date: " + DateHelper.format(user.getBirthDate()));
        System.out.println("Gender: " + user.getGender());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Company: " + user.getCompany());
        System.out.println("phone:" + user.getPhone());
        System.out.println("address: " + user.getAddress());
        System.out.println("PREFERENCES: ");
        String dateFormat = user.getPreferences().getDateFormat();
        if (dateFormat == null || dateFormat.isEmpty()) {
            System.out.println("Date Format: default (" + DateHelper.getUsersDateFormat() + ")");
        } else {
            System.out.println("Date Format: " + DateHelper.getUsersDateFormat());
        }
        ConsoleHelper.printNewLines(2);

        new ActionMenu(actions, ActionMenu.PREV_PAGE_ACTION).execute();
    }

    //other actions will be: edit email, phone, password, date format preference
    private Action[] actions = {
            new Action() {
                @Override
                public String getDescription() {
                    return "Edit First Name";
                }

                @Override
                public Object execute() {
                    String firstName = ConsoleHelper.input("Enter new first name");
                    AuthenticationSystem.getActiveUser().setFirstName(firstName);
                    AuthenticationSystem.updateActiveUser();
                    ConsoleHelper.printSuccess("First Name updated successfully.");
                    PageManager.redirect(new EditAccountPage());
                    return null;
                }
            },
            new Action() {
                @Override
                public String getDescription() {
                    return "Edit Last Name";
                }

                @Override
                public Object execute() {
                    String lastName = ConsoleHelper.input("Enter new last name");
                    AuthenticationSystem.getActiveUser().setLastName(lastName);
                    AuthenticationSystem.updateActiveUser();
                    ConsoleHelper.printSuccess("Last Name updated successfully.");
                    PageManager.redirect(new EditAccountPage());
                    return null;
                }
            },
            new Action() {
                @Override
                public String getDescription() {
                    return "Edit Birth Date";
                }

                @Override
                public Object execute() {
                    Date birthDate = ConsoleHelper.readDate("Birth date");
                    AuthenticationSystem.getActiveUser().setBirthDate(birthDate);
                    AuthenticationSystem.updateActiveUser();
                    ConsoleHelper.printSuccess("Birth Date updated successfully.");
                    PageManager.redirect(new EditAccountPage());
                    return null;
                }
            },
            new Action() {
                @Override
                public String getDescription() {
                    return "Edit Email";
                }

                @Override
                public Object execute() {
                    String email = inputValidEmail();
                    AuthenticationSystem.getActiveUser().setEmail(email);
                    AuthenticationSystem.updateActiveUser();
                    ConsoleHelper.printSuccess("Email updated successfully.");
                    PageManager.redirect(new EditAccountPage());
                    return null;
                }
            },
            new Action() {
                @Override
                public String getDescription() {
                    return "Edit Password";
                }

                @Override
                public Object execute() {
                    String password = inputPassword();
                    AuthenticationSystem.getActiveUser().setPassword(password);
                    AuthenticationSystem.updateActiveUser();
                    ConsoleHelper.printSuccess("Password updated successfully.");
                    PageManager.redirect(new EditAccountPage());
                    return null;
                }
            },
            new Action() {
                @Override
                public String getDescription() {
                    return "Edit Company";
                }

                @Override
                public Object execute() {
                    String company = ConsoleHelper.input("Enter new company");
                    AuthenticationSystem.getActiveUser().setCompany(company);
                    AuthenticationSystem.updateActiveUser();
                    ConsoleHelper.printSuccess("Company updated successfully.");
                    PageManager.redirect(new EditAccountPage());
                    return null;
                }
            },
            new Action() {
                @Override
                public String getDescription() {
                    return "Edit Gender";
                }

                @Override
                public Object execute() {
                    String lastName = ConsoleHelper.input("Enter gender");
                    AuthenticationSystem.getActiveUser().setLastName(lastName);
                    AuthenticationSystem.updateActiveUser();
                    ConsoleHelper.printSuccess("Gender updated successfully.");
                    PageManager.redirect(new EditAccountPage());
                    return null;
                }

            },
            new Action() {
                @Override
                public String getDescription() {
                    return "Edit date format preference";
                }

                @Override
                public Object execute() {
                    String dateFormat = ConsoleHelper.input("Enter new date format.\nNo check is done on the format so please enter a valid format or else the program will crash");
                    AuthenticationSystem.getActiveUser().getPreferences().setDateFormat(dateFormat);
                    AuthenticationSystem.updateActiveUser();
                    ConsoleHelper.printSuccess("Date format updated successfully.");
                    PageManager.redirect(new EditAccountPage());
                    return null;
                }

            }
    };

    private String inputValidEmail() {
        String email = null;
        while (true) {
            email = ConsoleHelper.input("Email");
            if (!emailIsValid(email)) {
                ConsoleHelper.printError("Email Not Valid!\n");
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

    private String inputPassword() {
        String password;
        while (true) {
            password = ConsoleHelper.input("Enter password");
            if (!isStrongPassword(password)) {
                ConsoleHelper.printWarning("Password is not strong enough. It should be at least 8 characters long "
                        + "and contain at least one digit and one special character.");
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
}
