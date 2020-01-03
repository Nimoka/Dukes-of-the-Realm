package game.production;

import game.castle.Castle;
import static utils.Settings.*;

public class LevelProduction extends Production {
	/*** CONSTRUCTORS *********************************************/

	public LevelProduction(Castle castle) {
		this.castle = castle;
		int currentLevel = castle.getLevel();
		this.object = (Integer) (currentLevel + 1);
		this.cost = CASTLE_LEVEL_PROD_COST(currentLevel);
		this.timer = CASTLE_LEVEL_PROD_TIME(currentLevel);
	}

	/*** METHODS **************************************************/

	public String toString() {
		return "LevelProduction { level: " + (Integer) this.object + ", timer: " + this.timer + ", cost: " + this.cost + " }";
	}

	/*** GETTER/SETTER ********************************************/

	public int getLevel() {
		return (Integer) this.object;
	}
}
