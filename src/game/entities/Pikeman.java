package game.entities;

import utils.Settings;

public class Pikeman extends Entity {
	/*** CONSTRUCTORS *********************************************/

	public Pikeman() {
		this.pointAttack = Settings.ENTITY_PIKEMAN_ATTACK;
		this.pointHealth = Settings.ENTITY_PIKEMAN_HEALTH;
		this.speed = Settings.ENTITY_PIKEMAN_SPEED;
	}

	/*** METHODS **************************************************/

	public void nextTurn() {
		super.nextTurn();
	}
}
