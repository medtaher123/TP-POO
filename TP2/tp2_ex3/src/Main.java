
public class Main {
    public static void main(String[] args) {
        
        Book novel = new Novel("Novel A", "Writer A", 2020);
        Book textbook = new Textbook("TextBook B", "TextBooker B", 2019);

        
        Library library = new Library();

        
        library.addBook(novel);
        library.addBook(textbook);

        
        library.listBooks();

        
        LibraryCard studentCard = new LibraryCard(123);
        LibraryCard teacherCard = new LibraryCard(456);

        
        LibraryUser student = new Student(studentCard);
        LibraryUser teacher = new Teacher(teacherCard);

        
        student.borrowBook(novel);
        teacher.borrowBook(textbook);

        student.returnBook(novel);
        teacher.returnBook(textbook);
    }
}
