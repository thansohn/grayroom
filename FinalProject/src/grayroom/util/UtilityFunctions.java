package grayroom.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.imageio.ImageIO;

// TODO: Auto-generated Javadoc
/**
 * The Class UtilityFunctions.
 */
public class UtilityFunctions {
	
	/**
	 * Load a resource image.
	 *
	 * @param filename the filename of the resource image
	 * @return the new BufferedImage object
	 */
	public static BufferedImage loadResourceImage(String filename) {
		BufferedImage image = null;
	
		try {
			InputStream is = Constants.CLASSLOADER.getResourceAsStream(filename);
			image = ImageIO.read(is);
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			System.err.println("Image read failed or filepath \"" + filename + "\" is invalid.");
		}
	
		return image;
	}
	
	/**
	 * Convert a color from an integer to RGB.
	 *
	 * @param color the color as an integer
	 * @return the color as a Color object
	 */
	public static Color convertToRGB(int color) {
		return new Color(color, true);
	}
	
	/**
	 * Load a resource text file.
	 *
	 * @param filename the filename of the resource text file
	 * @return the contents of the file
	 */
	/* file reader code adapted from:
	 * https://community.oracle.com/blogs/pat/2004/10/23/stupid-scanner-tricks */
	public static String loadResourceTextFile(String filename) {
		InputStream is = Constants.CLASSLOADER.getResourceAsStream(filename);
		String data = new Scanner(is, "UTF-8").useDelimiter("\\A").next();
			
		return data;
			
	}
}
