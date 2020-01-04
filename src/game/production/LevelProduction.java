package game.production;

import game.castle.Castle;
import static utils.Settings.*;

/**
 * A production of a new castle's level.
 */
public class LevelProduction extends Production {
	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct the production and the new level.
	 * @param castle Castle where the production is made.
	 */
	public LevelProduction(Castle castle) {
		this.castle = castle;
		int currentLevel = castle.getLevel();
		this.object = (Integer) (currentLevel + 1);
		this.cost = CASTLE_LEVEL_PROD_COST(currentLevel);
		this.timer = CASTLE_LEVEL_PROD_TIME(currentLevel);
	}

	/* METHODS ****************************************************/

	/**
	 * Write a message that contains all information of the production.
	 * @return Message that contains all information of the production.
	 */
	public String toString() {
		return "LevelProduction { level: " + (Integer) this.object + ", timer: " + this.timer + ", cost: " + this.cost + " }";
	}

	/* GETTER/SETTER **********************************************/

	/**
	 * Getter on object (cast as int).
	 * @return Level created during the production.
	 */
	public int getLevel() {
		return (Integer) this.object;
	}
}
