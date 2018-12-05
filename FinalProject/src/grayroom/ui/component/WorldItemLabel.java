package grayroom.ui.component;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import grayroom.Game;
import grayroom.event.GameEvent;
import grayroom.event.GameEventType;

// TODO: Auto-generated Javadoc
/**
 * The Class WorldItemLabel. This is never used, but has been retained in case
 * it were to ever be implemented
 */
public class WorldItemLabel extends JLabel implements MouseListener, MouseMotionListener{
	
	/** The parent item ID. */
	private String parentItemID;
	
	/**
	 * Instantiates a new world item label.
	 *
	 * @param parentItemID the parent item ID
	 * @param worldImg the world img
	 * @param xpos the xpos
	 * @param ypos the ypos
	 */
	public WorldItemLabel(String parentItemID, BufferedImage worldImg, int xpos, int ypos) {
		super();
		this.parentItemID = parentItemID;
		setBounds(xpos, ypos, worldImg.getWidth(), worldImg.getHeight());
		setIcon(new ImageIcon(worldImg));
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		//GameEvent event = new GameEvent(GameEventType.WORLD_ITEM_CLICKED);
		//event.addDataToPacket(parentItemID);
		//Game.eventHandler.registerEvent(event);
		setVisible(false);
		setEnabled(false);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
