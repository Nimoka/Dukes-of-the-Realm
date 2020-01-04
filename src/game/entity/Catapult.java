package game.entity;

import static utils.Settings.*;

/**
 * A catapult.
 */
public class Catapult extends Entity {
	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct a new catapult.
	 */
	public Catapult() {
		super();
		this.pointAttack = ENTITY_CATAPULT_ATTACK;
		this.pointHealth = ENTITY_CATAPULT_HEALTH;
		this.speed = ENTITY_CATAPULT_SPEED;
	}

	/* METHODS ****************************************************/

	/**
	 * Make the catapult lives.
	 * Called at each new turn.
	 */
	public void nextTurn() {
		super.nextTurn();
	}
}
