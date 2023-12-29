package Managers;

import models.Settings;
import services.SettingsService;

public class SettingsManager {
	//TODO doc: this class manages the settings of the application. It's a singleton class that loads the settings from the database when the application starts and reloads them when the admin changes them.
	private static Settings settings=null;
	public static Settings getSettings() {
		if(settings==null)
			reloadSettings();
		return settings;
	}
	public static void reloadSettings() {
		settings = SettingsService.getSettings();
	}
	
	
}
