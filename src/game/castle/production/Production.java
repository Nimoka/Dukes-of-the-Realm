package game.castle.production;

public abstract class Production {
	/*** VARIABLES ************************************************/

	protected int cost;
	protected Object object;
	protected int timer;

	/*** GETTER/SETTER ********************************************/

	public int getCost() {
		return this.cost;
	}

	public Object getObject() {
		return this.object;
	}

	public int getTimer() {
		return this.timer;
	}
}
