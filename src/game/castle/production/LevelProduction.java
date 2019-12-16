package game.castle.production;

import utils.Settings;

public class LevelProduction extends Production {
	/*** CONSTRUCTORS *********************************************/

	public LevelProduction(int currentLevel) {
		this.object = (Integer) (currentLevel + 1);
		this.cost = Settings.CASTLE_LEVEL_PROD_COST(currentLevel);
		this.timer = Settings.CASTLE_LEVEL_PROD_TIME(currentLevel);
	}
}
