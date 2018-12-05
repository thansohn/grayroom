package grayroom.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import grayroom.util.Constants;
import grayroom.util.UtilityFunctions;

// TODO: Auto-generated Javadoc
/**
 * The Class VictoryWindow.
 */
public class VictoryWindow extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The image label. */
	private JLabel imageLabel = new JLabel();
	
	/** The exit button. */
	private JButton exitButton = new JButton("Quit");
	
	/**
	 * Instantiates a new victory window.
	 */
	public VictoryWindow() {
		super("The Gray Room - Victory!");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setupFrame();
	}

	/**
	 * Setup frame.
	 */
	private void setupFrame() {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		BufferedImage victoryImage = UtilityFunctions.loadResourceImage(Constants.UI_ICON_DIRECTORY + "endscreen.png");
		
		imageLabel.setPreferredSize(new Dimension(victoryImage.getWidth(), victoryImage.getHeight()));
		imageLabel.setIcon(new ImageIcon(victoryImage));

		JPanel exitButtonPanel = new JPanel();
		exitButtonPanel.setLayout(new GridLayout(1,1));
		exitButton.setHorizontalAlignment(SwingConstants.CENTER);
		exitButtonPanel.add(exitButton);
		
		JPanel imagePanel = new JPanel();
		imagePanel.setLayout(new GridLayout(1,1));
		imagePanel.add(imageLabel);
		
		add(imagePanel);
		add(exitButtonPanel);
		
		exitButton.addActionListener(new ActionListener() { @Override
			public void actionPerformed(ActionEvent e) {
				System.exit(DISPOSE_ON_CLOSE);
			}
		});
		
		pack();
		
		
	}
}
