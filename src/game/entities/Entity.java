package game.entities;

import game.castle.action.Action;

public abstract class Entity {
	/*** VARIABLES ************************************************/

	protected Action currentAction;
	protected EntityState currentState;
	protected int pointAttack;
	protected int pointHealth;
	protected int speed;

	/*** CONSTRUCTORS *********************************************/

	public Entity() {
		currentState = EntityState.SLEEP;
	}

	/*** METHODS **************************************************/

	public void nextTurn() {
		if (this.currentState == EntityState.ATTACK) {
			this.currentAction.getTarget().receiveAttack();
			this.pointAttack--;
			if (this.pointAttack <= 0)
				this.currentState = EntityState.DIE;
		}
	}

	/*** GETTER/SETTER ********************************************/

	public int getPointAttack() {
		return this.pointAttack;
	}

	public int getPointHealth() {
		return this.pointHealth;
	}

	public int getSpeed() {
		return this.speed;
	}
}
