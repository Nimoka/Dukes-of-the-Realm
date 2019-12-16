package game.entities;

import utils.Settings;

public class Catapult extends Entity {
	/*** CONSTRUCTORS *********************************************/

	public Catapult() {
		this.pointAttack = Settings.ENTITY_CATAPULT_ATTACK;
		this.pointHealth = Settings.ENTITY_CATAPULT_HEALTH;
		this.speed = Settings.ENTITY_CATAPULT_SPEED;
	}
}
