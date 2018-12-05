package grayroom.ui.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import grayroom.item.Inventory;
import grayroom.item.Item;

// TODO: Auto-generated Javadoc
/**
 * The Class InventoryPanel.
 */
public class InventoryPanel extends JPanel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The stretch label. Required to keep the InventoryPanel visible. */
	private JLabel stretchLabel = new JLabel();
	
	/** The button panel. */
	private JPanel buttonPanel = new JPanel();
	
	/** The button group. */
	private ButtonGroup buttonGroup = new ButtonGroup();
	
	/**
	 * Instantiates a new inventory panel.
	 */
	public InventoryPanel() {
		super();
		setLayout(new FlowLayout());
		setBackground(Color.WHITE);
		
		stretchLabel.setPreferredSize(new Dimension(1, 60));
		
		Border blackline = BorderFactory.createLineBorder(Color.BLACK);
		TitledBorder title = BorderFactory.createTitledBorder(blackline, "Inventory");
		title.setTitleJustification(TitledBorder.LEFT);
		title.setTitleFont(new Font("Arial", Font.BOLD, 14));
		
		buttonPanel.add(stretchLabel);
		
		setBorder(title);
		add(buttonPanel);
	}
	
	/**
	 * Redraw the inventory buttons.
	 *
	 * @param inventory the player's inventory
	 */
	public void redrawButtons(Inventory inventory) {
		buttonPanel.removeAll();
		buttonPanel.revalidate();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(stretchLabel);
		buttonGroup = new ButtonGroup();
		generateButtons(inventory);
		buttonPanel.repaint();
	}
	
	/**
	 * Generate the inventory buttons.
	 *
	 * @param inventory the player's inventory
	 */
	public void generateButtons(Inventory inventory) {
		for (Item it : inventory.getAllItems()) {
				InventoryButton tempButton = new InventoryButton(it.getID());
				tempButton = new InventoryButton(it.getID());
				tempButton.setToolTipText(it.getName());
				tempButton.setIcon(new ImageIcon(it.getImage()));
				buttonGroup.add(tempButton);
				buttonPanel.add(tempButton);
		}
		
		buttonPanel.revalidate();
	}
}

