package utils;

import java.util.Random;

public class Position {
	/*** VARIABLES ************************************************/

	protected int x = 0;
	protected int y = 0;

	/*** CONSTRUCTORS *********************************************/

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/*** METHODS **************************************************/

	public Position convertBoardToDisplay() {
		return new Position((this.x * Settings.DISPLAY_CELL_WIDTH), (this.y * Settings.DISPLAY_CELL_HEIGHT));
	}

	public double distance(Position position) {
		return Math.sqrt(Math.pow((this.x - position.getX()), 2) + Math.pow((this.y - position.getY()), 2));
	}

	public static Position random(int width, int height) {
		Random generator = new Random();
		int x = generator.nextInt(width);
		int y = generator.nextInt(height);
		return new Position(x, y);
	}

	public String toString() {
		return "Position { x: " + this.x + ", y: " + this.y + " }";
	}

	public void translate(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}

	/*** GETTER/SETTER ********************************************/

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setX(int value) {
		this.x = value;
	}

	public void setY(int value) {
		this.y = value;
	}

	/*** OPERATORS ************************************************/

	public boolean equals(Position position) {
		return ((this.x == position.getX()) && (this.y == position.getY()));
	}
}
