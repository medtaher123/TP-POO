package models;

import authentication.AuthenticationSystem;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class User extends Model {
	private String firstName;
	private String lastName;
	private Date birthDate;
	private UserType type;// customer, admin
	// used an enum for userType for easier database manipulation. (instead of defining different classes for every userType
	// extending the User Class )
	private Date registrationDate;
	private String gender;
	private String email;
	private String company;
	private String phone;
	private String address;
	private String password;
	private Preferences preferences;

	public String getShortDisplay(){
		return firstName + " " + lastName;
	}
	
	public User(String firstName, String lastName, Date birthDate, UserType type, Date registrationDate, String gender,
			String email, String company, String phone, String address, String notCryptedPassword) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.type = type;
		this.registrationDate = registrationDate;
		this.gender = gender;
		this.email = email;
		this.company = company;
		this.phone = phone;
		this.address = address;
		this.password= AuthenticationSystem.crypt(notCryptedPassword);
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
	}*/

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

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCryptedPassword() { //the password in the database is crypted
		return password;
	}
	public Preferences getPreferences() {
		if(preferences==null){
			preferences=new Preferences();
		}
		return preferences;
	}

	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}


	public enum UserType {
		@SerializedName("C")
		CUSTOMER,
		@SerializedName("A")
		ADMIN
	}

	public static class Preferences {
		private String dateFormat;

		public String getDateFormat() {
			return dateFormat;
		}

		public void setDateFormat(String dateFormat) {
			this.dateFormat = dateFormat;
		}
		
	}

}
