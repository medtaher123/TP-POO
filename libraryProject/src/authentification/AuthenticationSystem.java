package authentification;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import authentification.AuthenticationSystem.AuthenticationException;
import models.User;
import services.UsersService;

public class AuthenticationSystem {

	private static User activeUser=null;


    public static boolean login(String email, String password) throws AuthenticationException {
    	User loginUser = UsersService.getUserByEmail(email); 
    	if(loginUser==null)
    		throw new InvalidEmailException("invalid email!");
    	if(!loginUser.getCryptedPassword().equals(crypt(password))){
    		throw new InvalidPasswordException("invalid password!");
    	}
    	activeUser = loginUser;
        return true;
    }
    
    public static void logout() {
    	activeUser=null;
    }
    
    public static User getActiveUser() {
    	return activeUser;
    }
    
    public static boolean isLoggedIn(){
    	return activeUser!=null;
    }
    
    private static Object crypt(String password) {
		return password; 
		// TODO: implement a real encryption algorithm here (not done to facilitate
		// testing and connect to users easily)
	}
    
	public static class AuthenticationException extends Exception {
	    public AuthenticationException(String message) {
	        super(message);
	    }
	}
	
    static class InvalidEmailException extends AuthenticationException {
        public InvalidEmailException(String message) {
            super(message);
        }
    }

    static class InvalidPasswordException extends AuthenticationException {
        public InvalidPasswordException(String message) {
            super(message);
        }
    }
}