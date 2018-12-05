package grayroom.scene.hotspot;

import java.awt.Color;

import grayroom.Game;
import grayroom.event.GameEvent;
import grayroom.event.GameEventType;
import grayroom.event.HotspotEventType;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemGiverHotspot.
 */
public class ItemGiverHotspot extends Hotspot {
	
	/** The item to give ID. */
	private String itemToGiveID;
	
	/** The ID of the scene without the item in it. */
	private String sceneWithNoObject;
	
	/**
	 * Instantiates a new item giver hotspot.
	 *
	 * @param color the color
	 * @param itemToGiveID the item to give ID
	 * @param sceneWithNoObject the scene without the item in it
	 */
	public ItemGiverHotspot(Color color, String itemToGiveID, String sceneWithNoObject) {
		super(color);
		this.itemToGiveID = itemToGiveID;
		this.sceneWithNoObject = sceneWithNoObject;
	}
	
	/* (non-Javadoc)
	 * @see grayroom.scene.hotspot.Hotspot#activate()
	 */
	@Override
	public void activate() {
		GameEvent event = new GameEvent(GameEventType.HOTSPOT_TRIGGERED);
		event.addDataToPacket(HotspotEventType.HOTSPOT_GIVE_ITEM, this);
		Game.eventHandler.registerEvent(event);
		
	}
	
	/**
	 * Gets the item to give ID.
	 *
	 * @return the item to give ID
	 */
	public String getItemToGiveID() {
		return itemToGiveID;
	}
	
	/**
	 * Gets the ID of the scene without the item in it.
	 *
	 * @return the ID of the scene without the item in it.
	 */
	public String getSceneWithNoObject() {
		return sceneWithNoObject;
	}

}
