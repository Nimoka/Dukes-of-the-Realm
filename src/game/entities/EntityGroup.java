package game.entities;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class EntityGroup {
	/*** VARIABLES ************************************************/

	private ArrayList<Catapult> listCatapults;
	private ArrayList<Knight> listKnights;
	private ArrayList<Pikeman> listPikemen;

	/*** CONSTRUCTORS *********************************************/

	public EntityGroup() {
		this.listCatapults = new ArrayList<>();
		this.listKnights = new ArrayList<>();
		this.listPikemen = new ArrayList<>();
	}

	public EntityGroup(int nbCatapults, int nbKnights, int nbPikemen) {
		this();
		this.addCatapults(nbCatapults);
		this.addKnights(nbKnights);
		this.addPikemen(nbPikemen);
	}

	public EntityGroup(ArrayList<Catapult> listCatapults, ArrayList<Knight> listKnights, ArrayList<Pikeman> listPikemen) {
		this.listCatapults = listCatapults;
		this.listKnights = listKnights;
		this.listPikemen = listPikemen;
	}

	/*** METHODS **************************************************/

	private void addCatapults(int nb) {
		for (int i = 0; i < nb; i++)
			this.listCatapults.add(new Catapult());
	}

	private void addKnights(int nb) {
		for (int i = 0; i < nb; i++)
			this.listKnights.add(new Knight());
	}

	private void addPikemen(int nb) {
		for (int i = 0; i < nb; i++)
			this.listPikemen.add(new Pikeman());
	}

	public EntityGroup split(int nbCatapults, int nbKnights, int nbPikemen) {
		ArrayList<Catapult> listCatapults = this.listCatapults.stream().limit(nbCatapults).collect(Collectors.toCollection(ArrayList::new));
		ArrayList<Knight> listKnights = this.listKnights.stream().limit(nbKnights).collect(Collectors.toCollection(ArrayList::new));
		ArrayList<Pikeman> listPikemen = this.listPikemen.stream().limit(nbPikemen).collect(Collectors.toCollection(ArrayList::new));
		EntityGroup result = new EntityGroup(listCatapults, listKnights, listPikemen);
		this.listCatapults.removeAll(listCatapults);
		this.listKnights.removeAll(listKnights);
		this.listPikemen.removeAll(listPikemen);
		return result;
	}

	/*** GETTER/SETTER ********************************************/

	public ArrayList<Catapult> getListCatapults() {
		return this.listCatapults;
	}

	public ArrayList<Entity> getListEntities() {
		ArrayList<Entity> result = new ArrayList<>();
		result.addAll(this.listCatapults);
		result.addAll(this.listKnights);
		result.addAll(this.listPikemen);
		return result;
	}

	public ArrayList<Knight> getListKnights() {
		return this.listKnights;
	}

	public ArrayList<Pikeman> getListPikemen() {
		return this.listPikemen;
	}

	public int getNbCatapults() {
		return this.listCatapults.size();
	}

	public int getNbEntities() {
		return ((this.listCatapults.size()) + (this.listKnights.size()) + (this.listPikemen.size()));
	}

	public int getNbKnights() {
		return this.listKnights.size();
	}

	public int getNbPikemen() {
		return this.listPikemen.size();
	}
}
