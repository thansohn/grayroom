package grayroom.event;

import java.util.Map.Entry;

import grayroom.Game;

import grayroom.item.GameItems;
import grayroom.item.Inventory;
import grayroom.item.Item;

import grayroom.scene.Direction;
import grayroom.scene.Scene;
import grayroom.scene.SceneManager;
import grayroom.scene.hotspot.*;

import grayroom.ui.MainWindow;
import grayroom.ui.VictoryWindow;

// TODO: Auto-generated Javadoc
/**
 * The Class EventInterpreter.
 */
public class EventInterpreter {
	
	/**
	 * This methods interprets the event passed to it and modifies all parameters
	 * accordingly.
	 *
	 * @param e the GameEvent to interpret
	 * @param sceneManager the scene manager
	 * @param mainWindow the main window
	 * @param inventory the player's inventory
	 * @param gameItems the game items
	 */
	public static void interpretEvent(GameEvent e, 
			SceneManager sceneManager,
			MainWindow mainWindow,
			Inventory inventory,
			GameItems gameItems) 
	{
		switch(e.getType()) {
			case HOTSPOT_TRIGGERED:
				interpretHotspotEvent(e, sceneManager, mainWindow, inventory, gameItems);
				break;
			case DIRECTIONAL_BUTTON_CLICKED:
				//move to the scene in whatever direction has been passed
				Direction dir = (Direction)e.getPacket().get(0);
				Scene activeScene = sceneManager.getActiveScene();
				String nearbySceneID = activeScene.getSceneNearby(dir);
				
				if (nearbySceneID != null) {
					sceneManager.setActiveScene(nearbySceneID);
				}
				
				mainWindow.redrawSceneNav(sceneManager.getActiveScene());
				mainWindow.redrawInventory(inventory);
				mainWindow.displayMessage(" ");
				break;
			case INVENTORY_ITEM_CLICKED:
				//pick up an item
				String itemID = (String)e.getPacket().get(0);
				
				Item item = inventory.getItem(itemID);
				
				if (item != null) {
					inventory.setActiveItem(item);
				}
				
				break;
			case WORLD_ITEM_CLICKED:
				//NOT USED
				//originally intended to fire on the activation of a WorldItemLabel, but I
				//decided not to implement WorldItemLabel for the initial release. The code
				//will remain here in case I decide.
				String worldItemID = (String)e.getPacket().get(0);
				
				Item worldItem = gameItems.getItem(worldItemID);
				
				if (worldItem != null) {
					inventory.pickupItem(worldItem);
				}
				
				mainWindow.redrawInventory(inventory);
				break;
			case STOP:
				Thread.currentThread().interrupt();
			default:
				break;
		}
	}
	
