// Names: Tanvi Pedireddi & Megana Kumar

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

public class Dog {

	private Image coco;
	private int xloc = 0, yloc = 0;

	/**
	 * Creates a dog object and scales image to given width and height
	 */

	public Dog(int initW, int initH) {
		ImageIcon ii = new ImageIcon("resources/coco.png");
		coco = ii.getImage();

	}

	/**
	 * Scales dog image based on the given dimensions @ parameter width -> desired
	 * width of the dog @ parameter height -> desired height of the dog
	 * 
	 */

	public void scaleDog(int width, int height) {
		coco = coco.getScaledInstance(width, height, Image.SCALE_SMOOTH);

	}

	/**
	 * returns the dog image
	 */

	public Image getDog() {

		return coco;

	}

	/**
	 * returns the dog image's current width
	 */

	public int getWidth() {

		try {
			return coco.getWidth(null);
		}

		catch (Exception e) {
			return -1;

		}
	}

	/**
	 * returns the dog image's current height
	 */

	public int getHeight() {

		try {
			return coco.getHeight(null);
		}

		catch (Exception e) {
			return -1;

		}
	}

	/**
	 * sets the x coordinate of the upper left hand corner of the dog image
	 */

	public void setX(int x) {
		xloc = x;

	}

	/**
	 * gets the x coordinate of the upper left hand corner of the dog image
	 */

	public int getX() {

		return xloc;

	}

	/**
	 * sets the y coordinate of the upper left hand corner of the dog image
	 */

	public void setY(int y) {

		yloc = y;

	}

	/**
	 * gets the y coordinate of the upper left hand corner of the dog image
	 */

	public int getY() {

		return yloc;

	}

	/**
	 * gives back a rectangle outlining the dog
	 */

	public Rectangle getRectangle() {

		// needs to return a Rectangle or it will not compile

		return (new Rectangle(xloc, yloc, coco.getWidth(null), coco.getHeight(null)));

	}

	/**
	 * returns the buffered image which represents the dog's image object
	 */
	public BufferedImage getBuffImg() {

		// needs to return a BufferedImage or it will not compile

		BufferedImage BI = new BufferedImage(coco.getWidth(null), coco.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics grphics = BI.getGraphics();
		grphics.drawImage(coco, 0, 0, null);
		grphics.dispose();
		return BI;
	}

}
