import java.util.ArrayList;

// Generic Container class with bounded type parameters
public class NSCLibraryMediaContainer<T extends MediaItem> {
    private ArrayList<T> mediaList;

    public NSCLibraryMediaContainer() {
        this.mediaList = new ArrayList<>();
    }

    public void add(T media) {
        mediaList.add(media);
    }

    public void remove(T media) {
        mediaList.remove(media);
    }

    public void display() {
        for (T media : mediaList) {
            NSCMediaUtils.printMediaDetails(media);
        }
    }
}
