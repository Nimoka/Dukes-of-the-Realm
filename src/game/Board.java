package game;

import game.castle.Castle;
import game.duke.Duke;
import main.Main;
import player.Player;
import utils.Position;
import static utils.Settings.*;

import java.util.ArrayList;

/**
 * A board where all players are playing, with castles and armies.
 */
public class Board {
	/* VARIABLES **************************************************/

	private ArrayList<Castle> castles;      /** List of the castles on the board. */
	private int currentTurn;                /** Current turn of the match. */
	private Main environment;               /** The environment (application) of the game. */
	private boolean matchState;             /** State of the match (false = over). */
	private ArrayList<Player> players;      /** List of the players playing on the board. */

	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct a board for certain players.
	 * @param players List of the players playing on the board.
	 */
	public Board(ArrayList<Player> players, Main environment) {
		this.environment = environment;
		this.players = players;
		this.createCastles();
		this.currentTurn = (BOARD_FIRST_TURN - 1);
		this.matchState = true;
	}

	/* METHODS ****************************************************/

	// make load and save (using ObjectOutputStream and ObjectInputStream)

	/**
	 * Check if a board's cell contains a castle.
	 * @param position Position of the cell to check.
	 * @return Board's cell is empty.
	 */
	public boolean checkEmptyCell(Position position) {
		for (Castle castle: this.castles) {
			if (castle.getPosition().equals(position))
				return false;
		}
		return true;
	}

	/**
	 * Check if the distance between castles and a position is met.
	 * @param position Position of the cell to check.
	 * @return Condition is met.
	 */
	public boolean checkCastleDistance(Position position) {
		for (Castle castle: this.castles) {
			if (castle.getPosition().distance(position) < CASTLE_DISTANCE)
				return false;
		}
		return true;
	}

	/**
	 * Check if the match is over.
	 */
	public void checkMatchState() {
		Duke duke = this.castles.get(0).getDuke();
		for (Castle castle: this.castles) {
			if (duke != castle.getDuke())
				return;
		}
		this.matchState = false;
	}

	/**
	 * Create a route from a source castle door to a target castle, avoiding others.
	 * @param source Source castle of the route.
	 * @param target Target castle of the route.
	 * @return Route created.
	 */
	public ArrayList<Position> computeArmyRoute(Castle source, Castle target) {
		ArrayList<Position> route = new ArrayList<>();
		Position targetPosition = getCastleDoorPosition(target);
		Position position = getCastleDoorPosition(source);
		route.add(position);
		int dx = targetPosition.getX() - position.getX();
		int dy = targetPosition.getY() - position.getY();
		while (!position.equals(targetPosition)) {
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
		route.add(target.getPosition());
		return route;
	}

	/**
	 * Create a castle per player.
	 */
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

	/**
	 * Get the position facing a castle door.
	 * @param castle Castle involved.
	 * @return Position facing the castle door.
	 */
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

	/**
	 * Get the winner duke of the match.
	 * @return Winner duke.
	 */
	public Duke getWinner() {
		if (matchState == true)
			return null;
		return this.castles.get(0).getDuke();
	}

	/**
	 * Increase the turn counter, call nextTurn() for each castle and check the match state.
	 * Called at each new turn.
	 */
	public void nextTurn() {
		this.currentTurn++;
		System.out.println("[Board] Turn " + this.currentTurn);
		for (Castle castle: this.castles)
			castle.nextTurn();
		checkMatchState();
	}

	/**
	 * Write a message that contains all information of the board.
	 * @return Message that contains all information of the board.
	 */
	public String toString() {
		String message = "Board { castles: [ ";
		for (Castle castle: this.castles)
			message += castle.toString() + ", ";
		message += "], turn: " + this.currentTurn + " }";
		return message;
	}

	/* GETTER/SETTER **********************************************/

	/**
	 * Getter on castles.
	 * @return List of the castles on the board.
	 */
	public ArrayList<Castle> getCastles() {
		return this.castles;
	}

	/**
	 * Getter on currentTurn.
	 * @return Current turn of the match.
	 */
	public int getCurrentTurn() {
		return this.currentTurn;
	}

	/**
	 * Getter on environment.
	 * @return The environment (application) of the game.
	 */
	public Main getEnvironment() {
		return this.environment;
	}

	/**
	 * Getter on matchState.
	 * @return State of the match (false = over).
	 */
	public boolean getMatchState() {
		return this.matchState;
	}

	/**
	 * Getter on players.
	 * @return List of the players playing on the board.
	 */
	public ArrayList<Player> getPlayers() {
		return this.players;
	}
}
