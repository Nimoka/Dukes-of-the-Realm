package game.castle;

import game.castle.production.Production;
import utils.Position;
import utils.Settings;

public class Castle {
	/*** VARIABLES ************************************************/

	private Position position;
	private Production production;
	private game.entities.Group stock;

	/*** CONSTRUCTORS *********************************************/

	public Castle(Position position) {
		this.position = position;
		this.stock = new game.entities.Group(Settings.CASTLE_NB_CATAPULT, Settings.CASTLE_NB_KNIGHT, Settings.CASTLE_NB_PIKEMAN);
	}

	/*** METHODS **************************************************/

	public String toString() {
		return "Castle { position: " + this.position.toString() + " }";
	}

	/*** GETTER/SETTER ********************************************/

	public Position getPosition() {
		return this.position;
	}

	public game.entities.Group getStock() {
		return this.stock;
	}
}
