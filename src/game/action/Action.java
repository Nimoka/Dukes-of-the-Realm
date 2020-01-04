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

/**
 * Action commanded by a duke, from a source castle to a target. An army of entities takes a route, then attack or join the target castle.
 */
public class Action {
	/* VARIABLES **************************************************/

	private Army army;                      /** Army involved in the action. */
	private ActionState currentState;       /** State of the action. */
	private Duke duke;                      /** Duke that commands the action. */
	private ArrayList<Position> route;      /** Route that army takes to go the target. */
	private Castle source;                  /** Source castle where army come from. */
	private Castle target;                  /** Target castle where army will go. */

	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct an action depending on the source castle, the target and the army.
	 * @param source Source castle where army come from.
	 * @param target Target castle where army will go.
	 * @param army Army involved in the action.
	 */
	public Action(Castle source, Castle target, Army army) {
		this.army = army;
		this.currentState = ActionState.LAUNCH;
		this.duke = source.getDuke();
		this.source = source;
		this.target = target;
		this.route = source.getBoard().computeArmyRoute(source, target);
	}

	/* METHODS ****************************************************/

	/**
	 * Launch a number of entities from the army (depending on the size of the source castle's door) on the route.
	 * When all entities of the army are outside the source castle, the army starts to move.
	 */
	public void launch() {
		this.army.getListEntities().stream().filter(e -> (e.getCurrentState() == EntityState.SLEEP)).limit(CASTLE_DOOR_SIZE(this.source.getLevel())).forEach(e -> launch());
		if (this.army.getListEntities().stream().filter(e -> (e.getCurrentState() == EntityState.SLEEP)).count() == 0)
			this.currentState = ActionState.MOVE;
	}

	/**
	 * Give the position where an entity will be at the next turn, depending on his current position and his speed.
	 * @param position Current position of the entity.
	 * @param speed Speed of the entity.
	 * @return Next position of the entity on the road.
	 * @throws ExceptionPositionOutOfRoute Thrown if the current position of the entity is outside the route.
	 */
	public Position getNextPosition(Position position, int speed) throws ExceptionPositionOutOfRoute {
		if (!this.route.contains(position))
			throw new ExceptionPositionOutOfRoute(this.route, position);
		int index = this.route.indexOf(position);
		if ((index + speed) >= this.route.size())
			return this.route.get(this.route.size() - 1);
		return this.route.get(index + speed);
	}

	/**
	 * If action is launching: launches new entities of the army on the route.
	 * If action is moving: calls nextTurn() on each entities of the army to make them move.
	 * If action is attack: calls nextTurn() on each entities of the army to make them attack, and check elements on the target castle. If the castle is owned by the duke, the action finishes. If the target's stock of entities is empty, the castle is conquered.
	 * Called at each new turn.
	 */
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

	/**
	 * Write a message that contains all information of the action.
	 * @return Message that contains all information of the action.
	 */
	public String toString() {
		String message = "Action { from: " + this.source.toString() + ", target: " + this.target.toString() + ", army: " + this.army.toString() + ", route: [ ";
		for (Position position: this.route)
			message += position.toString() + ", ";
		message += "] }";
		return message;
	}

	/* GETTER/SETTER **********************************************/

	/**
	 * Getter on army.
	 * @return Army involved in the action.
	 */
	public Army getArmy() {
		return this.army;
	}

	/**
	 * Getter on currentState.
	 * @return State of the action.
	 */
	public ActionState getCurrentState() {
		return this.currentState;
	}

	/**
	 * Getter on source.
	 * @return Source castle where army come from.
	 */
	public Castle getSource() {
		return this.source;
	}

	/**
	 * Getter on target.
	 * @return Target castle where army will go.
	 */
	public Castle getTarget() {
		return this.target;
	}
}
