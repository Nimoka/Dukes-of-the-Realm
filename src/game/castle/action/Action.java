package game.castle.action;

import game.castle.Castle;
import game.entity.group.Army;
import game.entity.Entity;
import utils.Position;

import java.util.ArrayList;

public class Action {
	/*** VARIABLES ************************************************/

	private Army army;
	private ArrayList<Position> moves;
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
