package exceptions;

import game.duke.Duke;

/**
 * Exception thrown when trying to make an action with a duke that is not playing.
 */
public class ExceptionDukeNotPlayer extends Exception {
	/* VARIABLES **************************************************/

	private String action;                  /** Name of the action when exception was thrown. */
	private Duke duke;                      /** Duke object that caused this exception. */

	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct an exception object and write warning message in System.err.
	 * @param duke Name of the action when exception was thrown.
	 * @param action Duke object that caused this exception.
	 */
	public ExceptionDukeNotPlayer(Duke duke, String action) {
		this.duke = duke;
		this.action = action;
		System.err.println(toString());
	}

	/* METHODS ****************************************************/

	/**
	 * Write a warning message using the duke object and the action name.
	 * @return Warning message created.
	 */
	public String toString() {
		return "Action Unavailable, Duke not a Player: " + this.action + " using " + this.duke.getName();
	}

	/* GETTER/SETTER **********************************************/

	/**
	 * Getter on action.
	 * @return Name of the action when exception was thrown.
	 */
	public String getAction() {
		return this.action;
	}

	/**
	 * Getter on duke.
	 * @return Duke object that caused this exception.
	 */
	public Duke getDuke() {
		return this.duke;
	}
}
