package game.entity;

import static utils.Settings.*;

public class Pikeman extends Entity {
	/*** CONSTRUCTORS *********************************************/

	public Pikeman() {
		super();
		this.pointAttack = ENTITY_PIKEMAN_ATTACK;
		this.pointHealth = ENTITY_PIKEMAN_HEALTH;
		this.speed = ENTITY_PIKEMAN_SPEED;
	}

	/*** METHODS **************************************************/

	public void nextTurn() {
		super.nextTurn();
	}
}
