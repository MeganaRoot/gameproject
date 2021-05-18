// Names: Tanvi Pedireddi & Megana Kumar

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

public class GameScreen extends JPanel implements ActionListener {
	// default reference ID
	private static final long serialVersionUID = 1L;

	private static final int HEIGHT_SCREEN = 680;
	private static final int DOG_STARTING_X = 148;
	private final int DOG_STARTING_Y = HEIGHT_SCREEN / 2 - HEIGHT_DOG;
	private static final int WIDTH_DOG = 120;
	private static final int HEIGHT_DOG = 75;

	// global variables
	private final int sw;
	private final int sh;

	private final ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private final Dog dog;

	private boolean variableLoop = true;
	private boolean displayGameOver = false;
	private int score = 0;
	private final Timer timer;
	private final int DELAY = 200;

	/** This list is used to stage obstacles before start of the game */
	private final int[][] obstacleXYPosition = {

			{ 650, 0

			},

			{ 650, 500 }, { 1000, 0 },

			{ 1000, 350 },

			{ 1350, 0 },

			{ 1350, 350 }

	};

	/** This list is used in random selection height of the obstacle */
	private final int[] obstaclesHeightList = { 200, 225, 150, 175, 100, 75, 215, 160 };

	/**
	 * Creates a play game screen object and assigns a screen width, a screen
	 * height, and a boolean telling whether it is a splash screen or not.
	 */

	public GameScreen(int sw, int sh) {
		this.sw = sw;
		this.sh = sh;
		/* Register Dog key adapter to JPanel action listener */
		addKeyListener(new DogAdapter());
		/* set property of JPanel */
		setFocusable(true);
		setDoubleBuffered(true);
		/* create new dog */
		dog = new Dog(WIDTH_DOG, HEIGHT_DOG);
		dog.setX(DOG_STARTING_X);
		dog.setY(DOG_STARTING_Y);
		/* set up initial obstacles */
		initListObstacles();
		/*
		 * intialize the timer and start the timer. This will automatically call the
		 * action performed in periodical interval.
		 */
		timer = new Timer(DELAY, this);
		timer.start();

	}

	/**
	 * controls what is drawn on the JPanel by calling this method; utilizes a
	 * graphics object and draws/paints using that object
	 * 
	 */

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
		g.drawString("Score: " + score / 2, 15, 625);
		/* on end of game display red rectangle with white letter saying Game Over */
		if (displayGameOver) {
			System.out.println("Game Over");
			g.setColor(Color.red);
			g.fillRect(275, 200, sw / 4, sh / 8);
			g.setColor(Color.white);
			g.setFont(new Font("Calibri", Font.BOLD, 30));
			g.drawString("Game Over ", 290, 255);
			timer.stop();

		}
		Toolkit.getDefaultToolkit().sync();
		g.dispose();

	}

	public void actionPerformed(ActionEvent e) {
		if (variableLoop) {

			updateListLogsRightLeft();
			moveDog();
			collisionDetectors();
		}

		repaint();
	}

	/**
	 * Initalize and line up obstacles in JPanel constructor.
	 */
	private void initListObstacles() {
		// listLogs
		for (int i = 0; i < obstacleXYPosition.length; i++) {
			Random r = new Random();
			int randomNumber = r.nextInt(obstaclesHeightList.length);
			int h = obstaclesHeightList[randomNumber];
			String image_name;
			if (i % 2 == 0) {
				image_name = "rain";

			} else {
				image_name = "log";

			}
			Obstacle tempLog = new Obstacle(150, h, image_name);
			tempLog.setX(obstacleXYPosition[i][0]);
			int new_y = obstacleXYPosition[i][1];
			if (obstacleXYPosition[i][1] > 300) {
				new_y = obstacleXYPosition[i][1] + (600 - (obstacleXYPosition[i][1] + h));
			}
			tempLog.setY(new_y);
			obstacles.add(tempLog);
		}

	}

	/**
	 * whenever any obstacle run of screen and current available obstacle is only 3
	 * on the obstacles list then create two more obstacle and add to the obstacles
	 * list
	 */
	private void addListLogs() {
		int size = obstacles.size();
		if (size < 3) {
			for (int i = 0; i < 2; i++) {
				Random r = new Random();
				int randomNumber = r.nextInt(obstaclesHeightList.length);
				int h = obstaclesHeightList[randomNumber];
				String image_name;
				if (i % 2 == 0) {
					image_name = "rain";

				} else {
					image_name = "log";

				}
				Obstacle tempLog = new Obstacle(150, h, image_name);
				tempLog.setX(obstacleXYPosition[i][0]);
				int new_y = obstacleXYPosition[i][1];
				if (obstacleXYPosition[i][1] > 300) {
					new_y = obstacleXYPosition[i][1] + (600 - (obstacleXYPosition[i][1] + h));
				}
				tempLog.setY(new_y);
				obstacles.add(tempLog);
			}
		}

	}

	/**
	 * move the dog downward trajectory till hit the 600 y location, user has to
	 * press space bar to move upward trajectory. It is called from paint component
	 * function. paint component is invoked from repaint call at the action
	 * performed function.action performed function is invoked from PlayGameScreen
	 * constructor. It calls swing.timer
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
	 * move the obstacle in the screen from right left. It is called from paint
	 * component function. paint component is invoked from repaint call at the
	 * action performed function. action performed function is invoked from
	 * PlayGameScreen constructor. It calls swing.timer
	 * 
	 * whenever x location of obstacle goes below the x location of the dog , then
	 * increment the score by 1. since both up and down obstacles cross the dog at
	 * the s same time , the score is always incremented by 2. During display time
	 * in the paint component function, score will be divided by 2.
	 * 
	 */
	private void updateListLogsRightLeft() {

		if (obstacles.isEmpty()) {

			variableLoop = false;
			return;
		}

		for (int i = 0; i < obstacles.size(); i++) {

			Obstacle l = obstacles.get(i);

			int tmp = l.getX() + l.getWidth();

			if (tmp < dog.getX()) {
				score = score + 1;

			}
			if (l.getX() >= 0) {
				l.setX(l.getX() - 10);
			} else {
				obstacles.remove(i);
				addListLogs();
			}

		}

	}

	/**
	 * Find the rectangle for dog and find the rectangle for each obstacle. If
	 * obstacle rectangle , intersects with dog rectangle set variable loop to
	 * false. This variable loop is used in action performed to stop the loop.
	 */

	public void collisionDetectors() {

		Rectangle dr = dog.getRectangle();

		for (Obstacle l : obstacles) {

			Rectangle lr = l.getRectangle();

			if (dr.intersects(lr)) {

				variableLoop = false;
				displayGameOver = true;
			}
		}
	}

	/**
	 * The class KeyAdapter is an abstract (adapter) class for receiving keyboard
	 * events. All methods of this class are empty. This class is convenience class
	 * for creating listener objects.
	 * 
	 */
	private class DogAdapter extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE && variableLoop) {
				dog.setY(dog.getY() - 70);
			}

		}
	}

}
