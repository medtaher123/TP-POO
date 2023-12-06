import java.util.ArrayList;
import java.util.List;

class Library {
    private List<Book> inventory = new ArrayList<>();

    public void addBook(Book book) {
        inventory.add(book);
    }

    public void removeBook(Book book) {
        inventory.remove(book);
    }

    public void listBooks() {
        System.out.println("Books in the library:");
        for (Book book : inventory) {
            System.out.println(book.getTitle());
        }
    }
}