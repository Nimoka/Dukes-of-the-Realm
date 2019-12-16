package game.entities;

import utils.Settings;

public class Knight extends Entity {
	/*** CONSTRUCTORS *********************************************/

	public Knight() {
		this.pointAttack = Settings.ENTITY_KNIGHT_ATTACK;
		this.pointHealth = Settings.ENTITY_KNIGHT_HEALTH;
		this.speed = Settings.ENTITY_KNIGHT_SPEED;
	}
}
