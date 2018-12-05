package grayroom.ui;

import javax.swing.*;

import grayroom.item.Inventory;
import grayroom.scene.Scene;

import grayroom.ui.component.InventoryPanel;
import grayroom.ui.component.NavigationPanel;

import java.awt.*;

// TODO: Auto-generated Javadoc
/**
 * The Class MainWindow.
 */
public class MainWindow extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The info text. */
	private JLabel infoText = new JLabel("Where am I?");
	
	/** The info panel. */
	private JPanel infoPanel = new JPanel();
	
	/** The inventory panel. */
	private InventoryPanel inventoryPanel;
	
	/** The nav panel. */
	private NavigationPanel navPanel;
	
	/**
	 * Instantiates a new main window.
	 *
	 * @param currentScene the current scene
	 */
	public MainWindow(Scene currentScene) {
		super("The Gray Room");
		navPanel = new NavigationPanel(currentScene);
		inventoryPanel = new InventoryPanel();
		
		setupFrame();
		redrawSceneNav(currentScene);
	}
	
	/**
	 * Setup the frame.
	 */
	private void setupFrame() {
		setResizable(false);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		infoText.setHorizontalAlignment(SwingConstants.CENTER);
		infoText.setFont(new Font("Arial", Font.BOLD, 24));
		
		infoPanel.setLayout(new GridLayout(1, 1));
		infoPanel.add(infoText);

		add(navPanel);
		add(infoPanel);
		add(inventoryPanel);
		
		pack();
	}
	
	/**
	 * Redraw the navigation panel.
	 *
	 * @param scene the new scene to draw on the frame
	 */
	public void redrawSceneNav(Scene scene) {
		navPanel.redraw(scene);
	}
	
	/**
	 * Redraw the inventory panel's buttons.
	 *
	 * @param inventory the player's inventory
	 */
	public void redrawInventory(Inventory inventory) {
		inventoryPanel.redrawButtons(inventory);
	}
	
	/**
	 * Display a message.
	 *
	 * @param msg the message to display
	 */
	public void displayMessage(String msg) {
		infoText.setText(msg);
	}
}
