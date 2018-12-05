package grayroom.scene.hotspot;

import java.awt.Color;

import grayroom.Game;
import grayroom.event.GameEvent;
import grayroom.event.GameEventType;
import grayroom.event.HotspotEventType;

// TODO: Auto-generated Javadoc
/**
 * The Class PuzzlePieceHotspot.
 */
public class PuzzlePieceHotspot extends Hotspot {
	
	/**  The puzzle's value identifier in the scene's local value dictionary. */
	private String puzzleIdentifer;
	
	/** The puzzle code. */
	private String puzzleCode;
	
	/** The piece description. */
	private String pieceDesc;
	
	/**
	 * Instantiates a new puzzle piece hotspot.
	 *
	 * @param color the color
	 * @param pieceDesc the piece description
	 * @param puzzleIdentifer the puzzle local value identifier
	 * @param puzzleCode the puzzle code
	 */
	public PuzzlePieceHotspot(Color color, String pieceDesc, String puzzleIdentifer, String puzzleCode) {
		super(color);
		this.puzzleIdentifer = puzzleIdentifer;
		this.puzzleCode = puzzleCode;
		this.pieceDesc = pieceDesc;
	}
	
	/**
	 * Gets the puzzle identifier.
	 *
	 * @return the puzzle identifier
	 */
	public String getPuzzleIdentifer() { return puzzleIdentifer; }
	
	/**
	 * Gets the puzzle piece's "code" character.
	 *
	 * @return the piece code
	 */
	public String getPieceCode() { return puzzleCode; }
	
	/**
	 * Gets the piece description.
	 *
	 * @return the piece description.
	 */
	public String getPieceDesc() { return pieceDesc; }
	
	/* (non-Javadoc)
	 * @see grayroom.scene.hotspot.Hotspot#activate()
	 */
	@Override
	public void activate() {
		GameEvent event = new GameEvent(GameEventType.HOTSPOT_TRIGGERED);
		event.addDataToPacket(HotspotEventType.HOTSPOT_PUZZLE_PIECE, this);
		Game.eventHandler.registerEvent(event);
	}
}
