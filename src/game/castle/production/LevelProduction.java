package game.castle.production;

import game.castle.Castle;
import utils.Settings;

public class LevelProduction extends Production {
	/*** CONSTRUCTORS *********************************************/

	public LevelProduction(Castle castle) {
		this.castle = castle;
		int currentLevel = castle.getLevel();
		this.object = (Integer) (currentLevel + 1);
		this.cost = Settings.CASTLE_LEVEL_PROD_COST(currentLevel);
		this.timer = Settings.CASTLE_LEVEL_PROD_TIME(currentLevel);
	}

	/*** GETTER/SETTER ********************************************/

	public int getLevel() {
		return (Integer) this.object;
	}
}
