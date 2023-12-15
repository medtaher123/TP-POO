import java.util.Date;

import com.google.gson.Gson;

import models.Booking;
import models.User;
import services.BookCopiesService;
import services.BookingsService;
import services.DatabaseService;
import services.UsersService;
import ui.LoginPage;
import ui.PageManager;

public class MainApplication {
	public static void main(String[] args) {
		// System.out.println(Database.fetchDataFromServer("GET","http://localhost:3000/books"));		
		//User us = new User("halloo", "bruder", new Date(), User.Type.MEMBER, new Date(), null, null, null, null, null, new Date());
		//User us = UsersService.getUserById("hAJFFb7");
		//us.setLastName("MTBH");
		//System.out.println(new Gson().toJson(us));
		//UsersService.UpdateUser(us);
		//UsersService.addUser(us);
		//System.out.println(new Gson().toJson(UsersService.addUser(us)));
		//User us = UsersService.getUserById("65639da50170361ff27bda35");
		//System.out.println(new Gson().toJson(us));
		//System.out.println("\nccc\n" + UsersService.getAllUsers("firstName=Shelly") + "\nss\n");
		//LoginPage loginPage = new LoginPage();
		//loginPage.display();
		//Booking[] b =  BookingsService.getUsersActiveBookings(null);
		PageManager.restartApp();
	
		
	}
}
