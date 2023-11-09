// Magazine class inheriting from MediaItem
public class Magazine extends MediaItem {
    private int issueNumber;
    private String month;

    public Magazine(String title, int issueNumber, String month, boolean NSCCollection) {
        super(title, NSCCollection);
        this.issueNumber = issueNumber;
        this.month = month;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public String getMonth() {
        return month;
    }
}

