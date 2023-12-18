package helpers;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
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
}


