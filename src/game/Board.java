package game;

import game.castle.Castle;
import utils.Position;
import utils.Settings;

import java.util.ArrayList;

public class Board {
	/*** VARIABLES ************************************************/

	private ArrayList<Castle> castles;
	private int currentTurn;

	/*** CONSTRUCTORS *********************************************/

	public Board() {
		this.createCastles();
		this.currentTurn = 1;
	}

	/*** METHODS **************************************************/

	private void createCastles() {
		this.castles = new ArrayList<>();
		for (int i = 0; i < Settings.BOARD_NB_CASTLES; i++) {
			Position position;
			do {
				position = Position.random(Settings.BOARD_WIDTH, Settings.BOARD_HEIGHT);
			} while (!positionEmpty(position));
			this.castles.add(new Castle(position));
			// to continue
		}
	}

	public void nextTurn() {
		this.currentTurn++;
		for (Castle castle: this.castles)
			castle.nextTurn();
	}

	public boolean positionEmpty(Position position) {
		for (Castle castle: this.castles) {
			if (castle.getPosition().equals(position))
				return false;
		}
		return true;
	}

	public String toString() {
		String result = "Board { castles:\n";
		for (Castle castle: this.castles)
			result += '\t' + castle.toString() + '\n';
		result += "}";
		return result;
	}
}
