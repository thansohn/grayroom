package grayroom.scene.hotspot;

import java.awt.Color;

import grayroom.Game;
import grayroom.event.GameEvent;
import grayroom.event.GameEventType;
import grayroom.event.HotspotEventType;

// TODO: Auto-generated Javadoc
/**
 * The Class UnlockableHotspot.
 */
public class UnlockableHotspot extends Hotspot {
	
	/** The key ID. */
	String keyID;
	
	/** The message to show when the hotspot is unlocked. */
	String unlockedMsg;
	
	/** The message to show when the hotspot is locked. */
	String lockedMsg;
	
	/** The scene to change to when the hotspot is unlocked. */
	String sceneOnUnlockID;
	
	/** Whether the hotspot is locked or not. */
	boolean isLocked;
	
	/**
	 * Instantiates a new unlockable hotspot.
	 *
	 * @param color the color
	 * @param keyID the key ID
	 * @param sceneOnUnlockID the scene scene to change to when the hotspot is unlocked
	 * @param unlockedMsg The message to show when the hotspot is unlocked
	 * @param lockedMsg The message to show when the hotspot is locked
	 */
	public UnlockableHotspot(Color color, String keyID, String sceneOnUnlockID, String unlockedMsg, String lockedMsg)
	{
		super(color);
		this.keyID = keyID;
		this.sceneOnUnlockID = sceneOnUnlockID;
		this.unlockedMsg = unlockedMsg;
		this.lockedMsg = lockedMsg;
		
		isLocked = true;
	}

	/**
	 * Checks if the hotspot is locked.
	 *
	 * @return true, if is locked
	 */
	public boolean isLocked() { return isLocked; }
	
	/**
	 * Gets the message to show when the hotspot is locked.
	 *
	 * @return the message to show when the hotspot is locked
	 */
	public String getLockedMsg() { return lockedMsg; }
	
	/**
	 * Gets the message to show when the hotspot is unlocked.
	 *
	 * @return message to show when the hotspot is unlocked
	 */
	public String getUnlockedMsg() { return unlockedMsg; }
	
	/**
	 * Try to unlock the hotspot.
	 *
	 * @param itemID the item ID to match for a key
	 */
	public void tryUnlock(String itemID) {
		if (keyID.equals(itemID)) { 
			isLocked = false; 
		}
	}
	
	
	/* (non-Javadoc)
	 * @see grayroom.scene.hotspot.Hotspot#activate()
	 */
	@Override
	public void activate() {
		GameEvent event = new GameEvent(GameEventType.HOTSPOT_TRIGGERED);
		
		if (isLocked == true) {	
			event.addDataToPacket(HotspotEventType.HOTSPOT_UNLOCKABLE, this, false);
		} else {
			event.addDataToPacket(HotspotEventType.HOTSPOT_MOVETOSCENE, sceneOnUnlockID);
		}
		
		Game.eventHandler.registerEvent(event);
	}
}