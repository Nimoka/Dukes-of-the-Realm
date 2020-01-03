package game.castle;

import exceptions.ExceptionDukeNotPlayer;
import exceptions.ExceptionEmptyProductionQueue;
import game.Board;
import game.Duke;
import game.DukeType;
import game.action.Action;
import game.production.EntityProduction;
import game.production.LevelProduction;
import game.production.Production;
import game.entity.group.Army;
import game.entity.Entity;
import game.entity.group.Stock;
import utils.Position;
import static utils.Settings.*;

import java.util.*;

public class Castle {
	/*** VARIABLES ************************************************/

	private Board board;
	private Action currentAction;
	private CastleDirection direction;
	private Duke duke;
	private int level;
	private Position position;
	private Queue<Production> productions;
	private Stock stock;
	private int treasure;

	/*** CONSTRUCTORS *********************************************/

	public Castle(Board board, Duke duke, Position position) {
		this.board = board;
		this.direction = CastleDirection.random();
		this.duke = duke;
		this.position = position;
		this.productions = new PriorityQueue<>();
		if (duke.getType() == DukeType.PLAYER)
			initializeDukePlayer();
		else if (duke.getType() == DukeType.BARON)
			initializeDukeBaron();
	}

	/*** METHODS **************************************************/

	public void finishAction() {
		this.currentAction = null;
	}

	public void finishProduction() throws ExceptionDukeNotPlayer, ExceptionEmptyProductionQueue {
		if (this.duke.getType() == DukeType.BARON)
			throw new ExceptionDukeNotPlayer(this.duke, "terminateProduction");
		Production production = this.productions.poll();
		if (production == null)
			throw new ExceptionEmptyProductionQueue(this);
		if (production.getClass() == LevelProduction.class)
			this.level = ((LevelProduction) production).getLevel();
		else if (production.getClass() == EntityProduction.class)
			this.stock.addEntity(((EntityProduction) production).getEntity());
		this.treasure -= production.getCost();
	}

	private void initializeDukePlayer() {
		this.level = CASTLE_DEFAULT_LEVEL;
		this.stock = new Stock(CASTLE_DEFAULT_NB_CATAPULT, CASTLE_DEFAULT_NB_KNIGHT, CASTLE_DEFAULT_NB_PIKEMAN);
	}

	private void initializeDukeBaron() {
		Random generator = new Random();
		this.level = CASTLE_BARON_LEVEL_MIN + generator.nextInt(CASTLE_BARON_LEVEL_MAX - CASTLE_BARON_LEVEL_MIN);
		this.stock = new Stock((CASTLE_BARON_NB_CATAPULT_MIN + generator.nextInt(CASTLE_BARON_NB_CATAPULT_MAX - CASTLE_BARON_NB_CATAPULT_MIN)), (CASTLE_BARON_NB_KNIGHT_MIN + generator.nextInt(CASTLE_BARON_NB_KNIGHT_MAX - CASTLE_BARON_NB_KNIGHT_MIN)), (CASTLE_BARON_NB_PIKEMAN_MIN + generator.nextInt(CASTLE_BARON_NB_PIKEMAN_MAX - CASTLE_BARON_NB_PIKEMAN_MIN)));
		this.treasure = CASTLE_BARON_TREASURE_MIN + generator.nextInt(CASTLE_BARON_TREASURE_MAX - CASTLE_BARON_TREASURE_MIN);
	}

	public void launchNewAction(Castle target, int nbCatapults, int nbKnights, int nbPikemen) throws ExceptionDukeNotPlayer {
		if (this.duke.getType() == DukeType.BARON)
			throw new ExceptionDukeNotPlayer(this.duke, "launchNewAction");
		Army army = this.stock.createArmy(nbCatapults, nbKnights, nbPikemen);
		currentAction = new Action(this, target, army);
		// to continue
	}

	public void launchLevelProduction() throws ExceptionDukeNotPlayer {
		if (this.duke.getType() == DukeType.BARON)
			throw new ExceptionDukeNotPlayer(this.duke, "launchLevelProduction");
		this.productions.add(new LevelProduction(this));
	}

	public void launchEntityProduction(Class<Entity> type) throws ExceptionDukeNotPlayer {
		if (this.duke.getType() == DukeType.BARON)
			throw new ExceptionDukeNotPlayer(this.duke, "launchEntityProduction");
		this.productions.add(new EntityProduction(this, type));
	}

	public void nextTurn() {
		this.treasure += CASTLE_LEVEL_GAIN(this.level, this.duke.getType());
		this.productions.peek().nextTurn();
		this.currentAction.nextTurn();
	}

	public void receiveAttack() {
		this.stock.receiveAttack();
	}

	public void receiveConquer(Duke newDuke) {
		this.duke = newDuke;
		this.productions.clear();
	}

	public String toString() {
		return "Castle { duke: " + this.duke.toString() + ", position: " + this.position.toString() + " }";
	}

	/*** GETTER/SETTER ********************************************/

	public Board getBoard() {
		return this.board;
	}

	public CastleDirection getDirection() {
		return this.direction;
	}

	public Duke getDuke() {
		return this.duke;
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
