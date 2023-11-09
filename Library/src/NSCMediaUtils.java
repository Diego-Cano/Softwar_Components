// Utility class with generic methods
public class NSCMediaUtils {
    public static <T extends MediaItem> void printMediaDetails(T media) {
        System.out.println("Title: " + media.getTitle());
        System.out.println("NSC Collection: " + media.isNSCCollection());

        System.out.println();
    }
}
