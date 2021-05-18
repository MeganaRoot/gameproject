import javax.swing.*;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

/** Panel class. Paints GUI, dog sprite, and obstacles, controls game loop controls game logic. */


public class PlayGameScreen extends JPanel implements ActionListener {
    //default reference ID
    private static final long serialVersionUID = 1L;

    private static final int HEIGHT_SCREEN = 680;
    private static final int DOG_STARTING_X = 148;
    private final int DOG_STARTING_Y = HEIGHT_SCREEN / 2 - HEIGHT_DOG;
    private static final int WIDTH_DOG = 120;
    private static final int HEIGHT_DOG = 75;

    //global variables
    private final int sw;
    private final int sh;

    private final ArrayList < Obstacle > obstacles = new ArrayList < Obstacle > ();
    private final Dog dog;
 
    private boolean variableLoop = true;
    private boolean displayGameOver = false;
    private int score = 0;
    private final Timer timer;
    private final int DELAY = 200;

   /** This list is used to store obstacles before start of the game */
    private final int[][] obstacleXYPosition = {

        {
            650,
            0

        },

        {
            650,
            500
        },
        {
            1000,
            0
        },

        {
            1000,
            350
        },

        {
            1350,
            0
        },

        {
            1350,
            350
        }

    };

    /** This list is used to randomly select the height of the obstacles */
    private final int[] obstaclesHeightList = {
        200,
        225,
        150,
        175,
        100,
        75,
        215,
        160
    };


    /** Creates a play game screen object
    and assigns a screen width, a screen height. Starts the timer which calls the action performed method.
    */

    public PlayGameScreen(int sw, int sh) {
        this.sw = sw;
        this.sh = sh;
        /* Register Dog key adapter to JPanel action listener*/
        addKeyListener(new DogAdapter());
         /* set property of JPanel*/
        setFocusable(true);
        setDoubleBuffered(true);
        /*create new dog*/
        dog = new Dog(WIDTH_DOG, HEIGHT_DOG);
        dog.setX(DOG_STARTING_X);
        dog.setY(DOG_STARTING_Y);
        /*set up initial obstacles */
        initListObstacles();
        /* intializes the timer and starts the timer. This will automatically call the action performed method in periodic
        intervals.
         */
        timer = new Timer(DELAY, this);
        timer.start();

    }

    /** controls what is drawn on the JPanel.
     Utilizes a graphics object and draws/paints using that object.
     Draws the sky, the obstacles, and the game over sign.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


            // sky
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, sw, sh);
            g.setColor(Color.BLACK);
            g.fillRect(0, 601, sw, sh);
            g.drawImage(dog.getDog(), dog.getX(), dog.getY(), this);

            for (Obstacle l : obstacles) {
                g.drawImage(l.getLog(), l.getX(), l.getY(), this);
            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("Calibri", Font.BOLD, 30));
            g.drawString("Score: " + score / 2, 350, 640);
        /* at the end of game display a red rectangle with white letters saying Game Over */
        if ( displayGameOver) {
            System.out.println("Game Over");
            g.setColor(Color.red);
            g.fillRect(275, 200, sw/4, sh/8);
            g.setColor(Color.white);
            g.setFont(new Font("Calibri", Font.BOLD, 30));
            g.drawString("Game Over ", 290, 255);
            timer.stop();

        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();

    }

    /** Executes the game loop, shifts the log from right to left, moves the dog, and turns on collision detection.
     * Invokes the paintComponent() method using the repaint() method.
     * */

    public void actionPerformed(ActionEvent e) {
        if (variableLoop) {

            updateListLogsRightLeft();
            moveDog();
            collisionDetectors();
        }

        repaint();
    }


