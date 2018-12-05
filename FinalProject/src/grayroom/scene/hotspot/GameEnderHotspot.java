package grayroom.scene.hotspot;

import java.awt.Color;

import grayroom.Game;
import grayroom.event.GameEvent;
import grayroom.event.GameEventType;
import grayroom.event.HotspotEventType;

// TODO: Auto-generated Javadoc
/**
 * The Class GameEnderHotspot. This hotspot ends the game.
 */
public class GameEnderHotspot extends Hotspot {

	/**
	 * Instantiates a new game ender hotspot.
	 *
	 * @param color the color
	 */
	public GameEnderHotspot(Color color) {
		super(color);
	}

	/* (non-Javadoc)
	 * @see grayroom.scene.hotspot.Hotspot#activate()
	 */
	@Override
	public void activate() {
		GameEvent event = new GameEvent(GameEventType.HOTSPOT_TRIGGERED);
		event.addDataToPacket(HotspotEventType.HOTSPOT_ENDGAME);
		Game.eventHandler.registerEvent(event);
	}

}
