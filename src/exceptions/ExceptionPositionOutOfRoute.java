package exceptions;

import utils.Position;

import java.util.ArrayList;

/**
 * Exception thrown when trying to access to a Position out of a Route.
 */
public class ExceptionPositionOutOfRoute extends Exception {
	/* VARIABLES **************************************************/

	private Position position;              /** Position out of the route involved. */
	private ArrayList<Position> route;      /** Route involved. */

	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct an exception object and write warning message in System.err.
	 * @param route Route involved.
	 * @param position Position out of the route involved.
	 */
	public ExceptionPositionOutOfRoute(ArrayList<Position> route, Position position) {
		this.route = route;
		this.position = position;
		System.err.println(toString());
	}

	/* METHODS ****************************************************/

	/**
	 * Write a warning message using the duke object and the action name.
	 * @return Warning message created.
	 */
	public String toString() {
		String message = "Position Out of Route: " + this.position + " not found on (";
		for (Position pos: this.route)
			message += pos + " > ";
		message += ").";
		return message;
	}

	/* GETTER/SETTER **********************************************/

	/**
	 * Getter on position.
	 * @return Position out of the route involved.
	 */
	public Position getPosition() {
		return this.position;
	}

	/**
	 * Getter on route.
	 * @return Route involved.
	 */
	public ArrayList<Position> getRoute() {
		return this.route;
	}
}
