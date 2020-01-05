package exceptions;

import game.castle.Castle;

/**
 * Exception thrown when trying to make an action in a castle that already leads one.
 */
public class ExceptionActionAlreadyLaunched extends Exception {
	private Castle castle;                  /** Castle involved. */

	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct an exception object.
	 * @param castle Castle involved.
	 */
	public ExceptionActionAlreadyLaunched(Castle castle) {
		this.castle = castle;
	}

	/* METHODS ****************************************************/

	/**
	 * Write a warning message using the castle.
	 * @return Warning message created.
	 */
	public String toString() {
		return "Action Unavailable, Castle Have Already an Action: " + this.castle;
	}

	/* GETTER/SETTER **********************************************/

	/**
	 * Getter on castle.
	 * @return Castle involved.
	 */
	public Castle getCastle() {
		return this.castle;
	}
}
