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

import java.util.ArrayList;
import java.util.Random;

/**
 * A computer player (leads by "Artificial Intelligence").
 */
public class ComputerPlayer extends Player {
	/* VARIABLES **************************************************/

	private Main environment;               /** The environment (application). */
	private ArrayList<Castle> castles;      /** List of dukes' castles. */

	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct a computer player.
	 */
	public ComputerPlayer(Main environment) {
		this.environment = environment;
		this.duke = new Duke(DukeType.PLAYER);
	}

	/* METHODS ****************************************************/

	/**
	 * Check if duke own new castles.
	 */
	private void checkCastles() {
		for (Castle castle: this.environment.getBoard().getCastles()) {
			if (castle.getDuke() == this.duke) {
				if (!castles.contains(castle))
					castles.add(castle);
			}
		}
	}

	/**
	 * Launch a new action or production if it can.
	 * Called at each new turn.
	 */
	@Override
	public void nextTurn() {
		checkCastles();
		Random generator = new Random();
		for (Castle castle: this.castles) {
			int value = generator.nextInt(100);
			if (value < 10) {
				if (!castle.haveAction()) {
					Castle castleToAttack;
					do {
						castleToAttack = this.environment.getBoard().randomCastle();
					} while (castleToAttack.getDuke() == this.duke);
					try {
						castle.launchNewAction(castleToAttack, generator.nextInt(castle.getStock().getNbCatapults()), generator.nextInt(castle.getStock().getNbKnights()), generator.nextInt(castle.getStock().getNbPikemen()));
					} catch (ExceptionDukeNotPlayer | ExceptionActionAlreadyLaunched e) {
						e.printStackTrace();
					}
				}
			} else if (value < 50) {
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
