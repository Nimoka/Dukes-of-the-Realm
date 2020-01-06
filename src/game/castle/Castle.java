package game.castle;

import exceptions.ExceptionActionAlreadyLaunched;
import exceptions.ExceptionDukeNotPlayer;
import exceptions.ExceptionEmptyProductionQueue;
import game.Board;
import game.duke.Duke;
import game.duke.DukeType;
import game.action.Action;
import game.production.EntityProduction;
import game.production.LevelProduction;
import game.production.Production;
import game.entity.group.Army;
import game.entity.Entity;
import game.entity.group.Stock;
import utils.Position;
import static utils.Settings.*;

import java.io.Serializable;
import java.util.*;

/**
 * Castle on the board, owned by a duke.
 */
public class Castle implements Serializable {
	/* VARIABLES **************************************************/

	private Board board;                    /** Board where the castle is. */
	private Action currentAction;           /** Current action involving the castle. */
	private CastleDirection direction;      /** Direction of the castle's door. */
	private Duke duke;                      /** Duke that own this castle. */
	private int level;                      /** Current level of the castle. */
	private Position position;              /** Position of the castle on the board. */
	private Queue<Production> productions;  /** Production queue of the castle. */
	private Stock stock;                    /** Stock of entities of the castle. */
	private int treasure;                   /** Treasure of the castle. */

	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct a castle on a position of the board, owned by a duke.
	 * @param board Board where the castle is.
	 * @param duke Duke that own this castle.
	 * @param position Position of the castle on the board.
	 */
	public Castle(Board board, Duke duke, Position position) {
		this.board = board;
		this.direction = CastleDirection.random();
		this.duke = duke;
		this.position = position;
		this.productions = new LinkedList<>();
		if (duke.getType() == DukeType.PLAYER)
			initializeDukePlayer();
		else if (duke.getType() == DukeType.BARON)
			initializeDukeBaron();
	}

	/* METHODS ****************************************************/

	/**
	 * Clear the production queue.
	 */
	public void clearProductionQueue() {
		productions.poll();
		try {
			while (productions.size() != 0)
				removeLastProduction(true);
		} catch (ExceptionEmptyProductionQueue | ExceptionDukeNotPlayer e) {
			e.printStackTrace();
		}
	}

	/**
	 * Finish the current action.
	 */
	public void finishAction() {
		this.currentAction = null;
	}

