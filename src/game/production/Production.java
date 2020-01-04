package game.production;

import exceptions.ExceptionDukeNotPlayer;
import exceptions.ExceptionEmptyProductionQueue;
import game.castle.Castle;

/**
 * A production made by a castle.
 */
public abstract class Production {
	/* VARIABLES **************************************************/

	protected Castle castle;                /** Castle where the production is made. */
	protected int cost;                     /** Cost of the production. */
	protected Object object;                /** Object created during the production. */
	protected int timer;                    /** Time remaining until the end of the production. */

	/* METHODS ****************************************************/

	/**
	 * Move production forward, and tell the castle if it's ended.
	 * Called at each new turn.
	 */
	public void nextTurn() {
		this.timer--;
		try {
			this.castle.finishProduction();
		} catch (ExceptionDukeNotPlayer | ExceptionEmptyProductionQueue e) {
			e.printStackTrace();
		}
	}

	/* GETTER/SETTER **********************************************/

	/**
	 * Getter on cost.
	 * @return Cost of the production.
	 */
	public int getCost() {
		return this.cost;
	}

	/**
	 * Getter on object.
	 * @return Object created during the production.
	 */
	public Object getObject() {
		return this.object;
	}

	/**
	 * Getter on timer.
	 * @return Time remaining until the end of the production.
	 */
	public int getTimer() {
		return this.timer;
	}
}
