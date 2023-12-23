package Managers;

import models.Settings;
import services.SettingsService;

public class SettingsManager {
	
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
