package exceptions;

import game.castle.Castle;

/**
 * Exception thrown when trying to get a production on an empty production queue.
 */
public class ExceptionEmptyProductionQueue extends Exception {
	/* VARIABLES **************************************************/

	private Castle castle;                  /** Castle of the production queue involved. */

	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct an exception object and write warning message in System.err.
	 * @param castle Castle of the production queue involved.
	 */
	public ExceptionEmptyProductionQueue(Castle castle) {
		this.castle = castle;
		System.err.println(toString());
	}

	/* METHODS ****************************************************/

	/**
	 * Write a warning message using the duke object and the action name.
	 * @return Warning message created.
	 */
	public String toString() {
		return "Call on an Empty Production Queue: in " + this.castle.toString();
	}

	/* GETTER/SETTER **********************************************/

	/**
	 * Getter on castle.
	 * @return Castle of the production queue involved.
	 */
	public Castle getCastle() {
		return this.castle;
	}
}
