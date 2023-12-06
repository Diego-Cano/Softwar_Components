/**
 * Song record representing information about a song.
 *
 * @param trackName     Name of the song
 * @param artistsName   Name of the artist
 * @param releasedYear  Year that the song was released
 * @param releasedMonth Month that the song was released
 * @param releasedDay   Day that the song was released
 * @author DiegoCano
 */
public record Song(String trackName, String artistsName, String releasedYear, String releasedMonth, String releasedDay, String totalNumberOfStreamsOnSpotify ) implements Comparable<Song> {

    // Constructor with preconditions
    public Song {
        // Preconditions to ensure non-null and non-empty values
        if (trackName == null || artistsName == null  || totalNumberOfStreamsOnSpotify == null) {
            throw new NullPointerException("Null values are not allowed");
        }
        if (trackName.isEmpty() || artistsName.isEmpty() || totalNumberOfStreamsOnSpotify.isEmpty()) {
            throw new IllegalArgumentException("Empty contents are not allowed");
        }
        // Add additional preconditions if needed
    }

    // Implementing compareTo method for natural ordering by track name
    @Override
    public int compareTo(Song song) {

        // Updated variables. Error FIXED

        int parsedThis = Integer.parseInt(this.releasedYear);
        int parseSong = Integer.parseInt(song.releasedYear);

        // Compare first by releasedYear
        String yearComparison = String.valueOf(Integer.compare(parsedThis, parseSong));

        // Updated variable. Error FIXED
        int parsedyearComparison = Integer.parseInt(yearComparison);

        // If the years are different, return the result of the year comparison
        if (parsedyearComparison != 0) {
            return parsedyearComparison;
        }
        return this.trackName.compareTo(song.trackName);
    }

    // Implement equals method for comparing Song objects
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Song song = (Song) obj;
        return trackName.equals(song.trackName) && artistsName.equals(song.artistsName)
                && releasedYear == song.releasedYear && releasedMonth == song.releasedMonth
                && releasedDay == song.releasedDay && totalNumberOfStreamsOnSpotify.equals(song.totalNumberOfStreamsOnSpotify);
    }

    public String getTrackName() {
        return trackName;
    }

    public String getArtistsName() {
        return artistsName;
    }


    public String getReleasedYear() {
        return releasedYear;
    }

    public String getTotalNumberOfStreamsOnSpotify() {
        return totalNumberOfStreamsOnSpotify;
    }
}
///**
// * Song record representing information about a song.
// *
// * @param trackName     Name of the song
// * @param artistsName   Name of the artist
// * @param releasedYear  Year that the song was released
// * @param releasedMonth Month that the song was released
// * @param releasedDay   Day that the song was released
// * @author DiegoCano
// */
//public record Song(String trackName, String artistsName, String releasedYear, String releasedMonth, String releasedDay, String totalNumberOfStreamsOnSpotify ) implements Comparable<Song> {
//
//    // Constructor with preconditions
//    public Song {
//        // Preconditions to ensure non-null and non-empty values
//        if (trackName == null || artistsName == null  || totalNumberOfStreamsOnSpotify == null) {
//            throw new NullPointerException("Null values are not allowed");
//        }
//        if (trackName.isEmpty() || artistsName.isEmpty() || totalNumberOfStreamsOnSpotify.isEmpty()) {
//            throw new IllegalArgumentException("Empty contents are not allowed");
//        }
//        // Add additional preconditions if needed
//    }
//
//    // Implementing compareTo method for natural ordering by track name
//    @Override
//    public int compareTo(Song song) {
//
//        // Updated variables. Error FIXED
//
//        int parsedThis = Integer.parseInt(this.releasedYear);
//        int parseSong = Integer.parseInt(song.releasedYear);
//
//        // Compare first by releasedYear
//        String yearComparison = String.valueOf(Integer.compare(parsedThis, parseSong));
//
//        // Updated variable. Error FIXED
//        int parsedyearComparison = Integer.parseInt(yearComparison);
//
//        // If the years are different, return the result of the year comparison
//        if (parsedyearComparison != 0) {
//            return parsedyearComparison;
//        }
//        return this.trackName.compareTo(song.trackName);
//    }
//
//    // Implement equals method for comparing Song objects
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        Song song = (Song) obj;
//        return trackName.equals(song.trackName) && artistsName.equals(song.artistsName)
//                && releasedYear == song.releasedYear && releasedMonth == song.releasedMonth
//                && releasedDay == song.releasedDay && totalNumberOfStreamsOnSpotify.equals(song.totalNumberOfStreamsOnSpotify);
//    }
//
//    public String getTrackName() {
//        return trackName;
//    }
//
//    public String getArtistsName() {
//        return artistsName;
//    }
//
//
//    public String getReleasedYear() {
//        return releasedYear;
//    }
//
//    public String getTotalNumberOfStreamsOnSpotify() {
//        return totalNumberOfStreamsOnSpotify;
//    }
//
//}
