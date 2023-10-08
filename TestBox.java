import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoxTest {

    @Test
    void testCreateBox() {
        Box box = new Box(2.0, 3.0, 4.0);
        assertNotNull(box, "Box object should be created successfully");
    }

    @Test
    void testVolumeCalculation() {
        Box box = new Box(2.0, 3.0, 4.0);
        double expectedVolume = 2.0 * 3.0 * 4.0;
        assertEquals(expectedVolume, box.volume(), 0.0001, "Volume calculation is incorrect");
    }

    @Test
    void testAddItem() {
        Box box = new Box(2.0, 3.0, 4.0);
        box.addItem("Mystical Sword");
        assertEquals(1, box.getItemCount(), "Item should be added to the box");
    }

    @Test
    void testRemoveItem() {
        Box box = new Box(2.0, 3.0, 4.0);
        box.addItem("Mystical Sword");
        box.removeItem("Mystical Sword");
        assertEquals(0, box.getItemCount(), "Item should be removed from the box");
    }

    @Test
    void testRemoveNonexistentItem() {
        Box box = new Box(2.0, 3.0, 4.0);
        box.addItem("Mystical Sword");
        box.removeItem("Ancient Shield");
        assertEquals(1, box.getItemCount(), "Item should not be removed if it doesn't exist");
    }

    @Test
    void testAddTooManyItems() {
        Box box = new Box(2.0, 3.0, 4.0);
        for (int i = 0; i < 15; i++) {
            box.addItem("Item" + i);
        }
        assertEquals(10, box.getItemCount(), "Only 10 items should be added to the box");
    }
}
