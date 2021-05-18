// Names: Tanvi Pedireddi & Megana Kumar
import java.awt.EventQueue;
import javax.swing.JFrame;


/** Driver class. Runs the main function and creates the panel by calling the panel class. */

public class TopClass extends JFrame {

    private static final int WIDTH_SCREEN = 800;
    private static final int HEIGHT_SCREEN = 680;

    public TopClass() {

        buildFrame();
    }
    /**
	 * Method for constructing the JFrame and adding content. Calls the panel class, PlayGameScreen(), to create
     * the panel.
	 */

    private void buildFrame() {

        this.add(new PlayGameScreen(WIDTH_SCREEN, HEIGHT_SCREEN));
        this.pack();
        this.setResizable(false);
        this.setTitle("Coco's Adventure");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH_SCREEN, HEIGHT_SCREEN);
    }

	/**
	 * Method invoked while jar file is running
	 * 
	 * @param args
	 */
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            TopClass tc = new TopClass();
            tc.setVisible(true);
        });
    }
}