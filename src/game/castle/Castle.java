package game.castle;

import game.castle.action.Action;
import game.castle.production.EntityProduction;
import game.castle.production.LevelProduction;
import game.castle.production.Production;
import game.entities.Entity;
import game.entities.EntityGroup;
import utils.Position;
import utils.Settings;

public class Castle {
	/*** VARIABLES ************************************************/

	private Action currentAction;
	private int level;
	private Position position;
	private Production production;
	private EntityGroup stock;
	private int treasure;

	/*** CONSTRUCTORS *********************************************/

	public Castle(Position position) {
		this.level = Settings.CASTLE_LEVEL;
		this.position = position;
		this.stock = new EntityGroup(Settings.CASTLE_NB_CATAPULT, Settings.CASTLE_NB_KNIGHT, Settings.CASTLE_NB_PIKEMAN);
	}

	/*** METHODS **************************************************/

	public void launchNewAction(Castle target, int nbCatapults, int nbKnights, int nbPikemen) {
		EntityGroup army = stock.split(nbCatapults, nbKnights, nbPikemen);
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
		this.treasure += Settings.CASTLE_LEVEL_GAIN(this.level);
		this.production.nextTurn();
		this.currentAction.nextTurn();
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

	public int getLevel() {
		return this.level;
	}

	public Position getPosition() {
		return this.position;
	}

	public EntityGroup getStock() {
		return this.stock;
	}

	public int getTreasure() {
		return this.treasure;
	}
}
