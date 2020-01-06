package player;

import exceptions.ExceptionActionAlreadyLaunched;
import exceptions.ExceptionDukeNotPlayer;
import game.castle.Castle;
import game.duke.Duke;
import game.duke.DukeType;
import game.entity.Catapult;
import game.entity.Knight;
import game.entity.Pikeman;
import main.Main;
import static utils.Settings.*;

import java.util.Random;

/**
 * A computer player (leads by "Artificial Intelligence").
 */
public class ComputerPlayer extends Player {
	/* VARIABLES **************************************************/

	private Main environment;               /** The environment (application). */

	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct a computer player.
	 * @param environment The environment (application).
	 */
	public ComputerPlayer(Main environment) {
		this.environment = environment;
		this.duke = new Duke(DukeType.PLAYER);
	}

	/* METHODS ****************************************************/

	/**
	 * Launch a new action or production if it can.
	 * Called at each new turn.
	 */
	@Override
	public void nextTurn() {
		Castle castle = this.environment.getBoard().randomCastle();
		if (castle.getDuke() == this.duke) {
			Random generator = new Random();
			int value = generator.nextInt(100);
			if (value < 30) {
				if (!castle.haveAction()) {
					Castle castleToAttack;
					do {
						castleToAttack = this.environment.getBoard().randomCastle();
					} while (castleToAttack.getDuke() == this.duke);
					try {
						int nbCatapults = 0, nbKnights = 0, nbPikemen = 0;
						if (castle.getStock().getNbCatapults() > 0)
							nbCatapults = generator.nextInt(castle.getStock().getNbCatapults());
						if (castle.getStock().getNbKnights() > 0)
							nbKnights = generator.nextInt(castle.getStock().getNbKnights());
						if (castle.getStock().getNbPikemen() > 0)
							nbPikemen = generator.nextInt(castle.getStock().getNbPikemen());
						castle.launchNewAction(castleToAttack, nbCatapults, nbKnights, nbPikemen);
					} catch (ExceptionDukeNotPlayer | ExceptionActionAlreadyLaunched e) {
						e.printStackTrace();
					}
				}
			} else {
				if (!castle.haveProduction()) {
					value = generator.nextInt(100);
					if (value < 40) {
						if (castle.getTreasure() >= ENTITY_PIKEMAN_PROD_COST) {
							try {
								castle.launchEntityProduction(Pikeman.class);
							} catch (ExceptionDukeNotPlayer e) {
								e.printStackTrace();
							}
						}
					} else if (value < 70) {
						if (castle.getTreasure() >= ENTITY_KNIGHT_PROD_COST) {
							try {
								castle.launchEntityProduction(Knight.class);
							} catch (ExceptionDukeNotPlayer e) {
								e.printStackTrace();
							}
						}
					} else if (value < 90) {
						if (castle.getTreasure() >= ENTITY_CATAPULT_PROD_COST) {
							try {
								castle.launchEntityProduction(Catapult.class);
							} catch (ExceptionDukeNotPlayer e) {
								e.printStackTrace();
							}
						}
					} else {
						if (castle.getTreasure() >= CASTLE_LEVEL_PROD_COST(castle.getLevel())) {
							try {
								castle.launchLevelProduction();
							} catch (ExceptionDukeNotPlayer e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
}
