import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

/**
 * SongManager class that manages songs and year of release data.
 */
class SongManager implements SongManagerInterface {

    // Two-dimensional array for Song data
    private Song[][] songs;

    // One-dimensional array for release years
    private int[] releaseYears;

    private int[] songCounts;

    // Constructor
    public SongManager() {
        // Initialize arrays based on data from files
        initializeArrays();
    }

    // Method to initialize arrays by reading data from files
    private void initializeArrays() {
        int yearCount = 0;
        int maxSongCount = 0;
        releaseYears = new int[0];
        songCounts = new int[0];

        // Read data from count-by-release-year.csv to get array sizes
        try (CSVReader reader = new CSVReader(new FileReader("count-by-release-year.csv"))) {
            List<String[]> allLines = reader.readAll();
            for (int i = 0; i < allLines.size(); i++) {
                if (i == 0) {
                    // first line, total year count
                    yearCount = Integer.parseInt(allLines.get(i)[0]);
                    releaseYears = new int[yearCount];
                    songCounts = new int[yearCount];
                } else if (i == 1) {
                    // second line, headers, do nothing
                } else {
                    String[] yearAndCount = allLines.get(i);
                    int year = Integer.parseInt(yearAndCount[0]);
                    int songCount = Integer.parseInt(yearAndCount[1]);
                    releaseYears[i - 2] = year;
                    songCounts[i - 2] = songCount;
                    if (songCount > maxSongCount) {
                        maxSongCount = songCount;
                    }
                }
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException();
        }

        songs = new Song[yearCount][maxSongCount];

        // Read data from spotify-2023.csv and fill the arrays
        readSongDataFile("spotify-2023.csv");
    }

    private void readSongDataFile(String filename) {
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            reader.readNext(); // Skip the header

            for (int yearIndex = 0; yearIndex < releaseYears.length; yearIndex++) {
                for (int songIndex = 0; songIndex < songCounts[yearIndex]; songIndex++) {
                    String[] songLine = reader.readNext();
                    String trackName = songLine[0].trim();
                    String artistsName = songLine[1].trim();
                    String releasedYear = songLine[3].trim();
                    String releasedMonth = songLine[4].trim();
                    String releasedDay = songLine[5].trim();
                    String totalNumberOfStreamsOnSpotify = songLine[8].trim();

                    Song song = new Song(trackName, artistsName, releasedYear, releasedMonth, releasedDay, totalNumberOfStreamsOnSpotify);

                    songs[yearIndex][songIndex] = song;
                }
            }
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
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
        return songCounts[yearIndex];
    }

    /**
     * Returns the total number of songs in the collection.
     *
     * @return The count of songs.
     */
    @Override
    public int getSongCount() {
        int totalSongs = 0;
        for (int i = 0; i < songCounts.length; i++) {
            totalSongs += songCounts[i];
        }
        return totalSongs;
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
        return songCounts[yearIndex];
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
        return songs[yearIndex];
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
