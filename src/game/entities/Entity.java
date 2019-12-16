package game.entities;

public abstract class Entity {
	/*** VARIABLES ************************************************/

	protected int pointAttack;
	protected int pointHealth;
	protected int speed;

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
