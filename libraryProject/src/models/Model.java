package models;

import com.google.gson.Gson;

public abstract class Model {

	// chose to define Models as classes that don't have a logic
	// on their own. They are structures that represent entities from the database.
	// Any logic or treatment on these objects is implemented in other classes like
	// Managers. Most of the attributes have only getters since every modification
	// has to pass through the database first
	public String toString() {
		return new Gson().toJson(this);
	}

	public String getShortDisplay() {
		return toString();
	}

	public String getLongDisplay() {
		return toString();
	}
}
