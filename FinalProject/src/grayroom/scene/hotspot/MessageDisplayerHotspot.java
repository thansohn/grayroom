package grayroom.scene.hotspot;

import java.awt.Color;

import grayroom.Game;
import grayroom.event.GameEvent;
import grayroom.event.GameEventType;
import grayroom.event.HotspotEventType;

// TODO: Auto-generated Javadoc
/**
 * The Class MessageDisplayerHotspot.
 */
public class MessageDisplayerHotspot extends Hotspot {
	
	/** The message. */
	private String message;
	
	/**
	 * Instantiates a new message displayer hotspot.
	 *
	 * @param color the color
	 * @param message the message
	 */
	public MessageDisplayerHotspot(Color color, String message) {
		super(color);
		this.message = message;
	}
	
	/* (non-Javadoc)
	 * @see grayroom.scene.hotspot.Hotspot#activate()
	 */
	public void activate() {
		GameEvent event = new GameEvent(GameEventType.HOTSPOT_TRIGGERED);
		event.addDataToPacket(HotspotEventType.HOTSPOT_DISPLAYMESSAGE, message);
		Game.eventHandler.registerEvent(event);
	}
}

