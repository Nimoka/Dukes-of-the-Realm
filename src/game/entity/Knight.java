package game.entity;

import static utils.Settings.*;

public class Knight extends Entity {
	/*** CONSTRUCTORS *********************************************/

	public Knight() {
		super();
		this.pointAttack = ENTITY_KNIGHT_ATTACK;
		this.pointHealth = ENTITY_KNIGHT_HEALTH;
		this.speed = ENTITY_KNIGHT_SPEED;
	}

	/*** METHODS **************************************************/

	public void nextTurn() {
		super.nextTurn();
	}
}
