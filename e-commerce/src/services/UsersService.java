package services;

import adapters.GsonInstance;

import models.User;

public class UsersService extends DatabaseService {

	public static User[] getAllUsers() {
		return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.USERS_API_URL), User[].class);
	}

	public static User[] getAllUsers(String queryParams) {
		return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.USERS_API_URL + "?" + queryParams),
				User[].class);
	}
	
	public static User getUserById(String id) {
		return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.USERS_API_URL + "/" + id), User.class);
	}
	
	public static User updateUser(User user) {
		return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("PUT", DatabaseService.USERS_API_URL + "/" + user.getId(),user), User.class);
	}
	
	public static User getUserByEmail(String email) {
		User[] t = getAllUsers("email="+email);
		return t.length>0? t[0]:null;
	}
	public static User addUser(User newUser) {
		return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("POST", DatabaseService.USERS_API_URL, newUser), User.class);/*gson.toJson(newUser))*/
	}
	
	public static boolean DeleteUserById(String id) {
		DatabaseService.sendHttpRequest("DELETE", DatabaseService.USERS_API_URL + "/" + id);
		return true; //TODO: return status instead
	}
	
	
	
	
	
	
}
