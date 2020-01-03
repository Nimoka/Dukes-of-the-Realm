package game.production;

import game.castle.Castle;
import game.entity.*;
import static utils.Settings.*;

public class EntityProduction extends Production {
	/*** CONSTRUCTORS *********************************************/

	public EntityProduction(Castle castle, Class<Entity> type) {
		this.castle = castle;
		if (Catapult.class.equals(type)) {
			this.object = new Catapult();
			this.timer = ENTITY_CATAPULT_PROD_TIME;
			this.cost = ENTITY_CATAPULT_PROD_COST;
		} else if (Knight.class.equals(type)) {
			this.object = new Knight();
			this.timer = ENTITY_KNIGHT_PROD_TIME;
			this.cost = ENTITY_KNIGHT_PROD_COST;
		} else if (Pikeman.class.equals(type)) {
			this.object = new Pikeman();
			this.timer = ENTITY_PIKEMAN_PROD_TIME;
			this.cost = ENTITY_PIKEMAN_PROD_COST;
		}
	}

	/*** METHODS **************************************************/

	public String toString() {
		return "EntityProduction { object: " + this.object + ", timer: " + this.timer + ", cost: " + this.cost + " }";
	}

	/*** GETTER/SETTER ********************************************/

	public Entity getEntity() {
		return (Entity) this.object;
	}
}
