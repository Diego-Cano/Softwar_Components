// MediaItem class (base class for Book, DVD, and Magazine)
public class MediaItem {
    private String title;
    private boolean NSCCollection;

    public MediaItem(String title, boolean NSCCollection) {
        this.title = title;
        this.NSCCollection = NSCCollection;
    }

    public String getTitle() {
        return title;
    }

    public boolean isNSCCollection() {
        return NSCCollection;
    }
}

