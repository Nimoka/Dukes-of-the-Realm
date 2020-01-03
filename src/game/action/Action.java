package game.action;

import exceptions.ExceptionPositionOutOfRoute;
import game.duke.Duke;
import game.castle.Castle;
import game.entity.EntityState;
import game.entity.group.Army;
import game.entity.Entity;
import utils.Position;
import static utils.Settings.*;

import java.util.ArrayList;

public class Action {
	/*** VARIABLES ************************************************/

	private Army army;
	private ActionState currentState;
	private Duke duke;
	private ArrayList<Position> route;
	private Castle source;
	private Castle target;

	/*** CONSTRUCTORS *********************************************/

	public Action(Castle source, Castle target, Army army) {
		this.army = army;
		this.currentState = ActionState.LAUNCH;
		this.duke = source.getDuke();
		this.source = source;
		this.target = target;
		this.route = source.getBoard().computeArmyRoute(source, target);
	}

	/*** METHODS **************************************************/

	public void launch() {
		this.army.getListEntities().stream().filter(e -> (e.getCurrentState() == EntityState.SLEEP)).limit(CASTLE_DOOR_SIZE(this.source.getLevel())).forEach(e -> launch());
		if (this.army.getListEntities().stream().filter(e -> (e.getCurrentState() == EntityState.SLEEP)).count() == 0)
			this.currentState = ActionState.MOVE;
	}

	public Position getNextPosition(Position position, int speed) throws ExceptionPositionOutOfRoute {
		if (!this.route.contains(position))
			throw new ExceptionPositionOutOfRoute(this.route, position);
		int index = this.route.indexOf(position);
		if ((index + speed) >= this.route.size())
			return this.route.get(this.route.size() - 1);
		return this.route.get(index + speed);
	}

	public void nextTurn() {
		if (this.currentState == ActionState.LAUNCH) {
			launch();
		} else if (this.currentState == ActionState.MOVE) {
			for (Entity entity : this.army.getListEntities())
				entity.nextTurn();
		} else if (this.currentState == ActionState.ATTACK) {
			for (Entity entity : this.army.getListEntities())
				entity.nextTurn();
			if (this.target.getDuke() == this.source.getDuke())
				this.source.finishAction();
			if (this.target.getStock().getNbEntities() == 0)
				this.target.receiveConquer(this.duke);
		}
	}

	public String toString() {
		String message = "Action { from: " + this.source.toString() + ", target: " + this.target.toString() + ", army: " + this.army.toString() + ", route: [ ";
		for (Position position: this.route)
			message += position.toString() + ", ";
		message += "] }";
		return message;
	}

	/*** GETTER/SETTER ********************************************/

	public Army getArmy() {
		return this.army;
	}

	public ActionState getCurrentState() {
		return this.currentState;
	}

	public Castle getSource() {
		return this.source;
	}

	public Castle getTarget() {
		return this.target;
	}
}
