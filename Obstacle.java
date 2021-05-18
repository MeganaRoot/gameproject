// Names: Tanvi Pedireddi & Megana Kumar

import java.awt.Rectangle;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Obstacle {

	private Image log;
	private int xloc = 0, yloc = 0;

	/**
	 * Creates a log object and scales image to given width and height
	 */

	public Obstacle(int initW, int initH, String image_name) {

		ImageIcon ii = new ImageIcon("resources/" + image_name + ".png");
		log = ii.getImage();
		scaleLog(initW, initH);

	}

	/**
	 * Scales log image based on the given dimensions @ parameter width -> desired
	 * width of the log @ parameter height -> desired height of the log
	 * 
	 */

	public void scaleLog(int width, int height) {
		log = log.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}

	/**
	 * returns the log image
	 */

	public Image getLog() {

		return log;

	}

	/**
	 * returns the log image's current width
	 */

	public int getWidth() {

		return log.getWidth(null);
	}

	/**
	 * returns the log image's current height
	 */

	public int getHeight() {

		return log.getHeight(null);
	}

	/**
	 * sets the x coordinate of the upper left hand corner of the log image
	 */

	public void setX(int x) {

		xloc = x;

	}

	/**
	 * gets the x coordinate of the upper left hand corner of the log image
	 */

	public int getX() {

		return xloc;

	}

	/**
	 * sets the y coordinate of the upper left hand corner of the log image
	 */

	public void setY(int y) {

		yloc = y;

	}

	/**
	 * gets the y coordinate of the upper left hand corner of the log image
	 */

	public int getY() {

		return yloc;

	}

	/**
	 * gives back a rectangle outlining the log
	 */

	public Rectangle getRectangle() {

		return (new Rectangle(xloc, yloc, log.getWidth(null), log.getHeight(null)));
	}

}
