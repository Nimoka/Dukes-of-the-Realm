package game.entity;

import static utils.Settings.*;

/**
 * A knight.
 */
public class Knight extends Entity {
	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct a new knight.
	 */
	public Knight() {
		super();
		this.pointAttack = ENTITY_KNIGHT_ATTACK;
		this.pointHealth = ENTITY_KNIGHT_HEALTH;
		this.speed = ENTITY_KNIGHT_SPEED;
	}

	/* METHODS ****************************************************/

	/**
	 * Make the knight lives.
	 * Called at each new turn.
	 */
	public void nextTurn() {
		super.nextTurn();
	}
}
