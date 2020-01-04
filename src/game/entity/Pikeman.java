package game.entity;

import static utils.Settings.*;

/**
 * A pikeman.
 */
public class Pikeman extends Entity {
	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct a new pikeman.
	 */
	public Pikeman() {
		super();
		this.pointAttack = ENTITY_PIKEMAN_ATTACK;
		this.pointHealth = ENTITY_PIKEMAN_HEALTH;
		this.speed = ENTITY_PIKEMAN_SPEED;
	}

	/* METHODS ****************************************************/

	/**
	 * Make the pikeman lives.
	 * Called at each new turn.
	 */
	public void nextTurn() {
		super.nextTurn();
	}
}
