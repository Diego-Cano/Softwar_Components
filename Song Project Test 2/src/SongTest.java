import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SongTest {

    @Test
    public void testConstructorAndAccessors() {
        Song song = new Song("TestTrack", "TestArtist", "2023", "January", "1", "1000");

        assertEquals("TestTrack", song.getTrackName());
        assertEquals("TestArtist", song.getArtistsName());
        assertEquals("2023", song.getReleasedYear());
        assertEquals("1000", song.getTotalNumberOfStreamsOnSpotify());
    }

    @Test
    public void testToString() {
        Song song = new Song("TestTrack", "TestArtist", "2023", "January", "1", "1000");

        // Check that toString is not returning an empty string
        assertFalse(song.toString().isEmpty());

        // Check that toString doesn't start with "Song@"
        assertFalse(song.toString().startsWith("Song@"));
    }


    @Test
    public void testEquals() {
        Song song1 = new Song("TestTrack", "TestArtist", "2023", "January", "1", "1000");
        Song song2 = new Song("TestTrack", "TestArtist", "2023", "January", "1", "1000");
        Song song3 = new Song("DifferentTrack", "DifferentArtist", "2023", "January", "1", "1000");

        assertEquals(song1, song2);  // Check equality for two identical songs
        assertNotEquals(song1, song3);  // Check inequality for two different songs
    }

    @Test
    public void testEqualsWithNull() {
        Song song1 = new Song("TestTrack", "TestArtist", "2023", "January", "1", "1000");
        Song song2 = null;

        assertNotEquals(song1, song2);  // Check inequality with null
    }

    @Test
    public void testEqualsWithDifferentClass() {
        Song song = new Song("TestTrack", "TestArtist", "2023", "January", "1", "1000");
        String differentClassObject = "NotASong";

        assertNotEquals(song, differentClassObject);  // Check inequality with a different class object
    }

    @Test
    public void testGetReleasdDate() {
        Song song = new Song("TestTrack", "TestArtist", "2023", "January", "1", "1000");
        assertEquals("January/1/2023", song.getReleasdDate());
    }
    @Test
    public void testCompareTo() {
        Song song1 = new Song("TestTrack1", "TestArtist", "2023", "January", "1", "1000");
        Song song2 = new Song("TestTrack2", "TestArtist", "2023", "January", "2", "1500");

        assertTrue(song1.compareTo(song2) < 0);  // Check natural ordering by track name and release year
    }



}
