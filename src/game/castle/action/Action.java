package game.castle.action;

import game.castle.Castle;
import game.entities.Army;
import game.entities.Entity;

public class Action {
	/*** VARIABLES ************************************************/

	private Army army;
	private Castle target;

	/*** CONSTRUCTORS *********************************************/

	public Action(Castle target, Army army) {
		this.army = army;
		this.target = target;
	}

	/*** METHODS **************************************************/

	public void nextTurn() {
		for (Entity entity: this.army.getListEntities())
			entity.nextTurn();
	}

	/*** GETTER/SETTER ********************************************/

	public Army getArmy() {
		return this.army;
	}

	public Castle getTarget() {
		return this.target;
	}
}
