package game.entity.group;

import game.entity.Catapult;
import game.entity.Knight;
import game.entity.Pikeman;

import java.util.ArrayList;

/**
 * Army of entities.
 */
public class Army extends EntityGroup {
	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct an empty army of entities.
	 */
	public Army() {
		super();
	}

	/**
	 * Construct an army of a certain number of catapults, knights and pikemen.
	 * @param nbCatapults Number of catapults.
	 * @param nbKnights Number of knights.
	 * @param nbPikemen Number of pikemen.
	 */
	public Army(int nbCatapults, int nbKnights, int nbPikemen) {
		super(nbCatapults, nbKnights, nbPikemen);
	}

	/**
	 * Construct an army using lists of catapults, knights and pikemen.
	 * @param listCatapults List of catapults.
	 * @param listKnights List of knights.
	 * @param listPikemen List of pikemen.
	 */
	public Army(ArrayList<Catapult> listCatapults, ArrayList<Knight> listKnights, ArrayList<Pikeman> listPikemen) {
		super(listCatapults, listKnights, listPikemen);
	}
}
