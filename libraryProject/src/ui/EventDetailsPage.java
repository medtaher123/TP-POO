package ui;

import helpers.DateHelper;
import models.Book;
import models.Event;

public class EventDetailsPage extends BackOnlyPage {

	private Event event;
	
	@Override
	protected String getTitle() {
		return event.getTitle();
	}

	public EventDetailsPage(Event event) {
		super();
		this.event = event;
	}
	
	
	@Override
	public void printContent() {
	    //System.out.println("Event ID: " + event.getId());
	    System.out.println("Date: " + DateHelper.format(event.getDate()));
	    System.out.println("About: " + event.getAbout());
	    System.out.println("Organizer: " + event.getOrganizer());
	    System.out.println("Manager: " + event.getManager());
	    System.out.println("Email: " + event.getEmail());
	    System.out.println("Phone: " + event.getPhone());
	}
}
