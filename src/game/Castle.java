package game;

import utils.Position;

public class Castle {
	/*** VARIABLES ************************************************/

	private Position position = null;

	/*** CONSTRUCTORS *********************************************/

	public Castle(Position position) {
		this.position = position;
	}

	/*** GETTER/SETTER ********************************************/

	public Position getPosition() {
		return this.position;
	}
}
