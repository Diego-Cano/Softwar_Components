import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoxGUI extends JFrame {
    private final JTextField lengthField;
    private final JTextField widthField;
    private final JTextField heightField;

    public BoxGUI() {
        // Set up the JFrame
        setTitle("- GUI for Box - ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().setBackground(Color.CYAN);
        setForeground(Color.WHITE);


        // Create labels
        JLabel lengthLabel1 = new JLabel("Length:");
        JLabel widthLabel1 = new JLabel("Width:");
        JLabel heightLabel1 = new JLabel("Height:");


        // Create text fields
        lengthField = new JTextField(10);
        widthField = new JTextField(10);
        heightField = new JTextField(10);
//

        // Create buttons
        JButton calculateVolumeButton1 = new JButton("Calculate Volume");
        JButton calculateSurfaceAreaButton1 = new JButton("Calculate Surface Area");
        JButton displayBoxDetailsButton1 = new JButton("Display Box Details");

        // Add components to the JFrame
        add(lengthLabel1);
        add(lengthField);
        add(widthLabel1);
        add(widthField);
        add(heightLabel1);
        add(heightField);
        add(calculateVolumeButton1);
        add(calculateSurfaceAreaButton1);
        add(displayBoxDetailsButton1);

        // Event listeners for buttons
        calculateVolumeButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateVolume();
            }
        });

        calculateSurfaceAreaButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateSurfaceArea();
            }
        });

        displayBoxDetailsButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayBoxDetails();
            }
        });

        pack();
        setLocationRelativeTo(null); // Center the JFrame
        setVisible(true);
    }

    // Event handling methods
    private void calculateVolume() {
        double length = Double.parseDouble(lengthField.getText());
        double width = Double.parseDouble(widthField.getText());
        double height = Double.parseDouble(heightField.getText());

        Box box = new Box(length, width, height);
        double volume = box.calculateVolume();
        JOptionPane.showMessageDialog(this, "Volume: " + volume);
    }

    private void calculateSurfaceArea() {
        double length1 = Double.parseDouble(lengthField.getText());
        double width1 = Double.parseDouble(widthField.getText());
        double height1 = Double.parseDouble(heightField.getText());

        Box box = new Box(length1, width1, height1);
        double surfaceArea = box.calculateSurfaceArea();
        JOptionPane.showMessageDialog(this, "Surface Area: " + surfaceArea);
    }

    private void displayBoxDetails() {
        double length = Double.parseDouble(lengthField.getText());
        double width = Double.parseDouble(widthField.getText());
        double height = Double.parseDouble(heightField.getText());

        Box box = new Box(length, width, height);
        String details = "Length: " + box.getLength() + "\nWidth: " + box.getWidth() + "\nHeight: " + box.getHeight();
        JOptionPane.showMessageDialog(this, details);
    }

    public static void main(String[] args) {
        new BoxGUI();
    }
}
