package models;

import adapters.GsonInstance;

import java.util.Objects;

public abstract class Model {

	// TODO doc: chose to define Models as classes that don't have a logic
	//  on their own. They are structures that represent entities from the database.
	//  Any logic or treatment on these objects is implemented in other classes like
	//  Managers. Some attributes have only getters since every modification
	//  has to pass through the database first
	private String id;

	public String toString() {
		return GsonInstance.gson.toJson(this);
	}

	public String getShortDisplay() {
		return toString();
	}

	public String getLongDisplay() {
		return toString();
	}

	public String getId() {//id attribute only have getter (no setter) because it is automatically generated by the json-server. No need to set it manually.
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Model model = (Model) o;
		return Objects.equals(id, model.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
