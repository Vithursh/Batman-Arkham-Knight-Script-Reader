import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class DisplayFrame extends JFrame {
	
	/*
	 * The DisplayFrame class plays a key role in the "Batman Arkham Knight" script reader application. It 
	 * takes care of creating the main frame and setting up various components such as text areas, labels, 
	 * and the Panel class. The frame's appearance and behavior are carefully configured to ensure a seamless 
	 * user experience. Additionally, it initiates the FileImport class responsible for handling the script 
	 * and its associated operations.
	 */

    public static JTextArea textBox1 = new JTextArea();
    public static JTextArea textBox2 = new JTextArea(20, 60);

    public DisplayFrame() throws IOException {
    	
        // Add ConsumerPriceIndex class to this class
        this.add(new Panel());

        // Creates the GUI
        this.setTitle("Batman Arkham Knight Script Reader");
        this.setSize(1920, 1080);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // Set the content pane of the frame to the background label
        this.setContentPane(Panel.backgroundLabel);
        Panel.backgroundLabel.add(textBox1);
        Panel.backgroundLabel.add(textBox2);

        //Placing textBox1 on a specific place on the GUI 
        this.textBox1.setBounds(10, 10, 200, 300);
        //Making textBox1 visible
        this.textBox1.setVisible(true);
        
        //Placing textBox2 on a specific place on the GUI 
        this.textBox2.setBounds(770, 40, 500, 300);
        //Making textBox2 visible
        this.textBox2.setVisible(true);

        this.setVisible(true);
        
        //Calls the fileImport class 
        new FileImport();
    }
}