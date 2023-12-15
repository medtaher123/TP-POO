package services;

import com.google.gson.Gson;

import models.Settings;

public class SettingsService extends DatabaseService {

	public static Settings getSettings() {
		Gson gson = new Gson();
		
		return gson.fromJson(DatabaseService.sendHttpRequest("GET", SETTINGS_API_URL),
				Settings.class);
	}
	
	//TODO: add update method
	
}
