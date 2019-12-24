package game.castle.production;

import game.castle.Castle;

public abstract class Production {
	/*** VARIABLES ************************************************/

	protected Castle castle;
	protected int cost;
	protected Object object;
	protected int timer;

	/*** METHODS **************************************************/

	public void nextTurn() {
		this.timer--;
		this.castle.terminateProduction();
	}

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
