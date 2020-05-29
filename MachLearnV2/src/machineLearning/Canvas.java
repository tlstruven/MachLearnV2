package machineLearning;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	// MEMBER CONSTANTS

	// MEMBER VARIABLES
	ImageChar img;

	// CONSTRUCTOR(S)
	public Canvas() {
		this.setLayout(null); // uses coordinate system layout
		img = new ImageChar();
//		NeuralNet nn = new NeuralNet();
//		nn.beginTeaching(1000);
	}

	// MEMBER METHODS

	// paintComponent is a visualization tool, showing you a page of random
	// characters and how the characters fit within the BufferedImage
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		setBackground(new Color(60, 60, 60));
		g2.setColor(Color.WHITE);

		for (int x = 100; x < 600; x += 40) {
			for (int y = 100; y < 600; y += 40) {
				g2.drawRect(x, y, ImageChar.SIDE, ImageChar.SIDE);
				img.drawRandomChar(g2, x, y);
			}
		}
	}

} // end class Canvas