package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import models.Event;


public class EventsService extends DatabaseService {

	public static Event[] getAllEvents() {
		Gson gson = new Gson();
		return gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.EVENTS_API_URL), Event[].class);
	}

	public static Event[] getAllEvents(String queryParams) {
		Gson gson = new Gson();
		return gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.EVENTS_API_URL + "?" + queryParams), Event[].class);
	}
	public static Event[] getUpcomingEvents() {
		return filter(getAllEvents(), event -> !event.hasOccurred());
	}
	
	
	public static Event getEventById(String id) {
		Gson gson = new Gson();
		return gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.EVENTS_API_URL + "/" + id), Event.class);
	}
	
	public static Event UpdateEvent(Event event) {
		Gson gson = new Gson();
		return gson.fromJson(DatabaseService.sendHttpRequest("PUT", DatabaseService.EVENTS_API_URL + "/" + event.getId(),event), Event.class);
	}
	
	public static Event addEvent(Event newEvent) {
		return new Gson().fromJson(DatabaseService.sendHttpRequest("POST", DatabaseService.EVENTS_API_URL, newEvent), Event.class);
	}
	
	public static boolean DeleteEventById(String id) {
		DatabaseService.sendHttpRequest("DELETE", DatabaseService.USERS_API_URL + "/" + id);
		return true; 
	}
	
	
}
