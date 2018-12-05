package grayroom.event;

import java.util.Queue;
import java.util.LinkedList;

// TODO: Auto-generated Javadoc
/**
 * The Class GameEventHandler.
 */
public class GameEventHandler {
	
	/** The event queue. */
	private Queue<GameEvent> eventQueue;
	
	/**
	 * Instantiates a new game event handler.
	 */
	public GameEventHandler() {
		eventQueue = new LinkedList<>();
	}
	
	/**
	 * Register event.
	 *
	 * @param event the GameEvent to register
	 */
	public void registerEvent(GameEvent event) {
		eventQueue.add(event);
	}
	
	/**
	 * Gets the next event in the queue.
	 *
	 * @return the next event in the queue
	 */
	public GameEvent getNextEvent() {
		GameEvent event;
		
		if (isEmpty()) {
			return new GameEvent(GameEventType.WAIT_FOR_EVENT);
		} else {			
			event = eventQueue.poll();
			return event;
		}
	}
	
	/**
	 * Checks if the event queue empty.
	 *
	 * @return true, if the queue is, indeed, empty
	 */
	public boolean isEmpty() {
		return eventQueue.isEmpty();
	}
}
