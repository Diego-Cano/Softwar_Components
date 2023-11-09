// DVD class inheriting from MediaItem
public class DVD extends MediaItem {
    private int duration;
    private String genre;

    public DVD(String title, int duration, String genre, boolean NSCCollection) {
        super(title, NSCCollection);
        this.duration = duration;
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public String getGenre() {
        return genre;
    }
}

