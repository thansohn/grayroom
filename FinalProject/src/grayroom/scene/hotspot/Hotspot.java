package grayroom.scene.hotspot;

import java.awt.Color;

// TODO: Auto-generated Javadoc
/**
 * The Class Hotspot.
 */
public abstract class Hotspot {

	/** The color. */
	private Color color;

	/**
	 * Instantiates a new default hotspot.
	 */
	public Hotspot() {
		color = Color.BLACK;
	}

	/**
	 * Instantiates a new hotspot.
	 *
	 * @param color the color
	 */
	public Hotspot(Color color) {
		this.color = color;
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Abstract method Activate. Called whenever a hotspot is triggered.
	 */
	public abstract void activate();
}
