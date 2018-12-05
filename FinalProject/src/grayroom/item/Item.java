package grayroom.item;

import java.awt.image.BufferedImage;

import grayroom.util.Constants;
import grayroom.util.UtilityFunctions;

// TODO: Auto-generated Javadoc
/**
 * The Class Item.
 */
public class Item {
	
	/** The id. */
	private String id;
	
	/** The name. */
	private String name;
	
	/** What the item looks like in the inventory. */
	private BufferedImage invImage;
	
	/** What the item looks like in the world. (NEVER USED) */
	private BufferedImage worldImage;
	
	/**
	 * Instantiates a new generic item object.
	 */
	public Item() {
		id = "generic";
		name = "Generic Item";
		invImage = UtilityFunctions.loadResourceImage(Constants.ITEM_DIRECTORY + "invgeneric.png");
		worldImage = UtilityFunctions.loadResourceImage(Constants.ITEM_DIRECTORY + "invgeneric.png");
	}
	
	/**
	 * Instantiates a new item object.
	 *
	 * @param id the item's id
	 * @param name the item's name
	 * @param invImagePath path to the item's inventory image
	 * @param worldImagePath path to the item's world image
	 */
	public Item(String id, String name, String invImagePath, String worldImagePath) {
		this.id = id;
		this.name = name;
		invImage = UtilityFunctions.loadResourceImage(Constants.ITEM_DIRECTORY + invImagePath);
		worldImage = UtilityFunctions.loadResourceImage(Constants.ITEM_DIRECTORY + worldImagePath);
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getID() {return id;}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {return name;}
	
	/**
	 * Gets the item's inventory image.
	 *
	 * @return the item's inventory image
	 */
	public BufferedImage getImage() {return invImage;}
	
	/**
	 * Get the item's world image. (NEVER USED)
	 *
	 * @return the item's world image
	 */
	public BufferedImage worldImage() {return worldImage; }
}

