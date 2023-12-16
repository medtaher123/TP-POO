package ui;

public class NotFoundPage extends BackOnlyPage {

	@Override
	void printContent() {
		System.out.println("The feature you are looking for is not yet implemented.\n"
				+ "It may have been added to a newer version");

	}

	@Override
	protected String getTitle() {
		return "Page not found";
	}

}
