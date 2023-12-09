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
}
