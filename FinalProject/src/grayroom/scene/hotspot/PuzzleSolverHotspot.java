package grayroom.scene.hotspot;

import java.awt.Color;

import grayroom.Game;
import grayroom.event.GameEvent;
import grayroom.event.GameEventType;
import grayroom.event.HotspotEventType;

// TODO: Auto-generated Javadoc
/**
 * The Class PuzzleSolverHotspot.
 */
public class PuzzleSolverHotspot extends Hotspot {
	
	/** The puzzle's value identifer in the scene's local value dictionary. */
	private String puzzleLocalValueIdentifer;
	
	/** The puzzle solution. */
	private String puzzleSolution;
	
	/** Whether the puzzle is solved or not. */
	private boolean isSolved;
	
	/** The scene on solved ID. */
	private String sceneOnSolvedID;
	
	/**
	 * Instantiates a new puzzle solver hotspot.
	 *
	 * @param color the color
	 * @param puzzleLocalValueIdentifer the puzzle local value identifer
	 * @param puzzleSolution the puzzle solution
	 * @param sceneOnSolvedID the scene on solved ID
	 */
	public PuzzleSolverHotspot(Color color, String puzzleLocalValueIdentifer, String puzzleSolution, String sceneOnSolvedID) {
		super(color);
		this.puzzleLocalValueIdentifer = puzzleLocalValueIdentifer;
		this.puzzleSolution = puzzleSolution;
		this.sceneOnSolvedID = sceneOnSolvedID;
		isSolved = false;
	}
	
	/**
	 * Gets the puzzle value identifier in the scene's local value dictionary.
	 *
	 * @return the puzzle local value identifier
	 */
	public String getPuzzleLocalValueIdentifer() { return puzzleLocalValueIdentifer; }
	
	/**
	 * Gets the puzzle solution.
	 *
	 * @return the puzzle solution
	 */
	public String getPuzzleSolution() { return puzzleSolution; }
	
	/**
	 * Try to solve.
	 *
	 * @param currentSolution the current solution attempted
	 */
	public void tryToSolve(String currentSolution) {
		if (puzzleSolution.equals(currentSolution)) {
			isSolved = true;
		}
	}

	/**
	 * Checks if the puzzle is solved.
	 *
	 * @return true, if the puzzle is, indeed, solved
	 */
	public boolean isSolved() {
		return isSolved;
	}

	/* (non-Javadoc)
	 * @see grayroom.scene.hotspot.Hotspot#activate()
	 */
	@Override
	public void activate() {
		GameEvent event = new GameEvent(GameEventType.HOTSPOT_TRIGGERED);
		
		if (isSolved == false) {	
			event.addDataToPacket(HotspotEventType.HOTSPOT_PUZZLE_SOLVER, this);
		} else {
			event.addDataToPacket(HotspotEventType.HOTSPOT_MOVETOSCENE, sceneOnSolvedID);
		}
		Game.eventHandler.registerEvent(event);
	}

}
