package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import models.User;

public class UsersService extends DatabaseService {

	public static User[] getAllUsers() {
		Gson gson = new Gson();
		return gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.USERS_API_URL), User[].class);
	}

	public static User[] getAllUsers(String queryParams) {
		Gson gson = new Gson();
		return gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.USERS_API_URL + "?" + queryParams),
				User[].class);
	}
	
	public static User getUserById(String id) {
		Gson gson = new Gson();
		return gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.USERS_API_URL + "/" + id), User.class);
	}
	
	public static User UpdateUser(User user) {
		Gson gson = new Gson();
		return gson.fromJson(DatabaseService.sendHttpRequest("PUT", DatabaseService.USERS_API_URL + "/" + user.getId(),user), User.class);
	}
	
	public static User getUserByEmail(String email) {
		User[] t = getAllUsers("email="+email);
		return t.length>0? t[0]:null;
	}
	public static User addUser(User newUser) {
		return new Gson().fromJson(DatabaseService.sendHttpRequest("POST", DatabaseService.USERS_API_URL, newUser), User.class);/*gson.toJson(newUser))*/
	}
	
	public static boolean DeleteUserById(String id) {
		DatabaseService.sendHttpRequest("DELETE", DatabaseService.USERS_API_URL + "/" + id);
		return true; //TODO: return status instead
	}
	
	public static boolean EmailIsAvailable(String email) {
		return getUserByEmail(email)==null;
	}
	
	
	
	
}
