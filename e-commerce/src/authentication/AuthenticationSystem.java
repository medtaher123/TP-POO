package authentication;

import models.User;
import services.UsersService;

public class AuthenticationSystem {

	private static User activeUser=null;


    public static boolean login(String email, String password) throws AuthenticationException {
        activeUser=null;
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
    
    public static String crypt(String password) {
		return password; 
		// TODO: implement a real encryption algorithm here (not done to facilitate testing and connect to users easily)
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