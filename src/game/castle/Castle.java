package game.castle;

import game.castle.production.Production;
import game.entities.EntityGroup;
import utils.Position;
import utils.Settings;

public class Castle {
	/*** VARIABLES ************************************************/

	private Position position;
	private Production production;
	private EntityGroup stock;

	/*** CONSTRUCTORS *********************************************/

	public Castle(Position position) {
		this.position = position;
		this.stock = new EntityGroup(Settings.CASTLE_NB_CATAPULT, Settings.CASTLE_NB_KNIGHT, Settings.CASTLE_NB_PIKEMAN);
	}

	/*** METHODS **************************************************/

	public String toString() {
		return "Castle { position: " + this.position.toString() + " }";
	}

	/*** GETTER/SETTER ********************************************/

	public Position getPosition() {
		return this.position;
	}

	public EntityGroup getStock() {
		return this.stock;
	}
}
