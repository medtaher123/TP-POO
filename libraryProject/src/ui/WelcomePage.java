package ui;

import java.util.Arrays;
import java.util.Scanner;

import authentification.AuthenticationSystem;
import helpers.ConsoleHelper;
import ui.Action;

public class WelcomePage extends Page {
	//TODO: add to doc: explain why the use of Action ActionMenu classes
	
	@Override
	protected String getTitle() {
		return "Welcome " + AuthenticationSystem.getActiveUser().getFirstName();
	}
	
	Action[] generalActions = { 
		new Action() {
			@Override
			public String getDescription() {
				return "books";
			}
	
			@Override
			public void execute() {
				PageManager.callPage(new bookListPage());
			}
		},
		new Action() {
			@Override
			public String getDescription() {
				return "search book";
			}
	
			@Override
			public void execute() {
				PageManager.callPage(new bookListPage(ConsoleHelper.input("search")));
			}
		},
		new Action() {
			@Override
			public String getDescription() {
				return "Manage Account";
			}
	
			@Override
			public void execute() {
				PageManager.callPage(new ManageAccountPage());
			}
		},
	};
	
	Action[] memberActions = {
		new Action() {
			@Override
			public String getDescription() {
				return "Booking History";
			}

			@Override
			public void execute() {
				PageManager.callPage(new UserBookingListPage(true));
			}
		},
		new Action() {
			@Override
			public String getDescription() {
				return "Active Bookings";
			}

			@Override
			public void execute() {
				PageManager.callPage(new UserBookingListPage(false));
			}
		}
	};
	Action[] employeeActions = {
			new Action() {
				@Override
				public String getDescription() {
					return "New member";
				}

				@Override
				public void execute() {
					PageManager.callPage(new newMemberPage());
				}
			},
			new Action() {
				@Override
				public String getDescription() {
					return "Subscription renewal";
				}

				@Override
				public void execute() {
					PageManager.callPage(new SubscriptionRenewalPage());
				}
			},
			new Action() {
				@Override
				public String getDescription() {
					return "register booking";
				}

				@Override
				public void execute() {
					PageManager.callPage(new RegisterBookingPage());
				}
			},
			new Action() {
				@Override
				public String getDescription() {
					return "register book return";
				}

				@Override
				public void execute() {
					PageManager.callPage(new BookingReturnPage());
					//TODO: book return Page ;
				}
			},
			new Action() {
				@Override
				public String getDescription() {
					return "consult Book bookings history";
				}

				@Override
				public void execute() {
					PageManager.callPage(new NotFoundPage());
					//TODO: Book history Page ;
				}
			}
	};
	Action[] adminActions = {
			new Action() {
				@Override
				public String getDescription() {
					return "manage settings";
				}

				@Override
				public void execute() {
					PageManager.callPage(new NotFoundPage());
					//TODO: manage settings Page ;
				}
			},
			new Action() {
				@Override
				public String getDescription() {
					return "account recovery";
				}

				@Override
				public void execute() {
					PageManager.callPage(new NotFoundPage());
					//TODO: account recovery Page ;
				}
			},
			new Action() {
				@Override
				public String getDescription() {
					return "new account";
				}

				@Override
				public void execute() {
					PageManager.callPage(new NotFoundPage());
					//TODO: new account Page ;
				}
			},
			new Action() {
				@Override
				public String getDescription() {
					return "delete account";
				}

				@Override
				public void execute() {
					PageManager.callPage(new NotFoundPage());
					//TODO: delete account Page ;
				}
			}
	};
	
	Action closingAction = new Action() {
		
		@Override
		public String getDescription() {
			return "logOut";
		}

		@Override
		public void execute() {	
			logout();
		}
		
	};
	
	public void printContent() {
		System.out.println("what can I do for you today\n");
		
		Action[] userSpecificActions=null;
		switch(AuthenticationSystem.getActiveUser().getType()) {
			case MEMBER:
				userSpecificActions = memberActions;
	            break;
	        case EMPLOYEE:
	        	userSpecificActions = employeeActions;
	            break;
	        case ADMIN:
	        	userSpecificActions = adminActions;
	            break;
	        default:
	            throw new RuntimeException("Unknown user type");
		}
		
		Action[] actions = concatenateArrays(generalActions,userSpecificActions); 
		new ActionMenu(actions,closingAction).execute();

	}
	
	public static Action[] concatenateArrays(Action[] arr1, Action[] arr2) {
        int length1 = arr1.length;
        int length2 = arr2.length;
        
        Action[] result = Arrays.copyOf(arr1, length1 + length2);
        System.arraycopy(arr2, 0, result, length1, length2);
        
        return result;
    }
}
