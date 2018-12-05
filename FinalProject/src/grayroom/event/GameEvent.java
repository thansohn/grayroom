package grayroom.event;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class GameEvent.
 */
public class GameEvent {
	
	/** The event type. */
	private GameEventType eventType;
	
	/** The data packet. */
	private ArrayList<Object> dataPacket;
	
	/**
	 * Instantiates a new game event.
	 *
	 * @param type the type
	 */
	public GameEvent(GameEventType type) {
		eventType = type;
		dataPacket = new ArrayList<Object>();
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public GameEventType getType() { return eventType; }

	/**
	 * Adds the data to packet.
	 *
	 * @param data the data
	 */
	public void addDataToPacket(Object... data) {
		for (Object d : data) {
			dataPacket.add(d);
		}
	}
	
	/**
	 * Gets the packet.
	 *
	 * @return the packet
	 */
	public ArrayList<Object> getPacket() {
		return dataPacket;
	}
}
