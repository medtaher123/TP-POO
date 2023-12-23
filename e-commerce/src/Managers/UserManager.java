package Managers;

import authentication.AuthenticationSystem;
import models.User;
import models.User.UserType;
import services.UsersService;

import java.util.Date;

//Managers are classes that handle the logic behind Models, since Models are only structures 
public class UserManager {

	public static User createNewUser(String firstName, String lastName, Date birthDate, UserType type, String gender,
			String email, String company, String phone, String address, String notCryptedPassword) throws AuthenticationSystem.AuthenticationException {

		User newUser = new User(firstName, lastName, birthDate, type, new Date(), gender, email, company, phone, address,notCryptedPassword);
		newUser = UsersService.addUser(newUser);
		if(newUser==null)return null;
		AuthenticationSystem.login(email,notCryptedPassword);
		return newUser;
    }
	public static boolean EmailIsAvailable(String email) {
		return UsersService.getUserByEmail(email)==null;
	}

	
	public static boolean deleteUser(User user) {
		return UsersService.DeleteUserById(user.getId());
	}
}