    /** Initalizes and line up obstacles in JPanel constructor. A one time call.
     */
    private void initListObstacles() {
       // listLogs 
        for (int i = 0; i < obstacleXYPosition.length; i++) {
            Random r = new Random();
            int randomNumber = r.nextInt(obstaclesHeightList.length);
            int h = obstaclesHeightList[randomNumber];
            String image_name;
            if (i%2 == 0) {
                image_name = "rain";


            } else {
                image_name = "log";

            }
            Obstacle tempLog = new Obstacle(150, h, image_name);
            tempLog.setX(obstacleXYPosition[i][0]);
            int new_y=obstacleXYPosition[i][1];
            if (obstacleXYPosition[i][1] > 300)
             { new_y=obstacleXYPosition[i][1]+(600-(obstacleXYPosition[i][1]+h));}
            tempLog.setY(new_y);
            obstacles.add(tempLog);
        }

    }
    /**
     Whenever any obstacle runs on the screen and the current obstacle is 3 on the obstacles list, then
     create two more obstacles and add them to the obstacles list.
     This method will be called repeatedly after removing the obstacles
     that run out of screen, ie x position is going below 0.
     */
   private void addNewObstacles() {
        int size = obstacles.size();
        if (size < 3 )
        {
          for (int i = 0; i < 2; i++) {
            Random r = new Random();
            int randomNumber = r.nextInt(obstaclesHeightList.length);
            int h = obstaclesHeightList[randomNumber];
            String image_name;
              if (i%2 == 0) {
                  image_name = "rain";


              } else {
                  image_name = "log";

              }
            Obstacle tempLog = new Obstacle(150, h, image_name);
            tempLog.setX(obstacleXYPosition[i][0]);
            int new_y=obstacleXYPosition[i][1];
            if (obstacleXYPosition[i][1] > 300)
             { new_y=obstacleXYPosition[i][1]+(600-(obstacleXYPosition[i][1]+h));}
            tempLog.setY(new_y);
            obstacles.add(tempLog);
         }
        }

    }


    /**
     Moves the dog on a downward trajectory until it hits the 600 y location.
     */
    private void moveDog() {
        if (dog.getY() > 600 - 1 - (dog.getHeight() + 8)) {
            variableLoop = false;
            displayGameOver = true;

        } else {
            dog.setY(dog.getY() + 25);

        }

    }

    /**
     Moves the obstacle in the screen from right to left.

     Whenever the x location of the obstacle goes below the x location of the dog, then increment the score by 1.
     Since both up and down obstacles cross the dog at the
     same time, the score is always incremented by 2.
     During the display time in the paint component function, the score will be divided by 2.

     */
    private void updateListLogsRightLeft() {

        if (obstacles.isEmpty()) {

            variableLoop = false;
            return;
        }

        for (int i = 0; i < obstacles.size(); i++) {

            Obstacle l = obstacles.get(i);

            int tmp = l.getX() +l.getWidth();

            if ( tmp < dog.getX()) {
                //System.out.println(i + ":" + l.getX() + ":"+ tmp+" : " + dog.getX());
                score = score + 1;
                //System.out.println("Score" + "" + score);

            }
            if (l.getX() >= 0) {
                l.setX(l.getX() - 10);
            } else {
                obstacles.remove(i);
                addNewObstacles();
            }

        }
        //System.out.println("Score" + "" + score);

    }

    /**
     Find the rectangle for dog and find the rectangle for each obstacle.
     If the obstacle rectangle intersects with dog rectangle, set variable loop to false.
     */

    public void collisionDetectors() {

        Rectangle dr = dog.getRectangle();

        for (Obstacle l: obstacles) {

            Rectangle lr = l.getRectangle();

            if (dr.intersects(lr)) {

                variableLoop = false;
                displayGameOver = true;
            }
        }
    }


    /** The class KeyAdapter is an abstract (adapter) class for receiving keyboard events. 
        All methods of this class are empty. This class is convenience class for creating listener objects.
        Used to ensure that when the user presses the space bar (given that the game loop is still running) the dog moves upwards by 70 pixels.
     
      */
    private class DogAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE && variableLoop) {
                dog.setY(dog.getY() - 70);
            }

        }
    }

}