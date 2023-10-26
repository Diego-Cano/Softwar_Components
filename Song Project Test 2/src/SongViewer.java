import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SongViewer class supplying the GUI for loading and browsing song data by year.
 */
public class SongViewer extends JFrame {

    // SongManager instance to manage song data
    private final SongManager songManager;

    // GUI components
    private final JButton loadButton;
    private final JButton prevButton;
    private final JButton nextButton;
    private final JComboBox<String> yearComboBox;
    private final JTextField trackNameField;
    private final JTextField artistField;
    private final JTextField releasedYearField;
    private final JTextField streamsField;

    // Current state
    private int currentYearIndex;
    private int currentSongIndex;

    // Constructor
    public SongViewer(SongManager songManager) {
        this.songManager = songManager;

        // Initialize GUI components
        loadButton = new JButton("Load Data");
        prevButton = new JButton("Prev");
        nextButton = new JButton("Next");
        yearComboBox = new JComboBox<>();
        trackNameField = new JTextField();
        artistField = new JTextField();
        releasedYearField = new JTextField();
        streamsField = new JTextField();

        // Set layout manager
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Add components to the frame
        add(loadButton);
        add(new JLabel("Release Year:"));
        add(yearComboBox);
        add(new JLabel("Track Name:"));
        add(trackNameField);
        add(new JLabel("Artist:"));
        add(artistField);
        add(new JLabel("Released Year:"));
        add(releasedYearField);
        add(new JLabel("Total Streams:"));
        add(streamsField);
        add(prevButton);
        add(nextButton);

        // Set initial state
        setInitialState();

        // Add action listeners
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadButtonClicked();
            }
        });

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prevButtonClicked();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextButtonClicked();
            }
        });

        yearComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yearComboBoxChanged();
            }
        });

        // Set frame properties
        setTitle("Song Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    // Method to set the initial state of the GUI
    private void setInitialState() {
        loadButton.setEnabled(true);
        prevButton.setEnabled(false);
        nextButton.setEnabled(false);
        yearComboBox.setEnabled(false);
        trackNameField.setEditable(false);
        artistField.setEditable(false);
        releasedYearField.setEditable(false);
        streamsField.setEditable(false);
    }

    // Method called when the Load Data button is clicked
    private void loadButtonClicked() {
        // Load data from SongManager and populate the yearComboBox
        for (int i = 0; i < songManager.getYearCount(); i++) {
            yearComboBox.addItem(songManager.getYearName(i));
        }

        // Enable components
        loadButton.setEnabled(false);
        prevButton.setEnabled(true);
        nextButton.setEnabled(true);
        yearComboBox.setEnabled(true);

        // Set the initial state for the first year
        currentYearIndex = 0;
        currentSongIndex = 0;
        updateFields();
    }

    // Method called when the Prev button is clicked
    private void prevButtonClicked() {
        if (currentSongIndex > 0) {
            currentSongIndex--;
        } else if (currentYearIndex > 0) {
            currentYearIndex--;
            currentSongIndex = songManager.getSongCount(currentYearIndex) - 1;
        }

        updateFields();
    }

    // Method called when the Next button is clicked
    private void nextButtonClicked() {
        if (currentSongIndex < songManager.getSongCount(currentYearIndex) - 1) {
            currentSongIndex++;
        } else if (currentYearIndex < songManager.getYearCount() - 1) {
            currentYearIndex++;
            currentSongIndex = 0;
        }

        updateFields();
    }

    // Method called when the yearComboBox selection changes
    private void yearComboBoxChanged() {
        currentYearIndex = yearComboBox.getSelectedIndex();
        currentSongIndex = 0;

        updateFields();
    }

    // Method to update the text fields based on the current state
    private void updateFields() {
        Song currentSong = songManager.getSong(currentYearIndex, currentSongIndex);

        trackNameField.setText(currentSong.getTrackName());
        artistField.setText(currentSong.getArtistsName());
        releasedYearField.setText(String.valueOf(currentSong.getReleasedYear()));
//        streamsField.setText(currentSong.getTotalNumberOfStreamsOnSpotify());
    }

    public static void main(String[] args) {

                new SongViewer(new SongManager());

    }
}

