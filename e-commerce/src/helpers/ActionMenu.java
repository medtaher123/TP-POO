package helpers;

import Managers.PageManager;


public class ActionMenu {
	private static final String MENU_INDEX_COLOR = ConsoleColors.DEFAULT_DIM;
	private Action[] actions;
	private Action closingAction;
	private String message;
	//private Scanner scanner;

	public final static Action PREV_PAGE_ACTION = new Action() {

		@Override
		public String getDescription() {
			return "Go Back";
		}

		@Override
		public Object execute() {
			PageManager.prevPage();
			return null;
		}
	};
	public final static Action LOGOUT_ACTION = new Action() {

		@Override
		public String getDescription() {
			return "logOut";
		}

		@Override
		public Object execute() {
			PageManager.restartApp();
            return null;
        }

	};
	public final static Action BACK_TO_HOMEPAGE_ACTION = new Action(){

		@Override
		public String getDescription() {
			return "Go back to homepage";
		}

		@Override
		public Object execute() {
			PageManager.BackTOHomepage();
			return null;
		}
	};
	public static final Action LOGIN_PAGE = new Action() {
		@Override
		public String getDescription() {
			return "Login";
		}

		@Override
		public Object execute() {
			PageManager.restartApp();
			return null;
		}
	};

	public ActionMenu(Action[] actions, Action closingAction, String message) {
		this.actions = actions;
		this.closingAction = closingAction;
		this.message = message;
	}

	public ActionMenu(Action[] actions, Action closingAction) {
		this(actions, closingAction, "Enter the index of the action to perform: ");
	}

	public ActionMenu(Action[] actions) {
		this(actions, null);
	}

	private void displayActions() {
		// System.out.println("Available Actions:");
		int indexLength = 1;
		if (actions != null) {
			indexLength = ((actions.length+1)+"").length();
			for (int i = 0; i < actions.length; i++) {
				System.out.println(ConsoleColors.getColoredString(ConsoleHelper.getPaddedString((i + 1)+"",indexLength) + ": ", MENU_INDEX_COLOR) + actions[i].getDescription());
			}
		}
		if (closingAction != null)
			System.out.println("\n"+ConsoleColors.getColoredString(ConsoleHelper.getPaddedString("0",indexLength) +": ", MENU_INDEX_COLOR) + closingAction.getDescription() + "\n");
	}

	private Object executeAction(int index) {
		if (choiceIsValid(index)) {
			if (index == 0)
				return closingAction.execute();
			else
				return actions[index - 1].execute();
		}
		System.out.println("Invalid choice. Please select a valid index.");
		return null;
	}

	private boolean choiceIsValid(int index) {
		return (index > 0 && index < actions.length + 1) || (closingAction != null && index == 0);
	}

	private int getUserChoice() {
		System.out.print(message);
		return ConsoleHelper.readInt(-1);
	}


	public Pair<Integer, Object> execute() {
		displayActions();
		int choice = getUserChoice();

		while (!choiceIsValid(choice)) {
			System.out.println("Invalid choice. Please select a valid index.");
			displayActions();
			choice = getUserChoice();
		}
		return new Pair<>(choice, executeAction(choice));
	}

}
