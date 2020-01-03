package game.castle.production;

import exceptions.game.ExceptionDukeNotPlayer;
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
		try {
			this.castle.terminateProduction();
		} catch (ExceptionDukeNotPlayer e) {
			e.printStackTrace();
		}
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
