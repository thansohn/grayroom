package grayroom.ui.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import grayroom.Game;
import grayroom.event.GameEvent;
import grayroom.event.GameEventType;
import grayroom.scene.Direction;
import grayroom.scene.Scene;
import grayroom.scene.hotspot.Hotspot;

import grayroom.util.Constants;
import grayroom.util.UtilityFunctions;


// TODO: Auto-generated Javadoc
/**
 * The Class NavigationPanel.
 */
public class NavigationPanel extends JPanel implements MouseListener, MouseMotionListener {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The north button. */
	private JButton northButton = new JButton();
	
	/** The south button. */
	private JButton southButton = new JButton();
	
	/** The west button. */
	private JButton westButton = new JButton();
	
	/** The east button. */
	private JButton eastButton = new JButton();
	
	/** The scene panel. */
	private JPanel scenePanel = new JPanel();
	
	/** The scene image. */
	private JLabel sceneImage = new JLabel("SceneImage");
	
	/** The active scene. */
	private Scene activeScene;
	
	/**
	 * Instantiates a new navigation panel.
	 *
	 * @param scene the scene
	 */
	public NavigationPanel(Scene scene) {
		setLayout(new BorderLayout());
		setFocusable(true);
		
		OverlayLayout overlay = new OverlayLayout(scenePanel);
		
		add(northButton, BorderLayout.NORTH);
		add(southButton, BorderLayout.SOUTH);
		add(westButton, BorderLayout.WEST);
		add(eastButton, BorderLayout.EAST);
		
		scenePanel.setBackground(Color.CYAN);
		scenePanel.setLayout(overlay);
		scenePanel.setPreferredSize(new Dimension(Constants.SCENE_IMAGE_WIDTH, Constants.SCENE_IMAGE_HEIGHT));
		scenePanel.add(sceneImage);
		scenePanel.addMouseListener(this);
		scenePanel.addMouseMotionListener(this);
		
		add(scenePanel, BorderLayout.CENTER);
		
		northButton.setIcon(new ImageIcon(UtilityFunctions.loadResourceImage(Constants.UI_ICON_DIRECTORY + "arrowUp.png")));
		northButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createDirectionalButtonEvent(Direction.NORTH);
			}
		});
		
		southButton.setIcon(new ImageIcon(UtilityFunctions.loadResourceImage(Constants.UI_ICON_DIRECTORY + "arrowdown.png")));
		southButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createDirectionalButtonEvent(Direction.SOUTH);
			}
		});
		
		eastButton.setIcon(new ImageIcon(UtilityFunctions.loadResourceImage(Constants.UI_ICON_DIRECTORY + "arrowright.png")));
		eastButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createDirectionalButtonEvent(Direction.EAST);
			}
		});
		
		westButton.setIcon(new ImageIcon(UtilityFunctions.loadResourceImage(Constants.UI_ICON_DIRECTORY + "arrowleft.png")));
		westButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createDirectionalButtonEvent(Direction.WEST);
			}
		});
		
		redraw(scene);
	}
	
	/**
	 * Redraw the panel with the new scene.
	 *
	 * @param scene the scene
	 */
	public void redraw(Scene scene) {
		activeScene = scene;
		sceneImage.setIcon(new ImageIcon(scene.getSceneImage()));
		
		if (scene.isSceneNearby(Direction.NORTH)) {
			northButton.setEnabled(true);
		} else { 
			northButton.setEnabled(false);
		}
		
		if (scene.isSceneNearby(Direction.SOUTH)) {
			southButton.setEnabled(true);
		} else { 
			southButton.setEnabled(false);
		}
		
		if (scene.isSceneNearby(Direction.WEST)) {
			westButton.setEnabled(true);
		} else { 
			westButton.setEnabled(false);
		}
		
		if (scene.isSceneNearby(Direction.EAST)) {
			eastButton.setEnabled(true);
		} else { 
			eastButton.setEnabled(false);
		}
		
		revalidate();
		repaint();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		Object source = e.getSource();
		
		if (source == scenePanel) {
			Point p = e.getPoint();
			Hotspot hotspot = activeScene.matchHotspotAt(p);
			
			if (hotspot != null) {
				hotspot.activate();
			}
			
		}	
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Changes the cursor when the mouse is moved over a hotspot.
	 *
	 * @param e the MouseEvent
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		Object source = e.getSource();
		if (source == scenePanel) {
			Point p = e.getPoint();
			BufferedImage hotspotMap = activeScene.getHotspotMap();
			
			
			if (hotspotMap != null) {
				Color color = UtilityFunctions.convertToRGB(hotspotMap.getRGB((int)p.getX(), (int)p.getY()));
			
				if (!color.equals(Constants.EMPTY_SPACE)) {
					scenePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				} else {
					scenePanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}
		}
	}

	/**
	 * Creates a new directional button event and adds it to the event handler.
	 *
	 * @param direction the direction of the button clicked
	 */
	public void createDirectionalButtonEvent(Direction direction) {
		GameEvent event = new GameEvent(GameEventType.DIRECTIONAL_BUTTON_CLICKED);
		event.addDataToPacket(direction);
		Game.eventHandler.registerEvent(event);
	}
	
}
