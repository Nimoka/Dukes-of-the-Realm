package exceptions;

import utils.Position;

import java.util.ArrayList;

public class ExceptionPositionOutOfRoute extends Exception {
	/*** VARIABLES ************************************************/

	private Position position;
	private ArrayList<Position> route;

	/*** CONSTRUCTORS *********************************************/

	public ExceptionPositionOutOfRoute(ArrayList<Position> route, Position position) {
		this.route = route;
		this.position = position;
		System.err.println(toString());
	}

	/*** METHODS **************************************************/

	public String toString() {
		String message = "Position Out of Route: " + this.position + " not found on (";
		for (Position pos: this.route)
			message += pos + " > ";
		message += ").";
		return message;
	}

	/*** GETTER/SETTER ********************************************/

	public Position getPosition() {
		return this.position;
	}

	public ArrayList<Position> getRoute() {
		return this.route;
	}
}
