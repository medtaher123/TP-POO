package helpers;

import java.util.Random;

import static helpers.ConsoleHelper.scanner;

public class CaptchaTest {
//TODO doc: this class is used to simulate a CAPTCHA system, it's not a real CAPTCHA system, it's just a simple math question.
    public static boolean run() {

        int num1 = new Random().nextInt(10);
        int num2 = new Random().nextInt(10);

        System.out.println("Please solve the following CAPTCHA to proceed:");
        System.out.println("What is the sum of " + num1 + " and " + num2 + "?");

        int userInput;
        try {
            userInput = Integer.parseInt(scanner.nextLine().trim());

            if (userInput == num1 + num2) {
                ConsoleHelper.printSuccess("CAPTCHA passed. Proceed.");
                return true;
            } else {
                ConsoleHelper.printError("CAPTCHA failed. Access denied.");
            }
        } catch (NumberFormatException e) {
            ConsoleHelper.printError("Invalid input. Please enter a number.");
        }
        return false;
    }
}
