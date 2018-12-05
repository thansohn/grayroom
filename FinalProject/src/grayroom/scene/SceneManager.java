package grayroom.scene;

import java.util.Map;

import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class SceneManager.
 */
public class SceneManager {
	
	/** The scene list. */
	private Map<String, Scene> sceneList;
	
	/** The active scene. */
	private Scene activeScene;
	
	/**
	 * Instantiates a new scene manager.
	 */
	public SceneManager() {
		sceneList = new HashMap<String, Scene>();
	}
	
	/**
	 * Initializes a new scene manager with the first scene to be loaded;.
	 */
	public void init() {
		activeScene = sceneList.get("start");
	}
	
	/**
	 * Adds a scene.
	 *
	 * @param sceneID the scene ID
	 * @param scene the scene
	 */
	public void addScene(String sceneID, Scene scene) {
		sceneList.put(sceneID, scene);
	}
	
	/**
	 * Removes a scene.
	 *
	 * @param sceneID the scene ID
	 */
	public void removeScene(String sceneID) {
		sceneList.remove(sceneID);
	}
	
	/**
	 * Gets the active scene.
	 *
	 * @return the active scene
	 */
	public Scene getActiveScene() {
		return activeScene;
	}
	
	/**
	 * Gets the scene with the provided ID.
	 *
	 * @param sceneID the scene ID
	 * @return the requested scene
	 */
	public Scene getScene(String sceneID) {
		return sceneList.get(sceneID);
	}
	
	/**
	 * Replace one scene with the data from another scene, so the first
	 * scene effectively becomes the second.
	 *
	 * @param fromSceneID the from scene ID
	 * @param toSceneID the ID of the scene to swap to
	 */
	public void swapScene(String fromSceneID, String toSceneID) {
		//If the active scene is the one being swapped, immediately
		//switch the active scene to the scene being swapped to.
		if (activeScene.getID().equals(fromSceneID)) {
			setActiveScene(toSceneID);
		}
		
		if (hasScene(fromSceneID) && hasScene(toSceneID)) {
			sceneList.put(fromSceneID, getScene(toSceneID));
		}
		
	}
	
	/**
	 * Checks if a scene exists.
	 *
	 * @param sceneID the scene ID
	 * @return true, if the scene exists
	 */
	public boolean hasScene(String sceneID) {
		return sceneList.containsKey(sceneID);
	}
	
	/**
	 * Sets the active scene.
	 *
	 * @param sceneID the new active scene
	 */
	public void setActiveScene(String sceneID) {
		activeScene = sceneList.get(sceneID);
	}
	
	/**
	 * Changes the active scene, while saving the original one.
	 *
	 * @param sceneID the scene ID to change to.
	 */
	public void moveTo(String sceneID) {
		if (hasScene(sceneID)) {
			sceneList.put(activeScene.getID(), activeScene);
			setActiveScene(sceneID);
		}
	}
}
