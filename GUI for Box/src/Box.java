// Box.java
public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    // Getter and Setter methods for length
    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    // Getter and Setter methods for width
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    // Getter and Setter methods for height
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    // Method to calculate the volume of the box
    public double calculateVolume() {
        return length * width * height;
    }

    // Method to calculate the surface area of the box
    public double calculateSurfaceArea() {
        return 2 * (length * width + length * height + width * height);
    }
}

