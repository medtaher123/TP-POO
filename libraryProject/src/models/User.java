package models;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class User extends Model{
	private String id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private UserType type;// member, employee, admin
	private Date registered;
	private String gender;
	private String email;
	private String company;
	private String phone;
	private String address;
	private Date lastSubscriptionDate;
	private String password;

	public User(String firstName, String lastName, Date birthDate, UserType type, Date registered,
			String gender, String email, String company, String phone, String address, Date lastSubscriptionDate) {
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
		this.lastSubscriptionDate = lastSubscriptionDate;
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

	public void setType(UserType type) {
		this.type = type;
	}

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

	public Date getLastSubscriptionDate() {
		return lastSubscriptionDate;
	}

	public void setLastSubscriptionDate(Date lastSubscriptionDate) {
		this.lastSubscriptionDate = lastSubscriptionDate;
	}

	public String getCryptedPassword() {
		return password;
	}
	
	public enum UserType {
		@SerializedName("M")
	    MEMBER,
	    @SerializedName("E")
	    EMPLOYEE,
	    @SerializedName("A")
	    ADMIN
	}

}
