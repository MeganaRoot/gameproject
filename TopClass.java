import java.awt.EventQueue;
import javax.swing.JFrame;

public class TopClass extends JFrame {

	private static final int WIDTH_SCREEN = 800;
	private static final int HEIGHT_SCREEN = 680;

	public TopClass() {

		buildFrame();
	}

	/**
	 * Method for constructing the JFrame and adding content
	 */
	private void buildFrame() {

		this.add(new GameScreen(WIDTH_SCREEN, HEIGHT_SCREEN));
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
