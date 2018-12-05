package grayroom.ui.component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import grayroom.Game;
import grayroom.event.GameEvent;
import grayroom.event.GameEventType;

// TODO: Auto-generated Javadoc
/**
 * The Class InventoryButton.
 */
public class InventoryButton extends JToggleButton {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The parent inventory item ID. */
	private String parentItemID;
	
	/**
	 * Instantiates a new inventory button.
	 *
	 * @param parentItem the button's parent inventory item ID
	 */
	public InventoryButton(String parentItem) {
		super("X");
		
		parentItemID = parentItem;
		setPreferredSize(new Dimension(56, 52));
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.BLACK));
		setHorizontalAlignment(SwingConstants.LEFT);
		
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSelected()) {
					GameEvent event = new GameEvent(GameEventType.INVENTORY_ITEM_CLICKED);
					event.addDataToPacket(parentItemID);
					Game.eventHandler.registerEvent(event);
				}
			}
		});
	}
}