	/**
	 * Interpret any event tagged as a hotspot_triggered event.
	 *
	 * @param e the event to interpret
	 * @param sceneManager the scene manager
	 * @param mainWindow the main window
	 * @param inventory the inventory
	 * @param itemList the item list
	 */
	private static void interpretHotspotEvent(GameEvent e, 
			SceneManager sceneManager,
			MainWindow mainWindow,
			Inventory inventory,
			GameItems itemList) 
	{
		
		HotspotEventType hotspotEvent = (HotspotEventType)e.getPacket().get(0);
		switch (hotspotEvent) {
			case HOTSPOT_MOVETOSCENE:
				/*
				 * Moves to the next scene.
				 */
				String sceneID = (String)e.getPacket().get(1);
				sceneManager.moveTo(sceneID);
				inventory.nullifyActiveItem();
				mainWindow.redrawInventory(inventory);
				mainWindow.redrawSceneNav(sceneManager.getActiveScene());
				mainWindow.displayMessage(" ");
				break;
			
			case HOTSPOT_SWAPSCENE:
				/*
				 * Swaps the identifier of one scene with the data of another.
				 */
				String srcScene = (String)e.getPacket().get(1);
				String destScene = (String)e.getPacket().get(2);
				sceneManager.swapScene(srcScene, destScene);
				mainWindow.redrawInventory(inventory);
				mainWindow.redrawSceneNav(sceneManager.getScene(srcScene));
				mainWindow.displayMessage(" ");
				break;
			case HOTSPOT_DISPLAYMESSAGE:
				/*
				 * Displays a message in the window
				 */
				String message = (String)e.getPacket().get(1);
				mainWindow.displayMessage(message);
				break;
			case HOTSPOT_UNLOCKABLE:
				/*
				 * "Unlocks" a hotspot if the player has the right key item active. When 
				 * unlocking is successful, that hotspot is permanently unlocked and the 
				 * key item is removed from the player's inventory. 
				 */
				UnlockableHotspot h = (UnlockableHotspot)e.getPacket().get(1);
				
				if (inventory.getActiveItem() != null) {
					h.tryUnlock(inventory.getActiveItem().getID());
					
					if (h.isLocked() == false) {
						inventory.dropItem(inventory.getActiveItem());
						mainWindow.redrawInventory(inventory);
						mainWindow.displayMessage(h.getUnlockedMsg());
					} else {
						mainWindow.displayMessage(h.getLockedMsg());
					}
				} else {
					mainWindow.displayMessage(h.getLockedMsg());
				}
			
				break;
			case HOTSPOT_REQUEST_ITEM:
				/*
				 * Functions similarly to HOTSPOT_UNLOCKABLE, insofar as it requires a
				 * key item before it comes active. However, once active, it will not only
				 * swap the current scene with another one, but multiple other scenes.
				 *
				 * This scene change is immediate.
				 */
				ItemRequesterHotspot itemRequester = (ItemRequesterHotspot)e.getPacket().get(1);
				String itemRequestedID = itemRequester.getItemRequested();
				
				if (inventory.getActiveItem() != null &&
					inventory.getActiveItem().getID().equals(itemRequestedID)) {
						inventory.dropItem(inventory.getActiveItem());
						mainWindow.redrawInventory(inventory);
						
						for (Entry<String, String> scene : itemRequester.getScenesToChange().entrySet()) {
							sceneManager.swapScene(scene.getKey(), scene.getValue());
						}

						mainWindow.redrawSceneNav(sceneManager.getActiveScene());
				} else {
					mainWindow.displayMessage("Something goes here.");
				}
						
				break;	
			case HOTSPOT_GIVE_ITEM:
				/*
				 * Gives an item to the player, then swap the active scene to a scene that no longer has that
				 * item visible. 
				 */
				
				ItemGiverHotspot giveItemHotspot = (ItemGiverHotspot)e.getPacket().get(1);
				
				Item newItem = itemList.getItem(giveItemHotspot.getItemToGiveID());
				
				if (newItem != null) {
					inventory.pickupItem(newItem);
					mainWindow.displayMessage("I picked up the " + newItem.getName() + ".");
				}
				
				mainWindow.redrawInventory(inventory);
				sceneManager.swapScene(sceneManager.getActiveScene().getID(), giveItemHotspot.getSceneWithNoObject());
				mainWindow.redrawSceneNav(sceneManager.getActiveScene());
				break;
			case HOTSPOT_PUZZLE_PIECE:
				/* 
				 * Attempts to solve a puzzle by getting the attempted solution from the scene's local
				 * values, and comparing it to the solver's completed solution.
				 */
				PuzzlePieceHotspot piece = (PuzzlePieceHotspot)e.getPacket().get(1);
				Scene activeScene = sceneManager.getActiveScene();
				
				mainWindow.displayMessage("I pressed a button labeled \"" + piece.getPieceDesc() + ".\"");
				
				if (activeScene.getLocalValue(piece.getPuzzleIdentifer()) != null) {
					activeScene.appendToLocalValue(piece.getPuzzleIdentifer(), piece.getPieceCode());
				} else {
					activeScene.setLocalValue(piece.getPuzzleIdentifer(), piece.getPieceCode());
				}
				break;
			case HOTSPOT_PUZZLE_SOLVER:
				/*
				 * Attempts to solve a puzzle by getting the attempted solution from the scene's local
				 * values and comparing it to the solver's completed solution.
				 */
				PuzzleSolverHotspot solver = (PuzzleSolverHotspot)e.getPacket().get(1);
				String puzzleIdentifier = solver.getPuzzleLocalValueIdentifer();
				String puzzleCurrentSolution = sceneManager.getActiveScene().getLocalValue(puzzleIdentifier);
				
				if (puzzleCurrentSolution != null) {
					solver.tryToSolve(puzzleCurrentSolution);
					
					if (solver.isSolved()){
						mainWindow.displayMessage("It worked!");
					}
					else {
						sceneManager.getActiveScene().removeLocalValue(puzzleIdentifier);
						mainWindow.displayMessage("Hmm... the code is wrong.");
					}
				} else {
					mainWindow.displayMessage("Hmm... the code is wrong.");
				}
				
				break;
			case HOTSPOT_ENDGAME:
				/*
				 * Trigger the gameover window.
				 */
				VictoryWindow victoryWindow = new VictoryWindow();
				mainWindow.setVisible(false);
				
				
				victoryWindow.setLocationRelativeTo(null);
				victoryWindow.setVisible(true);
				
				GameEvent event = new GameEvent(GameEventType.STOP);
				Game.eventHandler.registerEvent(event);
				break;
			default:
				break;
		}
	}
}
