// Utility class with generic methods
public class NSCMediaUtils {
    public static <T extends MediaItem> void printMediaDetails(T media) {
        System.out.println("Title: " + media.getTitle());
        System.out.println("NSC Collection: " + media.isNSCCollection());

        if (media instanceof Book) {
            Book book = (Book) media;
            System.out.println("Author: " + book.getAuthor());
            System.out.println("ISBN: " + book.getISBN());
        } else if (media instanceof DVD) {
            DVD dvd = (DVD) media;
            System.out.println("Duration: " + dvd.getDuration() + " minutes");
            System.out.println("Genre: " + dvd.getGenre());
        } else if (media instanceof Magazine) {
            Magazine magazine = (Magazine) media;
            System.out.println("Issue Number: " + magazine.getIssueNumber());
            System.out.println("Month: " + magazine.getMonth());
        }

        System.out.println();
    }
}

