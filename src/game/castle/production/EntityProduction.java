package game.castle.production;

import game.entities.*;
import utils.Settings;

public class EntityProduction extends Production {
	/*** CONSTRUCTORS *********************************************/

	public EntityProduction(Class<Entity> type) {
		if (Catapult.class.equals(type)) {
			this.object = new Catapult();
			this.timer = Settings.ENTITY_CATAPULT_PROD_TIME;
			this.cost = Settings.ENTITY_CATAPULT_PROD_COST;
		} else if (Knight.class.equals(type)) {
			this.object = new Knight();
			this.timer = Settings.ENTITY_KNIGHT_PROD_TIME;
			this.cost = Settings.ENTITY_KNIGHT_PROD_COST;
		} else if (Pikeman.class.equals(type)) {
			this.object = new Pikeman();
			this.timer = Settings.ENTITY_PIKEMAN_PROD_TIME;
			this.cost = Settings.ENTITY_PIKEMAN_PROD_COST;
		}
	}
}
