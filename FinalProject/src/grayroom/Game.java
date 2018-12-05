package grayroom;

import grayroom.scene.SceneLoader;
import grayroom.scene.SceneManager;

import grayroom.ui.MainWindow;

import grayroom.util.Constants;

import grayroom.event.EventInterpreter;
import grayroom.event.GameEventHandler;
import grayroom.event.GameEvent;
import grayroom.event.GameEventType;
import grayroom.item.GameItems;
import grayroom.item.Inventory;

// TODO: Auto-generated Javadoc
/**
 * The Game class. Where the magic happens.
 */
public class Game {
	
	/** The scene manager. */
	private SceneManager sceneManager;
	
	/** The player's inventory. */
	private Inventory playerInventory;
	
	/** The game items. */
	private GameItems gameItems;
	
	/** The main UI window. */
	private MainWindow uiWindow;
	
	/** The event handler. */
	public static GameEventHandler eventHandler;
	
	/**
	 * Instantiates a new game.
	 */
	public Game() {
		sceneManager = new SceneManager();
		playerInventory = new Inventory();
		eventHandler = new GameEventHandler();
		gameItems = new GameItems();
		
		gameItems.loadItemJSON(Constants.ITEM_DIRECTORY + "items.json");
		SceneLoader.loadSceneJSON(Constants.SCENE_DIRECTORY + "scenes.json", sceneManager);
		sceneManager.init();
	}
	
	/**
	 * Starts the game, displaying the main window and turning on the event handler.
	 */
	public void start() {
		  java.awt.EventQueue.invokeLater(new Runnable() {
	          public void run() {
	        	  uiWindow = new MainWindow(sceneManager.getActiveScene());
	        	  uiWindow.setLocationRelativeTo(null);
	        	  uiWindow.setVisible(true);
	          }
	    });
		
		//runs the event handler continuously in another thread, letting it interpret events
		//until told to stop
		Thread eventThread = new Thread(new Runnable() {	
			public void run() {
				GameEvent ev = eventHandler.getNextEvent();
					
				while (ev.getType() != GameEventType.STOP) {
					EventInterpreter.interpretEvent(ev, 
													sceneManager, 
													uiWindow,
													playerInventory, 
													gameItems);
					ev = eventHandler.getNextEvent();
				}
			}
		});
		
		eventThread.setDaemon(true);
		eventThread.start();
	}
}
