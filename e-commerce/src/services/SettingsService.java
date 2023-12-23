package services;

import adapters.GsonInstance;
import models.Settings;

public class SettingsService extends DatabaseService {

	public static Settings getSettings() {
		return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("GET", SETTINGS_API_URL),
				Settings.class);
	}
	
	//TODO: add update method
	
}
