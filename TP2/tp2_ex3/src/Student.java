class Student implements LibraryUser {
    private LibraryCard libraryCard;

    public Student(LibraryCard libraryCard) {
        this.libraryCard = libraryCard;
    }
    public Student(int cardId) {
    	this(new LibraryCard(cardId));
    }
    
    @Override
    public void borrowBook(Book book) {
        System.out.println("Student with Library Card ID " + libraryCard.getCardId() + " is borrowing " + book.getTitle());
    }

    @Override
    public void returnBook(Book book) {
        System.out.println("Student with Library Card ID " + libraryCard.getCardId() + " is returning " + book.getTitle());
    }
}