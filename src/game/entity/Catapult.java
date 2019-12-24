package game.entity;

import utils.Settings;

public class Catapult extends Entity {
	/*** CONSTRUCTORS *********************************************/

	public Catapult() {
		super();
		this.pointAttack = Settings.ENTITY_CATAPULT_ATTACK;
		this.pointHealth = Settings.ENTITY_CATAPULT_HEALTH;
		this.speed = Settings.ENTITY_CATAPULT_SPEED;
	}

	/*** METHODS **************************************************/

	public void nextTurn() {
		super.nextTurn();
	}
}
