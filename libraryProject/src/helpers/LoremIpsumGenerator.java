package helpers;
import java.util.Random;

import models.Book;
import models.BookCopy;
import models.User;
import models.User.Preferences;
import services.BookCopiesService;
import services.BooksService;
import services.UsersService;

public class LoremIpsumGenerator {

	//this class was used to generate the descriptions of the books
    private static final String[] LOREM_IPSUM_WORDS = {
            "Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit", "sed", "do", "eiusmod",
            "tempor", "incididunt", "ut", "labore", "et", "dolore", "magna", "aliqua", "Ut", "enim", "ad", "minim",
            "veniam", "quis", "nostrud", "exercitation", "ullamco", "laboris", "nisi", "ut", "aliquip", "ex", "ea",
            "commodo", "consequat", "Duis", "aute", "irure", "dolor", "in", "reprehenderit", "in", "voluptate", "velit",
            "esse", "cillum", "dolore", "eu", "fugiat", "nulla", "pariatur", "Excepteur", "sint", "occaecat", "cupidatat",
            "non", "proident", "sunt", "in", "culpa", "qui", "officia", "deserunt", "mollit", "anim", "id", "est", "laborum"
    };

    public String generateLoremIpsum() {
        StringBuilder loremIpsumText = new StringBuilder();
        Random random = new Random();

        int paragraphLength = random.nextInt(40) + 15;
        for (int i = 0; i < paragraphLength; i++) {
            int randomWordIndex = random.nextInt(LOREM_IPSUM_WORDS.length);
            loremIpsumText.append(LOREM_IPSUM_WORDS[randomWordIndex]);

            if (i < paragraphLength - 1) {
                if (random.nextInt(10)<1) {
                    loremIpsumText.append(", ");
                } else {
                    loremIpsumText.append(" ");
                }
            }
        }

        loremIpsumText.setCharAt(0, Character.toUpperCase(loremIpsumText.charAt(0)));

        loremIpsumText.append("."); 
        return loremIpsumText.toString();
    }

    public static void main0(String[] args) {
        LoremIpsumGenerator generator = new LoremIpsumGenerator();
        String loremIpsum;
        
        Book[] books = BooksService.getAllBooks();
        for(Book book : books) {
        	try {
        	    Thread.sleep(300);
        	    loremIpsum = generator.generateLoremIpsum();
        	    System.out.println(book.getShortDisplay());
        	    book.setDescription(loremIpsum);
        	    BooksService.UpdateBook(book);
        	} catch (InterruptedException e) {
        	    e.printStackTrace();
        	}
        }
    	
    	/*BookCopy[] books = BookCopiesService.getAllBookCopies();
        for(BookCopy book : books) {
        	try {
        	    Thread.sleep(100);
        	    System.out.println(book.getShortDisplay());
        	    BookCopiesService.UpdateBookCopy(book);
        	} catch (InterruptedException e) {
        	    e.printStackTrace();
        	}
        }*/
		/*
    	String[] formats = {"MMM dd YYYY",null, "MM-dd-yyyy", "dd-MM-yyyy" , "MM/dd/yyyy",null,"EEEEE dd MMMMM yyyy", "dd/MM/yyyy",null,null};
    	int i =0;
        User[] users = UsersService.getAllUsers();
        for(User user : users) {
        	try {
        	    Thread.sleep(200);
        	    System.out.println(user.getShortDisplay());
        	    Preferences preferences = new Preferences();
        	    preferences.setDateFormat(formats[i%formats.length]);
        	    user.setPreferences(preferences);
        	    UsersService.UpdateUser(user);
        	    System.out.println(user.getPreferences().getDateFormat());
        	} catch (InterruptedException e) {
        	    e.printStackTrace();
        	}
        	i++;
        }
        */
        
    	
    }
}
