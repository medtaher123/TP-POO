class Teacher implements LibraryUser {
    private LibraryCard libraryCard;

    public Teacher(LibraryCard libraryCard) {
        this.libraryCard = libraryCard;
    }

    public Teacher(int cardId) {
    	this(new LibraryCard(cardId));
    }
    
    @Override
    public void borrowBook(Book book) {
        System.out.println("Teacher with Library Card ID " + libraryCard.getCardId() + " is borrowing " + book.getTitle());
    }

    @Override
    public void returnBook(Book book) {
        System.out.println("Teacher with Library Card ID " + libraryCard.getCardId() + " is returning " + book.getTitle());
    }
}