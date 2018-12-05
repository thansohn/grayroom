package grayroom.item;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import grayroom.util.UtilityFunctions;

// TODO: Auto-generated Javadoc
/**
 * The Class GameItems.
 */
public class GameItems {
	
	/** The item list. */
	private Map<String, Item> itemList;
	
	/**
	 * Instantiates a new GameItems object.
	 */
	public GameItems() {
		itemList = new HashMap<String, Item>();
	}
	
	/**
	 * Gets the item with the specified ID.
	 *
	 * @param itemID the item ID
	 * @return the item
	 */
	public Item getItem(String itemID) {
		return itemList.get(itemID);
	}
	
	/**
	 * Load the item JSON.
	 *
	 * @param filename the filename
	 */
	public void loadItemJSON(String filename) {
		JSONParser parser = new JSONParser();
		String jsonFile = UtilityFunctions.loadResourceTextFile(filename);
		JSONObject jsonObj;
		
		try {
			jsonObj = (JSONObject)parser.parse(jsonFile);
			
			JSONArray itemData = (JSONArray)jsonObj.get("item");
			
			for (Object item: itemData) {
				JSONObject itemObj = (JSONObject) item;
				Item myItem = parseItem(itemObj);
				itemList.put(myItem.getID(), myItem);
			}
		} catch (ParseException e) {
			System.err.println("Program could not parse JSON file");
			e.printStackTrace();
		}
	}
	
	/**
	 * Parses the item's JSON.
	 *
	 * @param itemObj the item JSONObject to parse
	 * @return the new item
	 */
	private Item parseItem(JSONObject itemObj) {
		String id = (String)itemObj.get("id");
		String name = (String)itemObj.get("name");
		String invImgPath = (String)itemObj.get("invimage");
		String worldImgPath = (String)itemObj.get("worldimage");
		
		return new Item(id, name, invImgPath, worldImgPath);
	}
}
