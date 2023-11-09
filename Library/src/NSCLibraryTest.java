public class NSCLibraryTest {
    public static void main(String[] args) {
        // Instantiate containers
        NSCLibraryMediaContainer<Book> bookContainer = new NSCLibraryMediaContainer<>();
        NSCLibraryMediaContainer<DVD> dvdContainer = new NSCLibraryMediaContainer<>();
        NSCLibraryMediaContainer<Magazine> magazineContainer = new NSCLibraryMediaContainer<>();

        // Sample media items
        Book book1 = new Book("The Java Programming Language", "James Gosling", "978-0321349804", true);
        Book book2 = new Book("Clean Code", "Robert C. Martin", "978-0132350884", false);

        DVD dvd1 = new DVD("Inception", 148, "Science Fiction", true);
        DVD dvd2 = new DVD("The Shawshank Redemption", 142, "Drama", false);

        Magazine magazine1 = new Magazine("National Geographic", 255, "November", true);
        Magazine magazine2 = new Magazine("Time", 123, "October", false);

        // Adding media items to containers
        bookContainer.add(book1);
        bookContainer.add(book2);

        dvdContainer.add(dvd1);
        dvdContainer.add(dvd2);

        magazineContainer.add(magazine1);
        magazineContainer.add(magazine2);

        // Displaying media items from containers
        System.out.println("Book Container:");
        bookContainer.display();

        System.out.println("DVD Container:");
        dvdContainer.display();

        System.out.println("Magazine Container:");
        magazineContainer.display();

        // Using NSCMediaUtils to print details of individual media items
        System.out.println("\nDetails of Individual Media Items:");

        NSCMediaUtils.printMediaDetails(book1);
        NSCMediaUtils.printMediaDetails(dvd2);
        NSCMediaUtils.printMediaDetails(magazine1);
    }
}

