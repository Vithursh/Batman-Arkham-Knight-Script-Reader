import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.Timer;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

public class FileImport implements ActionListener {
	
	/*
	 * The FileImport class is responsible for importing and handling the script of the "Batman Arkham Knight" 
	 * game. It performs various tasks such as splitting the text into manageable chunks, managing timers 
	 * and progress bars, displaying relevant images based on the script content, and 
	 * incorporating text-to-speech synthesis using FreeTTS. These features enhance the overall 
	 * user experience by providing dynamic visuals and audio elements that accompany the script.
	 */
	
    // Define the class FileImport, which implements the ActionListener interface

    private int lineNumber = 0;
    // Declare a private integer variable lineNumber and initialize it to 0

    public static String line;
    public static String[] text; // Declare the 'text' variable as a string array

    public static int currentSlide = 0;
    public static Timer timer; // Declare the 'currentSlide' variable as an integer and 'timer' variable as a Timer

    public static ImageIcon JasonIcon;
    public static Image Jason;
    public static JLabel JasonLabel; // Declare variables for Jason's image icon, image, and label

    public static ImageIcon BatmanIcon;
    public static Image Batman;
    public static JLabel BatmanLabel; // Declare variables for Batman's image icon, image, and label

    public static ImageIcon GordonIcon;
    public static Image Gordon;
    public static JLabel GordonLabel; // Declare variables for Gordon's image icon, image, and label

    public static ImageIcon JokerIcon;
    public static Image Joker;
    public static JLabel JokerLabel; // Declare variables for Joker's image icon, image, and label

    public static ImageIcon AlfredIcon;
    public static Image Alfred;
    public static JLabel AlfredLabel; // Declare variables for Alfred's image icon, image, and label

    public Synthesizer synthesizer; // Declare a Synthesizer variable for text-to-speech functionality

    public int progress = 0;
    public int totalLines; // Declare integer variables for progress and totalLines

    public JProgressBar progressBar; // Declare a JProgressBar variable for displaying progress

    public FileImport() throws IOException {
        // Constructor for the FileImport class, which may throw an IOException

        Panel.playButton.addActionListener(this);
        Panel.pauseButton.addActionListener(this);
        // Add the current instance of FileImport as an ActionListener to the playButton and pauseButton in the Panel class

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        Panel.backgroundLabel.add(progressBar, BorderLayout.CENTER);
        progressBar.setVisible(true);
        progressBar.setBounds(12, 650, 1250, 20);
        // Create and configure a JProgressBar, set its visibility and position in the backgroundLabel of the Panel class

        String filePath = "Batman Arkham Knight Script.txt";
        text = splitTextFromFile(filePath); // Split the text from the specified file into an array
        DisplayFrame.textBox2 = DisplayFrame.textBox2;
        // Set the textBox2 field in the DisplayFrame class to itself
        timer = new Timer(1000, this); // Create a Timer with a delay of 1000 milliseconds and set this instance of FileImport as the ActionListener
        timer.start(); // Start the timer
        DisplayFrame.textBox2.setText(text[currentSlide]); // Set the initial text on the GUI to the first element in the text array

        JasonIcon = new ImageIcon("images/Jason.jpg");
        Jason = JasonIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        JasonLabel = new JLabel(new ImageIcon(Jason));
        JasonLabel.setBounds(0, 0, 350, 1080);
        // Load and scale the image of Jason, create a label with the image, and set its bounds

        BatmanIcon = new ImageIcon("images/Batman.jpg");
        Batman = BatmanIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        BatmanLabel = new JLabel(new ImageIcon(Batman));
        BatmanLabel.setBounds(0, 0, 800, 1080);
        // Load and scale the image of Batman, create a label with the image, and set its bounds

        GordonIcon = new ImageIcon("images/Gorden.jpg");
        Gordon = GordonIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        GordonLabel = new JLabel(new ImageIcon(Gordon));
        GordonLabel.setBounds(0, 0, 1250, 1080);
        // Load and scale the image of Gordon, create a label with the image, and set its bounds

        JokerIcon = new ImageIcon("images/Joker.jpg");
        Joker = JokerIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        JokerLabel = new JLabel(new ImageIcon(Joker));
        JokerLabel.setBounds(0, 0, 1920, 1080);
        // Load and scale the image of Joker, create a label with the image, and set its bounds

        AlfredIcon = new ImageIcon("images/Alfred.png");
        Alfred = AlfredIcon.getImage().getScaledInstance(655, 715, Image.SCALE_SMOOTH);
        AlfredLabel = new JLabel(new ImageIcon(Alfred));
        AlfredLabel.setBounds(0, 0, 1920, 1080);
        // Load and scale the image of Alfred, create a label with the image, and set its bounds

        Panel.backgroundLabel.add(FileImport.JasonLabel); // Add the Jason image label to the backgroundLabel in the Panel class
        Panel.backgroundLabel.add(FileImport.BatmanLabel); // Add the Batman image label to the backgroundLabel in the Panel class
        Panel.backgroundLabel.add(FileImport.GordonLabel); // Add the Gordon image label to the backgroundLabel in the Panel class
        Panel.backgroundLabel.add(FileImport.JokerLabel); // Add the Joker image label to the backgroundLabel in the Panel class
        Panel.backgroundLabel.add(FileImport.AlfredLabel); // Add the Alfred image label to the backgroundLabel in the Panel class

        try {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
            synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
            synthesizer.allocate();
            synthesizer.resume();
            // Set system properties and register the FreeTTS speech synthesis engine
            // Create a synthesizer with the US English voice
            // Allocate resources for the synthesizer and resume it
        } catch (Exception e) {
            e.printStackTrace();
            // Print the stack trace if an exception occurs
        }
    }

