package game.entity;

import static utils.Settings.*;

public class Catapult extends Entity {
	/*** CONSTRUCTORS *********************************************/

	public Catapult() {
		super();
		this.pointAttack = ENTITY_CATAPULT_ATTACK;
		this.pointHealth = ENTITY_CATAPULT_HEALTH;
		this.speed = ENTITY_CATAPULT_SPEED;
	}

	/*** METHODS **************************************************/

	public void nextTurn() {
		super.nextTurn();
	}
}
