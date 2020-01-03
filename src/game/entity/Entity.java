package game.entity;

import exceptions.game.ExceptionPositionOutOfRoute;
import game.castle.action.Action;
import utils.Position;

public abstract class Entity {
	/*** VARIABLES ************************************************/

	protected Action currentAction;
	protected EntityState currentState;
	protected int pointAttack;
	protected int pointHealth;
	protected Position position;
	protected int speed;

	/*** CONSTRUCTORS *********************************************/

	public Entity() {
		this.currentState = EntityState.SLEEP;
	}

	/*** METHODS **************************************************/

	public boolean isDead() {
		return this.currentState == EntityState.DIE;
	}

	public void nextTurn() {
		if (this.currentState == EntityState.MOVE) {
			try {
				this.position = this.currentAction.getNextPosition(this.position, this.speed);
			} catch (ExceptionPositionOutOfRoute exception) {
				exception.printStackTrace();
				this.position = this.currentAction.getTarget().getPosition();
			}
			if (this.position.equals(this.currentAction.getTarget().getPosition()))
				this.currentState = EntityState.ATTACK;
		} else if (this.currentState == EntityState.ATTACK) {
			this.currentAction.getTarget().receiveAttack();
			this.pointAttack--;
			if (this.pointAttack <= 0)
				this.currentState = EntityState.DIE;
		}
	}

	/*** GETTER/SETTER ********************************************/

	public EntityState getCurrentState() {
		return this.currentState;
	}

	public int getPointAttack() {
		return this.pointAttack;
	}

	public int getPointHealth() {
		return this.pointHealth;
	}

	public Position getPosition() {
		return this.position;
	}

	public int getSpeed() {
		return this.speed;
	}
}
