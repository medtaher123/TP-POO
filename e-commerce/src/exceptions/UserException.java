package exceptions;

import helpers.ConsoleHelper;

public class UserException extends Exception{
    public UserException(String message) {
        super(message);
    }


    public void printToUser() {
        ConsoleHelper.printError(getMessage());
    }
}
