package game.production;

import game.castle.Castle;
import game.entity.*;
import static utils.Settings.*;

/**
 * A production of entity.
 */
public class EntityProduction extends Production {
	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct the production and the entity.
	 * @param castle Castle where the production is made.
	 * @param type Type of entity to create.
	 */
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

	/* METHODS ****************************************************/

	/**
	 * Write a message that contains all information of the production.
	 * @return Message that contains all information of the production.
	 */
	public String toString() {
		return "EntityProduction { object: " + this.object + ", timer: " + this.timer + ", cost: " + this.cost + " }";
	}

	/* GETTER/SETTER **********************************************/

	/**
	 * Getter on object (cast as Entity).
	 * @return Entity created during the production.
	 */
	public Entity getEntity() {
		return (Entity) this.object;
	}
}
