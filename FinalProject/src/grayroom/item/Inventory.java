package grayroom.item;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Inventory.
 */
public class Inventory {
	
	/** The items collected. */
	private ArrayList<Item> itemsCollected = new ArrayList<Item>();
	
	/** The active item. */
	private Item activeItem;
	
	/**
	 * Instantiates a new inventory object.
	 */
	public Inventory() {
		activeItem = null;
	}
	
	/**
	 * Gets the item with the specified ID.
	 *
	 * @param itemID the item ID
	 * @return the item
	 */
	public Item getItem(String itemID) {
		for (Item item : itemsCollected) {
			if (item.getID() == itemID) {
				return item;
			}
		}
		
		return null;
	}
	
	/**
	 * Gets the currently "activated" item (i.e., the item the player has selected 
	 * in MainWindow's InventoryPanel).
	 *
	 * @return the active item
	 */
	public Item getActiveItem() { return activeItem; }
	
	/**
	 * Sets the currently "activated" item (i.e., the item the player has selected 
	 * in MainWindow's InventoryPanel).
	 *
	 * @param item the item to set active
	 */
	public void setActiveItem(Item item) {
			if (itemsCollected.contains(item)) {
				activeItem = item;
			}
	}
	
	/**
	 * Sets the currently "activated" item (i.e., the item the player has selected 
	 * in MainWindow's InventoryPanel). 
	 *
	 * @param itemID the ID of the item to set active
	 */
	public void setActiveItem(String itemID) {
		for (Item item : itemsCollected) {
			if (item.getID() == itemID) {
				setActiveItem(item);
				break;
			}
		}
	}
	
	/**
	 * Adds an item to the inventory.
	 *
	 * @param item the item
	 */
	public void pickupItem(Item item) {
		if (!itemsCollected.contains(item)) {
			itemsCollected.add(item);
		}
	}
	
	/**
	 * Removes an item from the inventory.
	 *
	 * @param item the item
	 */
	public void dropItem(Item item) {
		if (item.equals(activeItem)) {
			activeItem = null;
		}
		
		itemsCollected.remove(item);
	}
	
	/**
	 * Removes an item from the inventory.
	 *
	 * @param itemID the ID of the item to remove
	 */
	public void dropItem(String itemID) {
		Item itemToRemove = null;
		
		for (Item item : itemsCollected) {
			if (item.getID() == itemID) {
				itemToRemove = item;
			}
		}
		
		itemsCollected.remove(itemToRemove);
	}
	
	/**
	 * Gets the list of all the items currently in the inventory.
	 *
	 * @return the list of items collected
	 */
	public ArrayList<Item> getAllItems() { return itemsCollected; }
	
	/**
	 * Checks if the inventory is empty.
	 *
	 * @return true, if the inventory is, indeed, empty
	 */
	public boolean isEmpty() {
		return itemsCollected.isEmpty();
	}

	/**
	 * set activeItem to null, because MainWindow's InventoryPanel resets
	 * the active InventoryButton every time the buttons are redrawn.
	 */
	public void nullifyActiveItem() {
		activeItem = null;
	}
}
