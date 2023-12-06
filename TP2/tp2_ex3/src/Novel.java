class Novel extends Book {
    public Novel(String title, String author, int yearOfPublication) {
        super(title, author, yearOfPublication);
    }

    public void displayInformation() {
        System.out.println("Novel: " + getTitle() + " by " + getAuthor() + " (" + getYearOfPublication() + ")");
    }
}