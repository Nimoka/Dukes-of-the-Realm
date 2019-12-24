package game.entities;

import java.util.ArrayList;

public class Army extends EntityGroup {
	/*** CONSTRUCTORS *********************************************/

	public Army() {
		super();
	}

	public Army(int nbCatapults, int nbKnights, int nbPikemen) {
		super(nbCatapults, nbKnights, nbPikemen);
	}

	public Army(ArrayList<Catapult> listCatapults, ArrayList<Knight> listKnights, ArrayList<Pikeman> listPikemen) {
		super(listCatapults, listKnights, listPikemen);
	}
}
