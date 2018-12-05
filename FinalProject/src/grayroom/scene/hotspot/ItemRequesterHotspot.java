package grayroom.scene.hotspot;

import java.awt.Color;
import java.util.Map;

import grayroom.Game;
import grayroom.event.GameEvent;
import grayroom.event.GameEventType;
import grayroom.event.HotspotEventType;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemRequesterHotspot.
 */
public class ItemRequesterHotspot extends Hotspot {
	
	/** The item requested ID. */
	private String itemRequestedID;
	
	/**  A map of all the scenes (what they change into, the scenes to change) that change when this hotspot recieves the item it requests. */
	private Map<String, String> scenesToChange;
	
	/**
	 * Instantiates a new item requester hotspot.
	 *
	 * @param color the color
	 * @param itemRequestedID the item requested ID
	 * @param scenesToChange the scenes to change
	 */
	public ItemRequesterHotspot(Color color, String itemRequestedID, Map<String, String> scenesToChange) {
		super(color);
		this.scenesToChange = scenesToChange;
		this.itemRequestedID = itemRequestedID;
	}
	
	/**
	 * Adds the scenes to change.
	 *
	 * @param to the to
	 * @param from the from
	 */
	public void addScenesToChange(String to, String from) {
		scenesToChange.put(to, from);
	}
	
	/**
	 * Gets the item requested.
	 *
	 * @return the item requested
	 */
	public String getItemRequested() { return itemRequestedID; }
	
	/**
	 * Gets the scenes to change.
	 *
	 * @return the scenes to change
	 */
	public Map<String, String> getScenesToChange() { return scenesToChange; }

	
	/* (non-Javadoc)
	 * @see grayroom.scene.hotspot.Hotspot#activate()
	 */
	@Override
	public void activate() {
		GameEvent event = new GameEvent(GameEventType.HOTSPOT_TRIGGERED);
		event.addDataToPacket(HotspotEventType.HOTSPOT_REQUEST_ITEM, this);
		Game.eventHandler.registerEvent(event);

	}

}
