import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

/**
 * SongManager class that manages songs and year of release data.
 */
class SongManager implements SongManagerInterface {

    // Two-dimensional array for Song data
    private Song[][] songs;

    // One-dimensional array for release years
    private int[] releaseYears;

    // Constructor
    public SongManager() {
        // Initialize arrays based on data from files
        initializeArrays();
    }

    // Method to initialize arrays by reading data from files
    private void initializeArrays() {
        // Read data from count-by-release-year.csv to get array sizes
        Map<Integer, Integer> yearCountMap = readYearCountFile("count-by-release-year.csv");

        // Set the size of the releaseYears array
        releaseYears = new int[yearCountMap.size()];

        // Set the size of the songs array based on the maximum count
        int maxSongCount = yearCountMap.values().stream().mapToInt(Integer::intValue).max().orElse(0);
        songs = new Song[yearCountMap.size()][maxSongCount];

        // Read data from spotify-2023.csv and fill the arrays
        readSongDataFile("spotify-2023.csv");
    }

private void readSongDataFile(String filename) {
    try (CSVReader reader = new CSVReader(new FileReader(filename))) {
        reader.readNext(); // Skip the header

        for (int currentYearIndex = 0, currentSongIndex = 0; ; ) {
            String[] line = reader.readNext();

            if (line == null) {
                break; // Break the loop when no more lines are available
            }

            String trackName = line[0].trim();
            String artistsName = line[1].trim();
            String releasedYear = line[2].trim();
            String releasedMonth = line[3].trim();
            String releasedDay = line[4].trim();
            String totalNumberOfStreamsOnSpotify = line[5].trim();

            Song song = new Song(trackName, artistsName, releasedYear, releasedMonth, releasedDay, totalNumberOfStreamsOnSpotify);


            songs[currentYearIndex][currentSongIndex] = song;


            currentSongIndex++;

            if (currentSongIndex >= releaseYears[currentYearIndex]) {
                currentYearIndex++;
                currentSongIndex = 0;
            }
        }

        Arrays.stream(songs).forEach(Arrays::sort);

    } catch (CsvValidationException | IOException e) {
        e.printStackTrace();
    }
}

    private Map<Integer, Integer> readYearCountFile(String filename) {
        Map<Integer, Integer> yearCountMap = new HashMap<>();

        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            String[] header = reader.readNext();
            reader.readNext();

            if (header != null) {
                //FIXED "Invalid header in the CSV file." ERROR
                for (String[] line; (line = reader.readNext()) != null; ) {
                    int year = Integer.parseInt(line[0].trim());
                    int count = Integer.parseInt(line[1].trim());
                    yearCountMap.put(year, count);
                }
            } else {
                System.err.println("Invalid header in the CSV file.");
            }
        } catch (CsvValidationException | IOException e) {

        }

        return yearCountMap;
    }





    /**
     * Returns the total number of years in the collection.
     *
     * @return The count of years.
     */
    @Override
    public int getYearCount() {
        return releaseYears.length;
    }

    /**
     * Returns the number of songs for a specific year.
     *
     * @param yearIndex The index of the year.
     * @return The count of songs for the specified year.
     */
    @Override
    public int getSongCount(int yearIndex) {
        return releaseYears[yearIndex];
    }

    /**
     * Returns the total number of songs in the collection.
     *
     * @return The count of songs.
     */
    @Override
    public int getSongCount() {
        return Arrays.stream(releaseYears).sum();
    }

    /**
     * Returns the name of the year at the specified index.
     *
     * @param yearIndex The index of the year.
     * @return The name of the year.
     */
    @Override
    public String getYearName(int yearIndex) {
        return String.valueOf(releaseYears[yearIndex]);
    }

    /**
     * Returns the number of songs for a specific year given the year as a string.
     *
     * @param year The year as a string.
     * @return The count of songs for the specified year.
     */
    @Override
    public int getSongCount(String year) {
        int yearInt = Integer.parseInt(year);
        int yearIndex = Arrays.binarySearch(releaseYears, yearInt);
        return (yearIndex >= 0) ? releaseYears[yearIndex] : 0;
    }

    /**
     * Returns the song at the specified indices.
     *
     * @param yearIndex The index of the year.
     * @param songIndex The index of the song within the year.
     * @return The song at the specified indices.
     */
    @Override
    public Song getSong(int yearIndex, int songIndex) {
        return songs[yearIndex][songIndex];
    }

    /**
     * Returns an array of songs for a specific year.
     *
     * @param yearIndex The index of the year.
     * @return An array of songs for the specified year.
     */
    @Override
    public Song[] getSongs(int yearIndex) {
        return Arrays.copyOf(songs[yearIndex], releaseYears[yearIndex]);
    }

    /**
     * Finds and returns the year in which a song with the given track name was released.
     *
     * @param trackName The name of the song track.
     * @return The year in which the song was released, or -1 if not found.
     */
    @Override
    public int findSongYear(String trackName) {
        for (int i = 0; i < releaseYears.length; i++) {
            for (int j = 0; j < releaseYears[i]; j++) {
                if (songs[i][j].getTrackName().equals(trackName)) {
                    return releaseYears[i];
                }
            }
        }
        return -1;
    }

}

