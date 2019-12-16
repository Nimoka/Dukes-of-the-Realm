package game.entities;

public abstract class Entity {
	/*** VARIABLES ************************************************/

	protected int pointAttack = 0;
	protected int pointHealth = 0;
	protected int speed = 0;

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
