// Chapter 1: The Box of Dimensions
class Box {
    private final double width;
    private final double height;
    private final double depth;

    // Constructor to initialize the dimensions of the box
    public Box(double width, double height, double depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    // Method to compute and reveal the volume of the box
    public double volume() {
        return width * height * depth;
    }

    // Chapter 2: The Box of Items
    private String[] items = new String[10];
    private int itemCount = 0;

    // Method to add an item to the box's treasure trove
    public void addItem(String item) {
        if (itemCount < 10) {
            items[itemCount] = item;
            itemCount++;
            System.out.println(item + " added to the box.");
        } else {
            System.out.println("The box is full. Cannot add more items.");
        }
    }

    // Method to remove an item from the box
    public void removeItem(String item) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].equals(item)) {
                // Shift remaining items to fill the gap
                for (int j = i; j < itemCount - 1; j++) {
                    items[j] = items[j + 1];
                }
                System.out.println(item + " removed from the box.");
                return;
            }
        }
        System.out.println(item + " not found in the box.");
    }

    // Method to print out all the items in the box
    public void printItems() {
        System.out.println("Items in the box:");
        for (int i = 0; i < itemCount; i++) {
            System.out.println(items[i]);
        }
    }
}

