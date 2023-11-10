import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BoxTest {

    @Test
    public void testCalculateVolume() {
        Box box = new Box(2.0, 3.0, 4.0);
        assertEquals(24.0, box.calculateVolume(), 0.0001);
    }

    @Test
    public void testCalculateSurfaceArea() {
        Box box = new Box(2.0, 3.0, 4.0);
        assertEquals(52.0, box.calculateSurfaceArea(), 0.0001);
    }

    @Test
    public void testBoxDetails() {
        Box box = new Box(2.0, 3.0, 4.0);
        assertEquals("Length: 2.0\nWidth: 3.0\nHeight: 4.0", getBoxDetailsString(box));
    }

    // Helper method to get details string for a Box
    private String getBoxDetailsString(Box box) {
        return "Length: " + box.getLength() + "\nWidth: " + box.getWidth() + "\nHeight: " + box.getHeight();
    }
}
