package game.castle.action;

import game.castle.Castle;
import game.entities.EntityGroup;

public class Action {
	/*** VARIABLES ************************************************/

	private EntityGroup army;
	private Castle target;

	/*** CONSTRUCTORS *********************************************/

	public Action(Castle target, EntityGroup army) {
		this.army = army;
		this.target = target;
	}
}
