package exceptions;

import game.castle.Castle;

public class ExceptionEmptyProductionQueue extends Throwable {
	/*** VARIABLES ************************************************/

	private Castle castle;

	/*** CONSTRUCTORS *********************************************/

	public ExceptionEmptyProductionQueue(Castle castle) {
		this.castle = castle;
		System.err.println(toString());
	}

	/*** METHODS **************************************************/

	public String toString() {
		return "Call on an Empty Production Queue: in " + this.castle.toString();
	}

	/*** GETTER/SETTER ********************************************/

	public Castle getCastle() {
		return this.castle;
	}
}
