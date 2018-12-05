package grayroom.scene;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import grayroom.scene.hotspot.Hotspot;
import grayroom.util.Constants;
import grayroom.util.UtilityFunctions;

// TODO: Auto-generated Javadoc
/**
 * The Class Scene.
 */
public class Scene {
	
	/** The scene id. */
	private String id;
	
	/** The scene's image. */
	private BufferedImage image;
	
	/** The scene's hotspot map. */
	private BufferedImage hotspotMap;
	
	/** The list of all the hotspots. */
	private ArrayList<Hotspot> hotspots;
	
	/** The nearby (adjacent) scenes. */
	private Map<Direction, String> nearbyScenes;
	
	/** The dictionary containing all the scene's local data (namely to store 
	 * puzzle attempts/solutions). */
	private Map<String, String> localValues;
	
	/**
	 * Instantiates a new scene object.
	 *
	 * @param id the id
	 * @param image the image
	 * @param hotspotMap the hotspot map
	 */
	public Scene(String id, String image, String hotspotMap) {
		this.id = id;
		this.image = UtilityFunctions.loadResourceImage(Constants.SCENE_DIRECTORY + image);
		this.hotspotMap = UtilityFunctions.loadResourceImage(Constants.SCENE_DIRECTORY + hotspotMap);
		hotspots = new ArrayList<Hotspot>();
		nearbyScenes = new HashMap<Direction, String>();
		localValues = new HashMap<String, String>();
	}
	
	/**
	 * Gets the scene's id.
	 *
	 * @return the scene's id
	 */
	public String getID() { return id; }
	
	/**
	 * Sets the scene id.
	 *
	 * @param id the new id
	 */
	public void setID(String id) { this.id = id; }
	
	/**
	 * Gets the scene image.
	 *
	 * @return the scene image
	 */
	public BufferedImage getSceneImage() { return image; }
	
	/**
	 * Gets the hotspot map.
	 *
	 * @return the hotspot map
	 */
	public BufferedImage getHotspotMap() { return hotspotMap; }
	
	/**
	 * Adds a new hotspot to the scene.
	 *
	 * @param hotspot the hotspot to add
	 */
	public void addHotspot(Hotspot hotspot) {
		hotspots.add(hotspot);
	}
	
	/**
	 * Sets the scene nearby (adjacent).
	 *
	 * @param dir the direction the adjacent scene is in
	 * @param sceneID the scene ID
	 */
	public void setSceneNearby(Direction dir, String sceneID) {
		nearbyScenes.put(dir, sceneID);
	}
	
	/**
	 * Gets the scene (adjacent).
	 *
	 * @param dir the direction the adjacent scene is in
	 * @return the scene in the specified direction
	 */
	public String getSceneNearby(Direction dir) {
		return nearbyScenes.get(dir);
	}
	
	/**
	 * Checks if a scene is nearby (adjacent).
	 *
	 * @param dir the direction the adjacent scene is supposedly in
	 * @return true, if scene is adjacent
	 */
	public boolean isSceneNearby(Direction dir) {
		return (nearbyScenes.get(dir) != null);
	}
	
	
	/**
	 * Gets the pixel color at a certain point on the hotspotMap and
	 * check to see if any registered hotspots have that specific color.
	 *
	 * @param p the location of the mouse cursor
	 * @return the hotspot found (if there is one)
	 */
	public Hotspot matchHotspotAt(Point p) {
		int x = (int)p.getX();
		int y = (int)p.getY();
	
		Color c = UtilityFunctions.convertToRGB(hotspotMap.getRGB(x, y));
		
		if (!c.equals(Constants.EMPTY_SPACE)) {
			for (Hotspot h : hotspots) {
				if (c.equals(h.getColor())) {
					return h;
				}
			}
		}
		
		
		return null;
	}
	
	/**
	 * Gets the contents of a local value with the identifier "valName."
	 *
	 * @param valIdent the identifier of the local value requested
	 * @return the contents of the local value
	 */
	public String getLocalValue(String valIdent) {
	return localValues.get(valIdent);
	}
	
	/**
	 * Sets the contents of a local value with the name (identifier) "valName."
	 *
	 * @param valIdent a value identifer
	 * @param valData the data to add to the local value dictionary
	 */
	public void setLocalValue(String valIdent, String valData) {
		localValues.put(valIdent, valData);
	}
	
	/**
	 * Removes the local value with the identifer "valIdent".
	 *
	 * @param valIdent the value identifer
	 */
	public void removeLocalValue(String valIdent) {
		localValues.remove(valIdent);
	}
	
	/**
	 * Appends data to a pre-existing local value.
	 *
	 * @param valIdent the value identifer
	 * @param data the data to append to the local value
	 */
	public void appendToLocalValue(String valIdent, String data) {
		if (localValues.containsKey(valIdent)) {
			
			StringBuilder sb = new StringBuilder(localValues.get(valIdent));
			sb.append(data);
		
			localValues.put(valIdent, sb.toString());
		} else {
			System.err.println("LocalValue with identifier \"" + valIdent + "\" does not exist.");
		}
	}
}
