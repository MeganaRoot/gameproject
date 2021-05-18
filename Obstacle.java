// Names: Tanvi Pedireddi & Megana Kumar

import java.awt.Rectangle;
import java.awt.Image;
import javax.swing.ImageIcon;


public class Obstacle {
   
   private Image img;
   private int xloc = 0, yloc = 0;
   
   
   /** Creates an obstacle object
    * stores a log or rain object based on given image name
   scales image to given width and height
   */
   
   public Obstacle(int initW, int initH, String image_name) {
      

          ImageIcon ii = new ImageIcon("resources/" + image_name + ".png");
          img = ii.getImage();
          scaleLog(initW, initH);
   
   }

   /**  
   Scales image based on the given dimensions
   @ parameter width -> desired width of the image
   @ parameter height -> desired height of the image
   
   */

   public void scaleLog(int width, int height) {
      img  = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
   }
   
   /**  
   returns the image
   */
   
   public Image getLog() {
      
      return img;
   
   }
   
   /**  
  returns the image's current width
   */
   
   public int getWidth() {
   
         return img.getWidth(null);
   }
   
   /**  
   returns the image's current height
   */
   
   public int getHeight() {
   
         return img.getHeight(null);
   }
   
   /**  
   sets the x coordinate of the upper left hand corner of the image
   */
   
   public void setX(int x) {
   
      xloc = x;
   
   }
   
   /**  
   gets the x coordinate of the upper left hand corner of the image
   */
   
   public int getX() {
   
         return xloc;
   
   }
   
   /**  
   sets the y coordinate of the upper left hand corner of the image
   */
   
   public void setY(int y) {
   
      yloc = y;
   
   }
   
   /**  
   gets the y coordinate of the upper left hand corner of the image
   */
   
   public int getY() {
      
      return yloc;
   
   }
   
   /**  
      gives back a rectangle outlining the image
   */
   
   public Rectangle getRectangle() {
   
      return (new Rectangle(xloc, yloc, img.getWidth(null), img.getHeight(null)));
   }
   
   


}
