package ui;

import authentification.AuthenticationSystem;

public abstract class Page {
    public abstract void display();

	public void reset() {} 
	public void onDestroy() {}

	protected void logout() {
		AuthenticationSystem.logout();
		PageManager.restartApp();
	}
	protected void close() {
		PageManager.prevPage();
	}
}
