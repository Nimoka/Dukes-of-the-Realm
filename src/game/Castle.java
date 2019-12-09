package game;

import utils.Position;

public class Castle {
	/*** VARIABLES ************************************************/

	private Position position = null;

	/*** CONSTRUCTORS *********************************************/

	public Castle(Position position) {
		this.position = position;
	}

	/*** METHODS **************************************************/

	public String toString() {
		return "Castle { position: " + this.position.toString() + " }";
	}

	/*** GETTER/SETTER ********************************************/

	public Position getPosition() {
		return this.position;
	}
}
