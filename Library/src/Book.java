// Book class inheriting from MediaItem
public class Book extends MediaItem {
    private String author;
    private String ISBN;

    public Book(String title, String author, String ISBN, boolean NSCCollection) {
        super(title, NSCCollection);
        this.author = author;
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }
}
