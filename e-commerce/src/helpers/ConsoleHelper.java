package helpers;

import models.User.UserType;

import java.util.Date;
import java.util.Scanner;

public class ConsoleHelper {
	public final static int DEFAULT_CONSOLE_WIDTH=80;
	public static final String Y_N_COLOR = ConsoleColors.YELLOW;
	public static final String ERROR_COLOR = ConsoleColors.RED;
	public static final String WARNING_COLOR = ConsoleColors.YELLOW;
	public static final String SUCCESS_COLOR = ConsoleColors.GREEN;
	public static final String HINT_COLOR = ConsoleColors.PURPLE;

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
	public static void printColoredCenteredString(String inputString, char paddingChar, String color) {
		ConsoleColors.startColor(color);
		printCenteredString(inputString, paddingChar);
		ConsoleColors.resetColor();
	}
    
	public static String input(String inputField) {
		System.out.print(inputField+": ");
		String result = scanner.nextLine().trim();
		if(result.equals("null"))
			return null;
		return result;
	}
	
	public static boolean readBoolean(String message) {
		while(true) {
			System.out.print(message + ConsoleColors.getColoredString(" (y/n): ", Y_N_COLOR));
			try {
				char input = scanner.nextLine().charAt(0);
				return input == 'y' || input == 'Y';
			} catch (Exception e) {
				System.out.println("invalid input. please try again");
			}
		}
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
				ConsoleHelper.printError("invalid date, try again.");
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
	public static double readPositiveDouble(String message){
		Double val;
		while(true){
			val = readDouble(message,null);
			if(val!=null){
				return val;
			}
			System.out.println("invalid input. please try again");
		}
	}
	public static Double readDouble(String message, Double errorValue) {
		System.out.print(message);
		try {
			return scanner.nextDouble();
		}
		catch (Exception e){
			return errorValue;
		}
		finally {
			scanner.nextLine(); //TODO doc: nextDouble doesn't read the following new-line character, so the first nextLine (which returns the rest of the current line) will always return an empty string.
		}
	}
	public static int readIntInRange(String message, int min, int max) {
		int input;
		while(true) {
			input = readInt(message,min-1);
			if(input>=min && input<=max)
				return input;
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
			scanner.nextLine(); //TODO doc: nextInt doesn't read the following new-line character, so the first nextLine (which returns the rest of the current line) will always return an empty string.
		}
	}

	public static String getPaddedString(String inputString, int length) {
		StringBuilder paddedString = new StringBuilder(inputString);
		int spacesToAdd = length - inputString.length();
        paddedString.append(" ".repeat(Math.max(0, spacesToAdd)));//IDE propossed this solution (repeat)
		return paddedString.toString();
	}

	public static void printInColor(String message, String color) {
		System.out.print(color+message+ConsoleColors.RESET);
	}

	public static void printError(String message){
		printInColor(message+"\n",ERROR_COLOR);
	}
	public static void printWarning(String message){
		printInColor(message+"\n",WARNING_COLOR);
	}
	public static void printSuccess(String message){
		printInColor(message+"\n",SUCCESS_COLOR);
	}

	public static void printHint(String message) {
		printInColor(message+"\n",HINT_COLOR);
	}
}


