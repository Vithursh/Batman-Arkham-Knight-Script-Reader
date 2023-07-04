import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Panel extends JPanel{
	
	/*
	 * The Panel class adds visual enhancements and user-friendly features to the "Batman Arkham Knight" 
	 * script application. It takes care of text area styling, background image management, and the inclusion
	 * of intuitive play/pause buttons. These improvements create an appealing interface that enhances the 
	 * overall user experience.
	 */
	
	public static ImageIcon wallpaperIcon;
	public static Image wallpaperImage;
	public static JLabel backgroundLabel;
	
	public static JButton playButton = new JButton(new ImageIcon("images/play-buttton.png"));
	public static JButton pauseButton = new JButton(new ImageIcon("images/pause.png"));

	public Panel() {
		
		DisplayFrame.textBox2.setFont(new Font("Ink Free", Font.BOLD, 18));   // Set the font, bold, and size for the text area
        DisplayFrame.textBox2.setEditable(false);   // Set the text area to not be editable
        DisplayFrame.textBox2.setLineWrap(true); // Allows for line wrap
        DisplayFrame.textBox2.setWrapStyleWord(true); // Allows for word wrap
        DisplayFrame.textBox2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        DisplayFrame.textBox1.setFont(new Font("Ink Free", Font.BOLD, 18));   // Set the font, bold, and size for the text area
        DisplayFrame.textBox1.setEditable(false);   // Set the text area to not be editable
        DisplayFrame.textBox1.setLineWrap(true); // Allows for line wrap
        DisplayFrame.textBox1.setWrapStyleWord(true); // Allows for word wrap
        DisplayFrame.textBox1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Load the image and create a JLabel with the image
        wallpaperIcon = new ImageIcon("images/wallpaper.jpg");
        wallpaperImage = wallpaperIcon.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        backgroundLabel = new JLabel(new ImageIcon(wallpaperImage));
        backgroundLabel.setBounds(0, 0, 1920, 1080);
        
        playButton.setBounds(5, 320, 70, 70);
        playButton.setOpaque(false);   // Make the background of the play button transparent
        playButton.setContentAreaFilled(false);   // Remove the fill of the play button
        playButton.setBorderPainted(false); // Remove the border of the play button
        backgroundLabel.add(playButton);
        
        pauseButton.setBounds(70, 320, 70, 70);
        pauseButton.setOpaque(false);   // Make the background of the pause button transparent
        pauseButton.setContentAreaFilled(false);   // Remove the fill of the pause button
        pauseButton.setBorderPainted(false);  // Remove the border of the pause button
        backgroundLabel.add(pauseButton);
	}
}