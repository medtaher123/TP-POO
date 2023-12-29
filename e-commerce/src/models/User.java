package models;

import adapters.Proxy;
import authentication.AuthenticationSystem;
import com.google.gson.annotations.SerializedName;
import services.ProductsService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class User extends Model {
	private String firstName;
	private String lastName;
	private Date birthDate;
	private UserType type;// customer, admin
	//TODO doc: used an enum for userType for easier database manipulation. (instead of defining different classes for every userType
	// extending the User Class )
	private Date registrationDate;
	private String gender;
	private String email;
	private String company;
	private String phone;
	private String address;
	private String password;
	private Preferences preferences;

	private List<Proxy<Product>> wishList;

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
		setEmail(email);
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
		this.email = email.toLowerCase(Locale.ROOT); //TODO doc: email is case insensitive
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

	public void setPassword(String notCryptedPassword) {
		this.password= AuthenticationSystem.crypt(notCryptedPassword);
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

	public List<Product> getUnwrappedWishList() {
		if(wishList==null)
			wishList = new ArrayList<>();
		return Proxy.unwrap(wishList, ProductsService::getProductById);
	}
	public List<Proxy<Product>> getWishList() {
		if(wishList==null)
			wishList = new ArrayList<>();
		return wishList;
	}
	public void addToWishlist(Product product){
		if(wishList==null)
			wishList = new ArrayList<>();
		wishList.add(new Proxy<>(product.getId()));
	}
	public boolean isInWishlist(Product product){
		if(wishList==null)
			wishList = new ArrayList<>();
		return wishList.contains(new Proxy<>(product.getId()));
	}
	public void removeFromWishList(Product product){
		if(wishList==null)
			wishList = new ArrayList<>();
		wishList.remove(new Proxy<>(product.getId()));
	}

	public void removeFromWishlist(int index) {
		if(wishList==null || wishList.size()<=index)
			throw new IllegalArgumentException("Invalid index");

		wishList.remove(index);
	}
	public void removeFromWishlist(Product product) {
		if(wishList==null)
			wishList = new ArrayList<>();
		wishList.remove(new Proxy<>(product.getId()));
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
