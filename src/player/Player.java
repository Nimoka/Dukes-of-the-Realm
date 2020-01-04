package player;

import game.duke.Duke;

public abstract class Player {
	/*** VARIABLES ************************************************/

	protected Duke duke;

	/*** METHODS **************************************************/

	public void nextTurn() {
	}

	/*** GETTER/SETTER ********************************************/

	public Duke getDuke() {
		return this.duke;
	}
}
