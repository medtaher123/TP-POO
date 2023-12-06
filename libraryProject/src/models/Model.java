package models;

import com.google.gson.Gson;

public abstract class Model {

	public String toString() {
		return new Gson().toJson(this);
	}
	public String getShortDisplay(){
		return toString();
	}
	public String getLongDisplay() {
		return toString();
	}
}
