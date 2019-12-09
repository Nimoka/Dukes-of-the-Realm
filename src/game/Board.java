package game;

import utils.Position;

import java.util.ArrayList;

public class Board {
	/*** VARIABLES ************************************************/

	private ArrayList<Castle> castles = null;

	/*** CONSTRUCTORS *********************************************/

	public Board() {
		this.createCastles();
	}

	/*** METHODS **************************************************/

	private void createCastles() {
	}

	public boolean positionEmpty(Position position) {
		for (Castle castle: castles) {
			if (castle.getPosition().equals(position))
				return false;
		}
		return true;
	}
}
