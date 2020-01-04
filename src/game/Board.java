package game;

import game.castle.Castle;
import player.Player;
import utils.Position;
import static utils.Settings.*;

import java.util.ArrayList;

public class Board {
	/*** VARIABLES ************************************************/

	private ArrayList<Castle> castles;
	private ArrayList<Player> players;
	private int currentTurn;

	/*** CONSTRUCTORS *********************************************/

	public Board(ArrayList<Player> players) {
		this.players = players;
		this.createCastles();
		this.currentTurn = (BOARD_FIRST_TURN - 1);
	}

	/*** METHODS **************************************************/

	// make load and save (using ObjectOutputStream and ObjectInputStream)

	public boolean checkEmptyCell(Position position) {
		for (Castle castle: this.castles) {
			if (castle.getPosition().equals(position))
				return false;
		}
		return true;
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
		Position targetPosition = target.getPosition();
		Position position = getCastleDoorPosition(source);
		route.add(position);
		int dx = targetPosition.getX() - position.getX();
		int dy = targetPosition.getY() - position.getY();
		while (position.distance(targetPosition) > 1) {
			Position next = new Position(position);
			if (Math.abs(dy) > Math.abs(dx)) {
				next.translate(0, (dy > 0 ? 1 : -1));
				if (!checkEmptyCell(next)) {
					next.translate((dx > 0 ? 1 : -1), (dy > 0 ? -1 : 1));
					dx += (dx > 0 ? -1 : 1);
				} else {
					dy += (dy > 0 ? -1 : 1);
				}
			} else {
				next.translate((dx > 0 ? 1 : -1), 0);
				if (!checkEmptyCell(next)) {
					next.translate((dx > 0 ? -1 : 1), (dy > 0 ? 1 : -1));
					dy += (dy > 0 ? -1 : 1);
				} else {
					dx += (dx > 0 ? -1 : 1);
				}
			}
			position = next;
			route.add(position);
		}
		return route;
	}

	public void checkMatchState() {
		// check nb of castles per duke
		// if only one have castle(s) => end
	}

	private void createCastles() {
		this.castles = new ArrayList<>();
		for (Player player: this.players) {
			Position position;
			do {
				position = Position.random((BOARD_DIM_WIDTH - 2), (BOARD_DIM_HEIGHT - 2));
				position.translate(1, 1);
			} while (!checkCastleDistance(position));
			this.castles.add(new Castle(this, player.getDuke(), position));
		}
	}

	public Position getCastleDoorPosition(Castle castle) {
		Position position = new Position(castle.getPosition());
		switch (castle.getDirection()) {
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
		return position;
	}

	public void nextTurn() {
		this.currentTurn++;
		System.out.println("[Board] Turn " + this.currentTurn);
		for (Castle castle: this.castles)
			castle.nextTurn();
		checkMatchState();
	}

	public String toString() {
		String message = "Board { castles: [ ";
		for (Castle castle: this.castles)
			message += castle.toString() + ", ";
		message += "], turn: " + this.currentTurn + " }";
		return message;
	}

	/*** GETTER/SETTER ********************************************/

	public ArrayList<Castle> getCastles() {
		return this.castles;
	}

	public int getCurrentTurn() {
		return this.currentTurn;
	}

	public ArrayList<Player> getPlayers() {
		return this.players;
	}
}
