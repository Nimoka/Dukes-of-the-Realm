package player;

import game.duke.Duke;

import java.io.Serializable;

/**
 * A player of the game.
 */
public abstract class Player implements Serializable {
	/* VARIABLES **************************************************/

	protected Duke duke;                    /** The duke that the player plays. */

	/* METHODS ****************************************************/

	/**
	 * Called at each new turn.
	 */
	public void nextTurn() {
	}

	/* GETTER/SETTER **********************************************/

	/**
	 * Getter on duke.
	 * @return The duke that the player plays.
	 */
	public Duke getDuke() {
		return this.duke;
	}
}
