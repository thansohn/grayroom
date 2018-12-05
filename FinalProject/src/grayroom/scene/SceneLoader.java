package grayroom.scene;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.toIntExact;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import grayroom.scene.hotspot.*;
import grayroom.util.UtilityFunctions;

// TODO: Auto-generated Javadoc
/**
 * The Class SceneLoader.
 */
public class SceneLoader {
	
	/**
	 * Load the scene JSON file.
	 *
	 * @param filename the filename of the JSON file
	 * @param sm the scene manager to load the scenes into
	 */
	public static void loadSceneJSON(String filename, SceneManager sm) {
		JSONParser parser = new JSONParser();
		String jsonFile = UtilityFunctions.loadResourceTextFile(filename);
		JSONObject jsonObj;
		
		try {
			jsonObj = (JSONObject)parser.parse(jsonFile);
			
			JSONArray sceneData = (JSONArray)jsonObj.get("scene");
			
			for (Object s : sceneData) {
				JSONObject sceneJSON = (JSONObject) s;
				Scene scene = parseSceneData(sceneJSON);
				sm.addScene(scene.getID(), scene);
			}
		} catch (ParseException e) {
			System.err.println("Program could not parse JSON file");
			e.printStackTrace();
		}
	}
	
	/**
	 * Parses a JSONObject containing a scene's data.
	 *
	 * @param data the JSONObject containing the scene's data
	 * @return a new scene object with the data added.
	 */
	private static Scene parseSceneData(JSONObject data) {
		String sceneID = (String)data.get("id");
		String sceneImage = (String)data.get("image");
		String sceneMap = (String)data.get("map");

		Scene newScene = new Scene(sceneID, sceneImage, sceneMap);
		
		JSONArray hotspotData = (JSONArray)data.get("hotspots");
		
		//parse the hotspot data
		if (!hotspotData.isEmpty()) {
			for (Object hs : hotspotData) {
				JSONObject hotspotJSON = (JSONObject) hs;
				if (hs != null) {
					newScene.addHotspot(parseHotspotData(hotspotJSON));
				}
			}
		}
		
		//get the adjacent scenes
		JSONObject nearbyJSON = (JSONObject)data.get("nearby");
			
		if (nearbyJSON.get("north") != null) {
			newScene.setSceneNearby(Direction.NORTH, (String)nearbyJSON.get("north"));
		}
			
		if (nearbyJSON.get("south") != null) {
			newScene.setSceneNearby(Direction.SOUTH, (String)nearbyJSON.get("south"));
		}
			
		if (nearbyJSON.get("east") != null) {
			newScene.setSceneNearby(Direction.EAST, (String)nearbyJSON.get("east"));
		}
			
		if (nearbyJSON.get("west") != null) {
			newScene.setSceneNearby(Direction.WEST, (String)nearbyJSON.get("west"));
		}
		
		return newScene;
	}
	
	/**
	 * Parses the hotspot data.
	 *
	 * @param hotspotData a JSONObject containing the hotspot data
	 * @return a new hotspot object
	 */
	private static Hotspot parseHotspotData(JSONObject hotspotData)
	{
		String hotspotType = (String) hotspotData.get("type");
		JSONArray colorArray = (JSONArray) hotspotData.get("color");
		JSONObject hotspotSpecifics = (JSONObject) hotspotData.get("data");
		Hotspot h;
		
		int r = toIntExact((long)colorArray.get(0));
		int g = toIntExact((long)colorArray.get(1));
		int b = toIntExact((long)colorArray.get(2));
		
		Color color = new Color(r, g, b);
		
		switch (hotspotType) {
			case "sceneswitch":
				String sceneToSwitch = (String)hotspotSpecifics.get("switch_to");
				h = new SceneChangerHotspot(color, sceneToSwitch);
				break;
			case "displaymessage":
				String msg = (String)hotspotSpecifics.get("message");
				h = new MessageDisplayerHotspot(color, msg);
				break;
			case "unlockable":
				String unlockMsg = (String)hotspotSpecifics.get("unlock_message");
				String lockedMsg = (String)hotspotSpecifics.get("locked_message");
				String sceneOnUnlock = (String)hotspotSpecifics.get("scene_on_unlock");
				String keyID = (String)hotspotSpecifics.get("key_id");
				h = new UnlockableHotspot(color, keyID, sceneOnUnlock, unlockMsg, lockedMsg);
				break;
			case "puzzlepiece":
				String puzzleIdentifer = (String)hotspotSpecifics.get("identifer");
				String pieceDesc = (String)hotspotSpecifics.get("description");
				String code = (String)hotspotSpecifics.get("code");
				
				h = new PuzzlePieceHotspot(color, pieceDesc, puzzleIdentifer, code);
				break;
			case "puzzlesolver":
				String solverIdentifer = (String)hotspotSpecifics.get("identifer");
				String solution = (String)hotspotSpecifics.get("solution");
				String sceneIDOnSolve = (String)hotspotSpecifics.get("scene_change_on_solve");
				
				h = new PuzzleSolverHotspot(color, solverIdentifer, solution, sceneIDOnSolve);
				break;
			case "giveitem":
				String itemToGive = (String)hotspotSpecifics.get("item_id");
				String sceneToChangeTo = (String)hotspotSpecifics.get("scene_change_on_give");
				
				h = new ItemGiverHotspot(color, itemToGive, sceneToChangeTo);
				break;
			case "itemrequest":
				String itemRequested = (String)hotspotSpecifics.get("request");
				JSONArray scenesJSON = (JSONArray)hotspotSpecifics.get("scenes_to_change");
				Map<String, String> scenesToChange = new HashMap<String, String>();
		
				for (Object scenes : scenesJSON) {
					JSONObject scene = (JSONObject)scenes;
					scenesToChange.put((String)scene.get("from"), (String)scene.get("to"));
				}
				
				h = new ItemRequesterHotspot(color, itemRequested, scenesToChange);
				break;
			case "endgame":
				h = new GameEnderHotspot(color);
				break;
			default:
				h = null;
				break;
		}
		
		return h;
	}
		
}

