package helpers;

import models.User.UserType;

import java.util.Date;
import java.util.Scanner;

public class ConsoleHelper {
	public final static int DEFAULT_CONSOLE_WIDTH=80; 
	public static Scanner scanner = new Scanner(System.in);
	
	public static void printNewLines(int i) {
		System.out.print("\n".repeat(i));
	}

	public static void printCenteredString(String inputString, char paddingChar) {
		int consoleWidth = DEFAULT_CONSOLE_WIDTH; 
		inputString = " " + inputString + " ";
        int stringLength = inputString.length();
        
        if (stringLength >= consoleWidth) {
            System.out.println(inputString);
        } else {
            int paddingLength = (consoleWidth - stringLength) / 2;
            String padding = String.valueOf(paddingChar).repeat(Math.max(0, paddingLength));
            String centeredString = padding + inputString + padding;
            System.out.println(centeredString);
        }
	}
    
	public static String input(String inputField) {
		System.out.print(inputField+": ");
		return scanner.nextLine();
	}
	
	public static boolean readBoolean(String message) {
		System.out.print(message+" (y/n)");
		char input = scanner.nextLine().charAt(0);
		return input=='y' || input =='Y';
	}
	@SuppressWarnings("deprecation")
	public static Date readDate(String inputField) {
		boolean success = false;
		Date date=null;
		while (!success) {
			try {
				date = new Date(input(inputField + " (MM/DD/YYYY)"));
				success=true;
			} catch (IllegalArgumentException e) {
				System.out.println("invalid date, try again.");
			}
		}
		return date;

	}

	public static UserType readUserType() {
		String userType=null;
		while(true) {
			userType = input("User Type ('C':customer ,'A': admin)");
			switch(userType.charAt(0)) {
			case 'C':
				return UserType.CUSTOMER;
			case 'A':
				return UserType.ADMIN;
			default:
				System.out.println("invalid input");
			}
		}
	}

    public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
    }


	public static int readPositiveInt(String message){
		int val;
		while(true){
			val = readInt(message,-1);
			if(val>=0){
				return val;
			}
			System.out.println("invalid input. please try again");
		}
	}
    public static int readInt() {

		return readInt(-1);
    }

    public static int readInt(int errorValue) {
        return readInt("", errorValue);
    }


    public static int readInt(String message, int errorValue) {
		System.out.print(message);
		try {
			return scanner.nextInt();
		}
		catch (Exception e){
			return errorValue;
		}
		finally {
			scanner.nextLine(); //TODO: add to doc: nextInt doesn't read the following new-line character, so the first nextLine (which returns the rest of the current line) will always return an empty string.
		}
	}

	public static String getPaddedString(String inputString, int length) {
		StringBuilder paddedString = new StringBuilder(inputString);
		int spacesToAdd = length - inputString.length();
        paddedString.append(" ".repeat(Math.max(0, spacesToAdd)));//IDE propossed this solution (repeat)
		return paddedString.toString();
	}
}


