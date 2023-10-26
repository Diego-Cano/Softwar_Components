import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import java.io.FileReader;
import java.io.IOException;
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


    // Method to read data from count-by-release-year.csv and return a map of year counts
    private Map<Integer, Integer> readYearCountFile(String filename) {
        Map<Integer, Integer> yearCountMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Read the first line (header) and skip it
            String header = reader.readLine();

            // Check if the header contains expected column names
            if (header != null && header.contains("Year") && header.contains("Count")) {
                // Read data and populate the map
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    int year = Integer.parseInt(parts[0].trim());
                    int count = Integer.parseInt(parts[1].trim());
                    yearCountMap.put(year, count);
                }
            } else {
                // Handle the case where the header is missing or not as expected
                System.err.println("Invalid header in the CSV file.");
            }
        } catch (IOException | NumberFormatException e) {
            // Handle IOException or NumberFormatException
            e.printStackTrace();
        }

        return yearCountMap;
    }



    // Method to read data from spotify-2023.csv and fill the songs array
    private void readSongDataFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Skip the first line (header)
            reader.readLine();

            // Read data and fill the songs array
            String line;
            int currentYearIndex = 0;
            int currentSongIndex = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String trackName = parts[0].trim();
                String artistsName = parts[1].trim();
                String releasedYear =  parts[2].trim();
                String releasedMonth =  parts[3].trim();
                String releasedDay =  parts[4].trim();
                String totalNumberOfStreamsOnSpotify =  parts[1].trim();
//                Integer.parseInt(parts[3].trim());
//                Integer.parseInt(parts[4].trim());
//                Integer.parseInt(parts[5].trim());
//                parts[9].trim();
                // Create a Song object
                Song song = new Song(trackName, artistsName, releasedYear, releasedMonth, releasedDay, totalNumberOfStreamsOnSpotify);

                // Add the song to the songs array
                songs[currentYearIndex][currentSongIndex] = song;

                // Move to the next song index
                currentSongIndex++;

                // If the current song index exceeds the count for the current year, move to the next year
                if (currentSongIndex >= releaseYears[currentYearIndex]) {
                    currentYearIndex++;
                    currentSongIndex = 0;
                }
            }

            // Sort each year's songs by track name
            Arrays.stream(songs).forEach(Arrays::sort);

        } catch (IOException e) {
            // Handle IOException
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

