package game.entity;

import exceptions.ExceptionPositionOutOfRoute;
import game.castle.Castle;
import game.action.Action;
import utils.NameGenerator;
import utils.Position;

import java.io.Serializable;

/**
 * An entity.
 */
public abstract class Entity implements Serializable {
	/* VARIABLES **************************************************/

	protected Action currentAction;         /** Current action where entity is involved. */
	protected EntityState currentState;     /** Current state of the entity. */
	protected String name;                  /** Name of the entity. */
	protected int pointAttack;              /** Attack points of the entity. */
	protected int pointHealth;              /** Health points of the entity. */
	protected Position position;            /** Current position of the entity on the board. */
	protected int speed;                    /** Speed of the entity. */

	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct a new entity.
	 */
	public Entity() {
		this.name = NameGenerator.random();
		this.currentState = EntityState.SLEEP;
	}

	/* METHODS ****************************************************/

	/**
	 * Check if the entity is dead.
	 * @return The entity is dead.
	 */
	public boolean isDead() {
		return (this.currentState == EntityState.DIE) || (this.pointHealth == 0);
	}

	/**
	 * Launch the entity.
	 * @param action Action where entity is involved.
	 */
	public void launch(Action action) {
		this.currentAction = action;
		this.currentState = EntityState.MOVE;
		this.position = action.getFirstPosition();
	}

	/**
	 * Make the entity join a castle stock.
	 * @param castle Castle to join.
	 */
	private void joinCastleStock(Castle castle) {
		castle.getStock().addEntity(this);
		this.currentAction.getArmy().removeEntity(this);
		this.currentAction = null;
		this.currentState = EntityState.SLEEP;
		this.position = null;
	}

	/**
	 * If the entity is moving: check his next position.
	 * If the entity is attacking: attack the target, die if he can't attack anymore.
	 * Called at each new turn.
	 */
	public void nextTurn() {
		if (this.currentState == EntityState.MOVE) {
			try {
				this.position = this.currentAction.getNextPosition(this.position, this.speed);
			} catch (ExceptionPositionOutOfRoute exception) {
				exception.printStackTrace();
				this.position = this.currentAction.getTarget().getPosition();
			}
			if (this.position.equals(this.currentAction.getTarget().getPosition())) {
				if (this.currentAction.getTarget().getDuke() == this.currentAction.getSource().getDuke())
					joinCastleStock(this.currentAction.getTarget());
				else
					this.currentState = EntityState.ATTACK;
			}
		} else if (this.currentState == EntityState.ATTACK) {
			if (this.currentAction.getTarget().getDuke() == this.currentAction.getSource().getDuke()) {
				joinCastleStock(this.currentAction.getTarget());
			} else {
				this.currentAction.getTarget().receiveAttack();
				this.pointAttack--;
				if (this.pointAttack <= 0)
					this.currentState = EntityState.DIE;
			}
		}
	}

	/**
	 * Remove health point to the entity.
	 */
	public void receiveAttack() {
		if (this.pointHealth > 0)
			this.pointHealth--;
	}

	/**
	 * Write a message that contains all information of the entity.
	 * @return Message that contains all information of the entity.
	 */
	public String toString() {
		return this.getClass().toString() + " { name: " + this.name + ", state: " + this.currentState + ", position: " + this.position.toString() + ", pointAttack: " + this.pointAttack + ", pointHealth: " + this.pointHealth + ", speed: " + this.speed + " }";
	}

	/* GETTER/SETTER **********************************************/

	/**
	 * Getter on currentState.
	 * @return Current state of the entity.
	 */
	public EntityState getCurrentState() {
		return this.currentState;
	}

	/**
	 * Getter on pointAttack.
	 * @return Attack points of the entity.
	 */
	public int getPointAttack() {
		return this.pointAttack;
	}

	/**
	 * Getter on pointHealth.
	 * @return Health points of the entity.
	 */
	public int getPointHealth() {
		return this.pointHealth;
	}

	/**
	 * Getter on position.
	 * @return Current position of the entity on the board.
	 */
	public Position getPosition() {
		return this.position;
	}

	/**
	 * Getter on speed.
	 * @return Speed of the entity.
	 */
	public int getSpeed() {
		return this.speed;
	}
}
