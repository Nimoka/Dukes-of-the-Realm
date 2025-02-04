package game.entity.group;

import game.entity.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Stock of entities of a castle.
 */
public class Stock extends EntityGroup {
	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct an empty stock.
	 */
	public Stock() {
		super();
	}

	/**
	 * Construct a stock of a certain number of catapults, knights and pikemen.
	 * @param nbCatapults Number of catapults.
	 * @param nbKnights Number of knights.
	 * @param nbPikemen Number of pikemen.
	 */
	public Stock(int nbCatapults, int nbKnights, int nbPikemen) {
		super(nbCatapults, nbKnights, nbPikemen);
	}

	/**
	 * Construct a stock using lists of catapults, knights and pikemen
	 * @param listCatapults List of catapults.
	 * @param listKnights List of knights.
	 * @param listPikemen List of pikemen.
	 */
	public Stock(ArrayList<Catapult> listCatapults, ArrayList<Knight> listKnights, ArrayList<Pikeman> listPikemen) {
		super(listCatapults, listKnights, listPikemen);
	}

	/* METHODS ****************************************************/

	/**
	 * Create an army of certain number of catapults, knights and pikemen took on the stock.
	 * @param nbCatapults Number of catapults.
	 * @param nbKnights Number of knights.
	 * @param nbPikemen Number of Pikemen.
	 * @return Army of entities.
	 */
	public Army createArmy(int nbCatapults, int nbKnights, int nbPikemen) {
		ArrayList<Catapult> listCatapults = this.listCatapults.stream().limit(nbCatapults).collect(Collectors.toCollection(ArrayList::new));
		ArrayList<Knight> listKnights = this.listKnights.stream().limit(nbKnights).collect(Collectors.toCollection(ArrayList::new));
		ArrayList<Pikeman> listPikemen = this.listPikemen.stream().limit(nbPikemen).collect(Collectors.toCollection(ArrayList::new));
		Army army = new Army(listCatapults, listKnights, listPikemen);
		this.listCatapults.removeAll(listCatapults);
		this.listKnights.removeAll(listKnights);
		this.listPikemen.removeAll(listPikemen);
		return army;
	}

	/**
	 * Remove health point to a random entity.
	 */
	public void receiveAttack() {
		EntityType type;
		do {
			type = EntityType.random();
		} while (((type == EntityType.CATAPULT) && (listCatapults.size() == 0)) || ((type == EntityType.KNIGHT) && (listKnights.size() == 0)) || ((type == EntityType.PIKEMAN) && (listPikemen.size() == 0)));
		if (type == EntityType.CATAPULT) {
			Catapult catapult = this.listCatapults.get(0);
			catapult.receiveAttack();
			if (catapult.isDead())
				this.listCatapults.remove(catapult);
		} else if (type == EntityType.KNIGHT) {
			Knight knight = this.listKnights.get(0);
			knight.receiveAttack();
			if (knight.isDead())
				this.listKnights.remove(knight);
		} else if (type == EntityType.PIKEMAN) {
			Pikeman pikeman = this.listPikemen.get(0);
			pikeman.receiveAttack();
			if (pikeman.isDead())
				this.listPikemen.remove(pikeman);
		}
	}
}
