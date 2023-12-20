package ui;

import Managers.PageManager;
import authentification.AuthenticationSystem;
import helpers.Action;
import helpers.ActionMenu;
import models.Book;
import models.Booking;
import models.Event;
import services.BookingsService;
import services.BooksService;
import services.EventsService;

public class EventsListPage extends BackOnlyPage {

	private boolean showHistory = false;

	public EventsListPage() {
	}

	public EventsListPage (boolean showHistory) {
		this.showHistory = showHistory;
	}

	@Override
	void printContent() {
		Event[] events;
		if (showHistory) {
			events = EventsService.getAllEvents();
		} else {
			events = EventsService.getUpcomingEvents();
		}
		if(events.length == 0)
			System.out.println("---no events---");
		else {
			Action[] eventsActions = mapActions(events);	
			new ActionMenu(eventsActions , ActionMenu.PREV_PAGE_ACTION, "enter the index of the event to see details").execute();

		}

	}

	@Override
	protected String getTitle() {
		return showHistory ? "All Our Events (past & future)" : "Upcoming Events";
	}
	
	private Action[] mapActions(Event[] events) {
		Action[] actions = new Action[events.length];
		for (int i = 0; i < events.length; i++) {
			Event e = events[i];
			actions[i] = new Action() {

				@Override
				public String getDescription() {
					return e.getShortDisplay();
				}

				@Override
				public void execute() {
					PageManager.callPage(new EventDetailsPage(e));
				}
			};
		}
		return actions;
	}


}
