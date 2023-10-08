// Chapter 3: Unleash the Magic
public class MagicalBoxDemo {
    public static void main(String[] args) {
        // Create a magical box with dimensions 3.0 x 4.0 x 5.0
        Box magicalBox = new Box(3.0, 4.0, 5.0);

        // Add items to the box
        magicalBox.addItem("Silver Key");
        magicalBox.addItem("Ancient Scroll");
        magicalBox.addItem("Magic Book");
        magicalBox.addItem("Mystical Dagger");
        magicalBox.addItem("Trident");

        // Print the volume of the box
        System.out.println("Volume of the magical box: " + magicalBox.volume());

        // Print items in the box
        magicalBox.printItems();

        // Remove an item from the box
        magicalBox.removeItem("Ancient Scroll");

        // Print items again after removal
        magicalBox.printItems();
    }
}