	/**
	 * Finish the current production (first on the production queue).
	 * @throws ExceptionDukeNotPlayer Thrown if the duke of the castle is not playing: he couldn't ask a production.
	 * @throws ExceptionEmptyProductionQueue Thrown if the production queue is empty: there is no current production.
	 */
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
		System.out.println("[Castle] Production finished.");
	}

	/**
	 * Tell if there is an action.
	 * @return There is an action.
	 */
	public boolean haveAction() {
		return (this.currentAction != null);
	}

	/**
	 * Tell if there is a production.
	 * @return There is a production.
	 */
	public boolean haveProduction() {
		return (this.productions.peek() != null);
	}

	/**
	 * Initialize the level and the stock of the castle, considering the duke as a player (duke).
	 */
	private void initializeDukePlayer() {
		this.level = CASTLE_DEFAULT_LEVEL;
		this.stock = new Stock(CASTLE_DEFAULT_NB_CATAPULT, CASTLE_DEFAULT_NB_KNIGHT, CASTLE_DEFAULT_NB_PIKEMAN);
	}

	/**
	 * Initialize the level and the stock of the castle, considering the duke as a not a player (baron).
	 */
	private void initializeDukeBaron() {
		Random generator = new Random();
		this.level = CASTLE_BARON_LEVEL_MIN + generator.nextInt(CASTLE_BARON_LEVEL_MAX - CASTLE_BARON_LEVEL_MIN);
		this.stock = new Stock((CASTLE_BARON_NB_CATAPULT_MIN + generator.nextInt(CASTLE_BARON_NB_CATAPULT_MAX - CASTLE_BARON_NB_CATAPULT_MIN)), (CASTLE_BARON_NB_KNIGHT_MIN + generator.nextInt(CASTLE_BARON_NB_KNIGHT_MAX - CASTLE_BARON_NB_KNIGHT_MIN)), (CASTLE_BARON_NB_PIKEMAN_MIN + generator.nextInt(CASTLE_BARON_NB_PIKEMAN_MAX - CASTLE_BARON_NB_PIKEMAN_MIN)));
		this.treasure = CASTLE_BARON_TREASURE_MIN + generator.nextInt(CASTLE_BARON_TREASURE_MAX - CASTLE_BARON_TREASURE_MIN);
	}

	/**
	 * Launch a new action of an army to a target.
	 * @param target Target castle where army will go.
	 * @param nbCatapults Number of catapults in the army.
	 * @param nbKnights Number of knights in the army.
	 * @param nbPikemen Number of pikemen in the army.
	 * @throws ExceptionDukeNotPlayer Thrown if the duke of the castle is not playing: he can't launch an action.
	 * @throws ExceptionActionAlreadyLaunched Thrown if the castle leads already an action: he can't have two actions at the same time.
	 */
	public void launchNewAction(Castle target, int nbCatapults, int nbKnights, int nbPikemen) throws ExceptionDukeNotPlayer, ExceptionActionAlreadyLaunched {
		if (this.duke.getType() == DukeType.BARON)
			throw new ExceptionDukeNotPlayer(this.duke, "launchNewAction");
		if (this.currentAction != null)
			throw new ExceptionActionAlreadyLaunched(this);
		Army army = this.stock.createArmy(nbCatapults, nbKnights, nbPikemen);
		currentAction = new Action(this, target, army);
		this.board.getEnvironment().launchArmyRender(army);
	}

	/**
	 * Add a level production to the queue, and take the cost in the treasure of the castle.
	 * @throws ExceptionDukeNotPlayer Thrown if the duke of the castle is not playing: he can't ask a production.
	 */
	public void launchLevelProduction() throws ExceptionDukeNotPlayer {
		if (this.duke.getType() == DukeType.BARON)
			throw new ExceptionDukeNotPlayer(this.duke, "launchLevelProduction");
		Production production = new LevelProduction(this);
		this.productions.add(production);
		this.treasure -= production.getCost();
	}

	/**
	 * Add a entity production to the queue, and take the cost in the treasure of the castle.
	 * @param type Type of the entity to make.
	 * @throws ExceptionDukeNotPlayer Thrown if the duke of the castle is not playing: he can't ask a production.
	 */
	public void launchEntityProduction(Class<? extends Entity> type) throws ExceptionDukeNotPlayer {
		if (this.duke.getType() == DukeType.BARON)
			throw new ExceptionDukeNotPlayer(this.duke, "launchEntityProduction");
		Production production = new EntityProduction(this, type);
		this.productions.add(production);
		this.treasure -= production.getCost();
	}

	/**
	 * Increase the castle's treasure, call nextTurn() on the current action and production.
	 * Called at each new turn.
	 */
	public void nextTurn() {
		this.treasure += CASTLE_LEVEL_GAIN(this.level, this.duke.getType());
		if (this.currentAction != null)
			this.currentAction.nextTurn();
		Production production = this.productions.peek();
		if (production != null)
			production.nextTurn();
	}

	/**
	 * Receive an attack from an opponent's action.
	 */
	public void receiveAttack() {
		this.stock.receiveAttack();
	}

	/**
	 * The castle is conquered by a duke during his action.
	 * @param newDuke New duke of the castle.
	 */
	public void receiveConquer(Duke newDuke) {
		this.duke = newDuke;
		this.productions.clear();
	}

	/**
	 * Remove the last production of the queue.
	 * @param isCleanMode Tell the context of call (true = during cleaning).
	 * @throws ExceptionDukeNotPlayer Thrown if the duke of the castle is not playing: he couldn't ask a production.
	 * @throws ExceptionEmptyProductionQueue Thrown if the production queue is empty: there is no current production.
	 */
	public void removeLastProduction(boolean isCleanMode) throws ExceptionEmptyProductionQueue, ExceptionDukeNotPlayer {
		if (this.duke.getType() == DukeType.BARON)
			throw new ExceptionDukeNotPlayer(this.duke, "launchEntityProduction");
		Production production = this.productions.poll();
		if (production == null)
			throw new ExceptionEmptyProductionQueue(this);
		if ((this.productions.peek() != null) || isCleanMode)
		this.treasure += production.getCost();
	}

	/**
	 * Write a message that contains all information of the castle.
	 * @return Message that contains all information of the castle.
	 */
	public String toString() {
		return "Castle { duke: " + this.duke.toString() + ", position: " + this.position.toString() + " }";
	}

	/* GETTER/SETTER **********************************************/

	/**
	 * Getter on board.
	 * @return Board where the castle is.
	 */
	public Board getBoard() {
		return this.board;
	}

	/**
	 * Getter on direction.
	 * @return Direction of the castle's door.
	 */
	public CastleDirection getDirection() {
		return this.direction;
	}

	/**
	 * Getter on duke.
	 * @return Duke that own this castle.
	 */
	public Duke getDuke() {
		return this.duke;
	}

	/**
	 * Getter on level.
	 * @return Current level of the castle.
	 */
	public int getLevel() {
		return this.level;
	}

	/**
	 * Getter on position.
	 * @return Position of the castle on the board.
	 */
	public Position getPosition() {
		return this.position;
	}

	/**
	 * Getter on stock.
	 * @return Stock of entities of the castle.
	 */
	public Stock getStock() {
		return this.stock;
	}

	/**
	 * Getter on treasure.
	 * @return Treasure of the castle.
	 */
	public int getTreasure() {
		return this.treasure;
	}
}
