package utils;

import java.util.Random;

/**
 * A 2D position.
 */
public class Position {
	/* VARIABLES **************************************************/

	protected int x = 0;                    /** X value of the position. */
	protected int y = 0;                    /** Y value of the position. */

	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct a new position with X and Y values.
	 * @param x X value of the position.
	 * @param y Y value of the position.
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Construct a new position with a position.
	 * @param position Position to copy.
	 */
	public Position(Position position) {
		this.x = position.getX();
		this.y = position.getY();
	}

	/**
	 * Construct a new position with X and Y double values.
	 * @param x X double value of the position.
	 * @param y Y double value of the position.
	 */
	public Position(double x, double y) {
		this((int) x, (int) y);
	}

	/* METHODS ****************************************************/

	/**
	 * Convert the position to a display as if it was a board position.
	 * @return Display position.
	 */
	public Position convertBoardToDisplay() {
		return new Position((this.x * Settings.BOARD_CELL_STYLE_WIDTH), (this.y * Settings.BOARD_CELL_STYLE_HEIGHT));
	}

	/**
	 * Distance between this position and another.
	 * @param position Other position to check.
	 * @return Distance between this position and another.
	 */
	public double distance(Position position) {
		return Math.sqrt(Math.pow((this.x - position.getX()), 2) + Math.pow((this.y - position.getY()), 2));
	}

	/**
	 * Get a random position on width and height ranges.
	 * @param width Width range.
	 * @param height Height range.
	 * @return Random position.
	 */
	public static Position random(int width, int height) {
		Random generator = new Random();
		int x = generator.nextInt(width);
		int y = generator.nextInt(height);
		return new Position(x, y);
	}

	/**
	 * Get a random position on width and height doubles ranges.
	 * @param width Width double range.
	 * @param height Height double range.
	 * @return Random position.
	 */
	public static Position random(double width, double height) {
		return random((int) width, (int) height);
	}

	/**
	 * Write a message that contains the information of the position.
	 * @return Message that contains the information of the position.
	 */
	public String toString() {
		return "Position { x: " + this.x + ", y: " + this.y + " }";
	}

	/**
	 * Translate the position using delta X and Y.
	 * @param dx Delta X.
	 * @param dy Delta Y.
	 */
	public void translate(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}

	/**
	 * Translate the position using a vector.
	 * @param vector Vector (Position based on 0,0).
	 */
	public void translate(Position vector) {
		this.x += vector.getX();
		this.y += vector.getY();
	}

	/* GETTER/SETTER **********************************************/

	/**
	 * Getter on x.
	 * @return X value of the position.
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Getter on y.
	 * @return Y value of the position.
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Setter of x.
	 * @param value New X value of the position.
	 */
	public void setX(int value) {
		this.x = value;
	}

	/**
	 * Setter of y.
	 * @param value New Y value of the position.
	 */
	public void setY(int value) {
		this.y = value;
	}

	/* OPERATORS **************************************************/

	/**
	 * Check if this position is equals to another.
	 * @param position Position to check.
	 * @return Both positions are equals.
	 */
	public boolean equals(Position position) {
		return ((this.x == position.getX()) && (this.y == position.getY()));
	}
}
