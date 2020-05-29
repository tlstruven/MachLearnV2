package machineLearning;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

// This class generates a BufferedImage of side = SIDE with a random character
// drawn within. There are controls to randomize the placement, font style and font size
// of the character. The BufferedImage is a bitmap image with only black
// and white pixels.
// ImageChar.drawRandomChar() draws a random character, and returns real char
// ImageChar.getPixel(row, col) returns 1 if pixel is lit, 0 if not
// ImageChar.clear() clears the bitmap image (all 0's)
public class ImageChar {
	// CONSTANTS
	// Side of character drawing area - should be mult of 8 for best performance
	public static final int SIDE = 24;
	// Array of fonts to add randomness to characters drawn
	// Any font in FONTS will be randomly chosen
	private static final Font[] FONTS = { new Font("Arial", Font.PLAIN, 15) };

	/*
	 * private static final Font[] FONTS = { new Font("TimesRoman", Font.PLAIN, 10),
	 * new Font("TimesRoman", Font.PLAIN, 15), new Font("TimesRoman", Font.PLAIN,
	 * 20), new Font("TimesRoman", Font.PLAIN, 25), new Font("Lucida Console",
	 * Font.PLAIN, 10), new Font("Lucida Console", Font.PLAIN, 15), new
	 * Font("Lucida Console", Font.PLAIN, 20), new Font("Lucida Console",
	 * Font.PLAIN, 25), new Font("DejaVu Sans", Font.PLAIN, 10), new
	 * Font("DejaVu Sans", Font.PLAIN, 15), new Font("DejaVu Sans", Font.PLAIN, 20),
	 * new Font("DejaVu Sans", Font.PLAIN, 25), new Font("Arial", Font.PLAIN, 10),
	 * new Font("Arial", Font.PLAIN, 15), new Font("Arial", Font.PLAIN, 20), new
	 * Font("Arial", Font.PLAIN, 25), };
	 */

	// MEMBER VARIABLES
	private BufferedImage image;
	private Graphics2D g2;
	private FontRenderContext frc;
	private byte[] pixels; // pixel map of BufferedImage

	// CONSTRUCTOR
	public ImageChar() {
		image = new BufferedImage(SIDE, SIDE, BufferedImage.TYPE_BYTE_BINARY);
		g2 = image.createGraphics();
		frc = g2.getFontRenderContext();
		pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	}

	// MEMBER METHODS
	public char drawRandomChar(int x, int y) {
		char[] c = { 'N' };
		g2.setColor(Color.WHITE);
		g2.setFont(FONTS[randomNum(0, FONTS.length - 1)]);
		c[0] = randomChar();
		Rectangle2D r = g2.getFont().getStringBounds(c, 0, 1, frc);
		int w = (int) r.getWidth() + 1;
		int h = (int) r.getHeight() + 1;
// The following line allows randomization of the placement of the char
//		Point pt = new Point(randomNum(0, SIDE - w + 1), randomNum(0 + h, SIDE));

		Point pt = new Point(4, SIDE - 4); // use this to always place the same
		g2.drawChars(c, 0, 1, x + pt.x, y + pt.y);
		return c[0];
	}

	// draws a random character in the BufferedImage
	public char drawRandomChar(Graphics2D g2, int x, int y) {
		this.g2 = g2;
		frc = g2.getFontRenderContext();
		return drawRandomChar(x, y);
	}

	// clears the BufferedImage
	public void clear() {
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, SIDE, SIDE);
	}

	// benchmark to test the time it takes to draw many chars (used for testing)
	public long benchmark(long iterations) {
		long nano_startTime = System.nanoTime();
		for (long i = 0; i < iterations; i++) {
			drawRandomChar(0, 0);
			clear();
		}
		return System.nanoTime() - nano_startTime;
	}

	// returns a random number between low (inclusive) and high (inclusive)
	public static int randomNum(int low, int high) {
		return (int) (Math.random() * (high + 1 - low)) + low;
	}

	// returns a random number between 0 (inclusive) and high (inclusive)
	public static int randomNum(int high) {
		return randomNum(0, high);
	}

	// returns a random character between A to Z (inclusive)
	public static char randomChar() {
		return (char) randomNum('A', 'Z');
	}

	// Precondition: 0 <= row, col < SIDE
	// returns 1 if pixel, 0 if not
	public int getPixel(int row, int col) {
		return (pixels[row * SIDE / 8 + col / 8] >>> (7 - col % 8)) & 1;
	}

	@Override
	public String toString() {
		String result = "";
		for (int row = 0; row < SIDE; row++) {
			for (int col = 0; col < SIDE / 8; col++) {
				for (int bit = 0; bit < 8; bit++) {
					byte b = pixels[row * SIDE / 8 + col];
					result += (byte) ((b >>> (7 - bit)) & (byte) 1);
				}
			}
			result += "\n";
		}
		return result;
	}

	// prints char pattern to console (used for testing)
	public void testPattern() {
		for (int row = 0; row < SIDE; row++) {
			for (int col = 0; col < SIDE; col++) {
				System.out.print(getPixel(row, col) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
