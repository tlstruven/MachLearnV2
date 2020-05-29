package machineLearning;
import javax.swing.JFrame;

public class Program {

	public static void main(String[] args) {
		JFrame f = new JFrame("MACHINE LEARNING"); // Names your window at top
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close = exit
		Canvas c = new Canvas(); // Adds a drawing Canvas, extended from JPanel
		f.getContentPane().add(c); // Adds the Canvas (JPanel) to the JFrame
		f.setSize(700, 700); // Sets window size (x, y)
		f.setLocationRelativeTo(null); // Centers window on screen
		f.setVisible(true); // Makes your window visible (normally starts hidden)
	} // end main()
} // end class Game