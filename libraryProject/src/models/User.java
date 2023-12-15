package models;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

import helpers.DateHelper;

public class User extends Model {
	private String id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private UserType type;// member, employee, admin
	// TODO: add to doc: used an enum for userType instead of different classes
	// extending the User Class for easier database manipulation
	private Date registered;
	private String gender;
	private String email;
	private String company;
	private String phone;
	private String address;
	private Date subscriptionExpirationDate;
	private String password;
	private Preferences preferences;

	public String getShortDisplay(){
		return firstName + " " + lastName;
	}
	
	public User(String firstName, String lastName, Date birthDate, UserType type, Date registered, String gender,
			String email, String company, String phone, String address, Date subscriptionExpirationDate, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.type = type;
		this.registered = registered;
		this.gender = gender;
		this.email = email;
		this.company = company;
		this.phone = phone;
		this.address = address;
		this.subscriptionExpirationDate = subscriptionExpirationDate;
		this.password=password;
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public UserType getType() {
		return type;
	}

	/*public void setType(UserType type) {
		this.type = type;
	}*/ //TODO: to remove all setters

	public Date getRegistered() {
		return registered;
	}

	public void setRegistered(Date registered) {
		this.registered = registered;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getSubscriptionExpirationDate() {
		return subscriptionExpirationDate;
	}

	public void setSubscriptionExpirationDate(Date subscriptionExpirationDate) {
		this.subscriptionExpirationDate = subscriptionExpirationDate;
	}

	public String getCryptedPassword() {
		return password;// TODO: implement a real encryption algorithm here (not done to facilitate
						// testing and connect to users easily)
	}

	public boolean isSubscribed() {
		return subscriptionExpirationDate!=null && subscriptionExpirationDate.compareTo(DateHelper.getTodayStartOfDay())>=0;
	}
	public Preferences getPreferences() {
		return preferences;
	}

	public enum UserType {
		@SerializedName("M")
		MEMBER, 
		@SerializedName("E")
		EMPLOYEE, 
		@SerializedName("A")
		ADMIN
	}

	public static class Preferences {
		private String dateFormat;

		public String getDateFormat() {
			return dateFormat;
		}
	}

}
