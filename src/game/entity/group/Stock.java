package game.entity.group;

import game.entity.Catapult;
import game.entity.Knight;
import game.entity.Pikeman;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Stock extends EntityGroup {
	/*** CONSTRUCTORS *********************************************/

	public Stock() {
		super();
	}

	public Stock(int nbCatapults, int nbKnights, int nbPikemen) {
		super(nbCatapults, nbKnights, nbPikemen);
	}

	public Stock(ArrayList<Catapult> listCatapults, ArrayList<Knight> listKnights, ArrayList<Pikeman> listPikemen) {
		super(listCatapults, listKnights, listPikemen);
	}

	/*** METHODS **************************************************/

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

	public void receiveAttack() {

	}
}