    private String[] splitTextFromFile(String filePath) throws IOException {
        // Split the text from the file into an array of lines

        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Read the file using a BufferedReader
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n"); // Append each line to the StringBuilder
            }
        }

        totalLines = sb.toString().split("\n").length; // Calculate the total number of lines

        return sb.toString().split("\n"); // Split the text into an array of lines
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Action to perform when an event occurs

        currentSlide++; // Increment the current slide
        if (currentSlide >= text.length) {
            currentSlide = 0; // Reset to the first slide if reached the end
        }
        DisplayFrame.textBox2.setText(text[currentSlide]); // Set the text of the current slide on the GUI

        try {
            synthesizer.speakPlainText(text[currentSlide], null); // Synthesize the text to speech
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (currentSlide < totalLines) {
            progress = (currentSlide * 100) / totalLines;
            progressBar.setValue(progress);
            timer.start();
        } else {
            timer.stop();
        }

        // Progress complete, perform necessary actions

        if(e.getSource() == Panel.playButton) {
            timer.stop();
            System.out.println("The script has been paused");
        }
        else if(e.getSource() == Panel.pauseButton) {
            timer.start();
            System.out.println("The script has resumed");
        }

        // Display specific images and text based on the content of the current slide
        if (text[currentSlide].contains("Jason")) {
            System.out.println("The word 'Jason' is in this line");
            DisplayFrame.textBox1.setText("Jason:");
            FileImport.JasonLabel.setVisible(true);
            FileImport.BatmanLabel.setVisible(false);
            FileImport.GordonLabel.setVisible(false);
            FileImport.JokerLabel.setVisible(false);
            FileImport.AlfredLabel.setVisible(false);
        } else if (text[currentSlide].contains("Batman")) {
            System.out.println("The word 'Batman' is in this line");
            DisplayFrame.textBox1.setText("Batman:");
            FileImport.JasonLabel.setVisible(false);
            FileImport.BatmanLabel.setVisible(true);
            FileImport.GordonLabel.setVisible(false);
            FileImport.JokerLabel.setVisible(false);
            FileImport.AlfredLabel.setVisible(false);
        } else if (text[currentSlide].contains("Gordon")) {
            System.out.println("The word 'Gordon' is in this line");
            DisplayFrame.textBox1.setText("Gordon:");
            FileImport.JasonLabel.setVisible(false);
            FileImport.BatmanLabel.setVisible(false);
            FileImport.GordonLabel.setVisible(true);
            FileImport.JokerLabel.setVisible(false);
            FileImport.AlfredLabel.setVisible(false);
        } else if (text[currentSlide].contains("Joker")) {
            System.out.println("The word 'Joker' is in this line");
            DisplayFrame.textBox1.setText("Joker:");
            FileImport.JasonLabel.setVisible(false);
            FileImport.BatmanLabel.setVisible(false);
            FileImport.GordonLabel.setVisible(false);
            FileImport.JokerLabel.setVisible(true);
            FileImport.AlfredLabel.setVisible(false);
        } else if (text[currentSlide].contains("Alfred")) {
            System.out.println("The word 'Alfred' is in this line");
            DisplayFrame.textBox1.setText("Alfred:");
            FileImport.JasonLabel.setVisible(false);
            FileImport.BatmanLabel.setVisible(false);
            FileImport.GordonLabel.setVisible(false);
            FileImport.JokerLabel.setVisible(false);
            FileImport.AlfredLabel.setVisible(true);
        } else if (text[currentSlide].contains("[Music]")) {
            System.out.println("The word '[Music]' is in this line");
            DisplayFrame.textBox1.setText("No one is talking");
        }
    }
}