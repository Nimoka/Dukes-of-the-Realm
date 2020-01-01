package game;

import game.castle.Castle;
import utils.Position;
import static utils.Settings.*;

import java.util.ArrayList;

public class Board {
	/*** VARIABLES ************************************************/

	private ArrayList<Castle> castles;
	private ArrayList<Duke> dukes;
	private int currentTurn;

	/*** CONSTRUCTORS *********************************************/

	public Board() {
		this.dukes = new ArrayList<>();
		this.createDukes();
		this.createCastles();
		this.currentTurn = 1;
	}

	/*** METHODS **************************************************/

	public boolean checkCastle(Position position) {
		for (Castle castle: this.castles) {
			if (castle.getPosition().equals(position))
				return true;
		}
		return false;
	}

	public boolean checkCastleDistance(Position position) {
		for (Castle castle: this.castles) {
			if (castle.getPosition().distance(position) < CASTLE_DISTANCE)
				return false;
		}
		return true;
	}

	public ArrayList<Position> computeArmyRoute(Castle source, Castle target) {
		ArrayList<Position> route = new ArrayList<>();
		Position position = source.getPosition();
		switch (source.getDirection()) {
			case NORTH:
				position.translate(0, -1);
				break;
			case EAST:
				position.translate(1, 0);
				break;
			case SOUTH:
				position.translate(0, 1);
				break;
			case WEST:
				position.translate(-1, 0);
				break;
		}
		route.add(position);
		// to continue
		return route;
	}

	public void checkMatchState() {
	}

	private void createCastles() {
		this.castles = new ArrayList<>();
		for (Duke duke: this.dukes) {
			Position position;
			do {
				position = Position.random((BOARD_DIM_WIDTH - 2), (BOARD_DIM_HEIGHT - 2));
				position.translate(1, 1);
			} while (!checkCastleDistance(position));
			this.castles.add(new Castle(this, duke, position));
		}
	}

	private void createDukes() {
		for (int i = 0; i < BOARD_NB_DUKES + 1; i++)
			this.dukes.add(new Duke());
	}

	public void nextTurn() {
		this.currentTurn++;
		for (Castle castle: this.castles)
			castle.nextTurn();
		checkMatchState();
	}

	public String toString() {
		String result = "Board { castles:\n";
		for (Castle castle: this.castles)
			result += '\t' + castle.toString() + '\n';
		result += "}";
		return result;
	}

	/*** GETTER/SETTER ********************************************/

	public ArrayList<Castle> getCastles() {
		return this.castles;
	}

	public int getCurrentTurn() {
		return this.currentTurn;
	}

	public ArrayList<Duke> getDukes() {
		return this.dukes;
	}
}
