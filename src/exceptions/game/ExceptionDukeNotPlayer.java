package exceptions.castle;

import game.Duke;

public class ExceptionDukeNotPlayer extends Exception {
	/*** VARIABLES ************************************************/

	private String action;
	private Duke duke;

	/*** CONSTRUCTORS *********************************************/

	public ExceptionDukeNotPlayer(Duke duke, String action) {
		this.duke = duke;
		this.action = action;
		System.err.println(toString());
	}

	/*** METHODS **************************************************/

	public String toString() {
		return "Action Unavailable, Duke not a Player: " + this.action + " using " + this.duke.getName();
	}

	/*** GETTER/SETTER ********************************************/

	public String getAction() {
		return this.action;
	}

	public Duke getDuke() {
		return this.duke;
	}
}
