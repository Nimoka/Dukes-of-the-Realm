package game.castle;

import game.Duke;
import game.castle.action.Action;
import game.castle.production.EntityProduction;
import game.castle.production.LevelProduction;
import game.castle.production.Production;
import game.entity.group.Army;
import game.entity.Entity;
import game.entity.group.Stock;
import utils.Position;
import static utils.Settings.*;

public class Castle {
	/*** VARIABLES ************************************************/

	private Action currentAction;
	private CastleDirection direction;
	private Duke duke;
	private int level;
	private Position position;
	private Production production;
	private Stock stock;
	private int treasure;

	/*** CONSTRUCTORS *********************************************/

	public Castle(Duke duke, Position position) {
		this.direction = CastleDirection.getRandom();
		this.duke = duke;
		this.level = CASTLE_LEVEL;
		this.position = position;
		this.stock = new Stock(CASTLE_NB_CATAPULT, CASTLE_NB_KNIGHT, CASTLE_NB_PIKEMAN);
	}

	/*** METHODS **************************************************/

	public void launchNewAction(Castle target, int nbCatapults, int nbKnights, int nbPikemen) {
		Army army = this.stock.createArmy(nbCatapults, nbKnights, nbPikemen);
		currentAction = new Action(target, army);
		// to continue
	}

	public void launchLevelProduction() {
		if (production == null)
			this.production = new LevelProduction(this);
	}

	public void launchEntityProduction(Class<Entity> type) {
		if (production == null)
			this.production = new EntityProduction(this, type);
	}

	public void nextTurn() {
		this.treasure += CASTLE_LEVEL_GAIN(this.level);
		this.production.nextTurn();
		this.currentAction.nextTurn();
	}

	public void receiveAttack() {
		this.stock.receiveAttack();
	}

	public void terminateProduction() {
		if (production.getClass() == LevelProduction.class)
			this.level = ((LevelProduction) production).getLevel();
		else if (production.getClass() == EntityProduction.class)
			this.stock.add(((EntityProduction) production).getEntity());
		this.treasure -= this.production.getCost();
		this.production = null;
	}

	public String toString() {
		return "Castle { position: " + this.position.toString() + " }";
	}

	/*** GETTER/SETTER ********************************************/

	public CastleDirection getDirection() {
		return this.direction;
	}

	public int getLevel() {
		return this.level;
	}

	public Position getPosition() {
		return this.position;
	}

	public Stock getStock() {
		return this.stock;
	}

	public int getTreasure() {
		return this.treasure;
	}
}
