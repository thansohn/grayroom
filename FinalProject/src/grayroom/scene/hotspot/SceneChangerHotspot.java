package grayroom.scene.hotspot;

import java.awt.Color;

import grayroom.Game;
import grayroom.event.GameEvent;
import grayroom.event.GameEventType;
import grayroom.event.HotspotEventType;

// TODO: Auto-generated Javadoc
/**
 * The Class SceneChangerHotspot.
 */
public class SceneChangerHotspot extends Hotspot{
	
	/** The scene to change to. */
	private String sceneToChangeTo;
	
	/**
	 * Instantiates a new scene changer hotspot.
	 *
	 * @param color the color
	 * @param sceneToSwitchTo the scene to switch to
	 */
	public SceneChangerHotspot(Color color, String sceneToSwitchTo) {
		super(color);
		this.sceneToChangeTo = sceneToSwitchTo;
	}
	
	/* (non-Javadoc)
	 * @see grayroom.scene.hotspot.Hotspot#activate()
	 */
	public void activate() {
		GameEvent event = new GameEvent(GameEventType.HOTSPOT_TRIGGERED);
		event.addDataToPacket(HotspotEventType.HOTSPOT_MOVETOSCENE, sceneToChangeTo);
		Game.eventHandler.registerEvent(event);
	}
}
