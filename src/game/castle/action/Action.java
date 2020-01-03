package game.castle.action;

import exceptions.game.ExceptionPositionOutOfRoute;
import game.castle.Castle;
import game.entity.group.Army;
import game.entity.Entity;
import utils.Position;

import java.util.ArrayList;

public class Action {
	/*** VARIABLES ************************************************/

	private Army army;
	private ArrayList<Position> route;
	private Castle source;
	private Castle target;

	/*** CONSTRUCTORS *********************************************/

	public Action(Castle source, Castle target, Army army) {
		this.army = army;
		this.source = source;
		this.target = target;
		this.route = source.getBoard().computeArmyRoute(source, target);
	}

	/*** METHODS **************************************************/

	public Position getNextPosition(Position position, int speed) throws ExceptionPositionOutOfRoute {
		if (!this.route.contains(position))
			throw new ExceptionPositionOutOfRoute(this.route, position);
		int index = this.route.indexOf(position);
		if ((index + speed) >= this.route.size())
			return this.route.get(this.route.size() - 1);
		return this.route.get(index + speed);
	}

	public void nextTurn() {
		// if begins: send 3 entities (slowest) per turn
		// if target is owned by the source: entities join the castle
		// if target stock is empty: target is owned (keep only treasure and level)
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
