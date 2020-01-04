package game.entity.group;

import game.entity.Catapult;
import game.entity.Entity;
import game.entity.Knight;
import game.entity.Pikeman;

import java.util.ArrayList;

/**
 * Group of entities.
 */
public abstract class EntityGroup {
	/* VARIABLES **************************************************/

	protected ArrayList<Catapult> listCatapults;    /** List of catapults. */
	protected ArrayList<Knight> listKnights;        /** List of knights. */
	protected ArrayList<Pikeman> listPikemen;       /** List of pikemen. */

	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct an empty group of entities.
	 */
	public EntityGroup() {
		this.listCatapults = new ArrayList<>();
		this.listKnights = new ArrayList<>();
		this.listPikemen = new ArrayList<>();
	}

	/**
	 * Construct a group of a certain number of catapults, knights and pikemen
	 * @param nbCatapults Number of catapults.
	 * @param nbKnights Number of knights.
	 * @param nbPikemen Number of pikemen.
	 */
	public EntityGroup(int nbCatapults, int nbKnights, int nbPikemen) {
		this();
		this.createCatapults(nbCatapults);
		this.createKnights(nbKnights);
		this.createPikemen(nbPikemen);
	}

	/**
	 * Construct a group of entities using lists of catapults, knights and pikemen.
	 * @param listCatapults List of catapults.
	 * @param listKnights List of knights.
	 * @param listPikemen List of pikemen.
	 */
	public EntityGroup(ArrayList<Catapult> listCatapults, ArrayList<Knight> listKnights, ArrayList<Pikeman> listPikemen) {
		this.listCatapults = listCatapults;
		this.listKnights = listKnights;
		this.listPikemen = listPikemen;
	}

	/* METHODS ****************************************************/

	/**
	 * Add an entity to the group.
	 * @param entity Entity to add.
	 */
	public void addEntity(Entity entity) {
		if (entity.getClass() == Catapult.class)
			this.listCatapults.add((Catapult) entity);
		else if (entity.getClass() == Knight.class)
			this.listKnights.add((Knight) entity);
		else if (entity.getClass() == Pikeman.class)
			this.listPikemen.add((Pikeman) entity);
	}

	/**
	 * Create a number of new catapults.
	 * @param nb Number of catapults to create.
	 */
	private void createCatapults(int nb) {
		for (int i = 0; i < nb; i++)
			this.listCatapults.add(new Catapult());
	}

	/**
	 * Create a number of new knights.
	 * @param nb Number of knights to create.
	 */
	private void createKnights(int nb) {
		for (int i = 0; i < nb; i++)
			this.listKnights.add(new Knight());
	}

	/**
	 * Create a number of new pikemen.
	 * @param nb Number of pikemen to create.
	 */
	private void createPikemen(int nb) {
		for (int i = 0; i < nb; i++)
			this.listPikemen.add(new Pikeman());
	}

	/**
	 * Get an entire list of all entities on the group.
	 * @return List of all entities.
	 */
	public ArrayList<Entity> getListEntities() {
		ArrayList<Entity> result = new ArrayList<>();
		result.addAll(this.listPikemen);
		result.addAll(this.listKnights);
		result.addAll(this.listCatapults);
		return result;
	}

	/**
	 * Get the number of catapults on the group.
	 * @return Number of catapults.
	 */
	public int getNbCatapults() {
		return this.listCatapults.size();
	}

	/**
	 * Get the number of entities on the group.
	 * @return Number of entities.
	 */
	public int getNbEntities() {
		return ((this.listCatapults.size()) + (this.listKnights.size()) + (this.listPikemen.size()));
	}

	/**
	 * Get the number of knights on the group.
	 * @return Number of knights.
	 */
	public int getNbKnights() {
		return this.listKnights.size();
	}

	/**
	 * Get the number of pikemen on the group.
	 * @return Number of pikemen.
	 */
	public int getNbPikemen() {
		return this.listPikemen.size();
	}

	/**
	 * Remove an entity on the group.
	 * @param entity Entity to remove.
	 */
	public void removeEntity(Entity entity) {
		if (entity.getClass() == Catapult.class)
			this.listCatapults.remove((Catapult) entity);
		else if (entity.getClass() == Knight.class)
			this.listKnights.remove((Knight) entity);
		else if (entity.getClass() == Pikeman.class)
			this.listPikemen.remove((Pikeman) entity);
	}

	/**
	 * Write a message that contains all information of the group of entities.
	 * @return Message that contains all information of the group of entities.
	 */
	public String toString() {
		String message = this.getClass().toString() + " { catapults: [ ";
		for (Catapult catapult: this.listCatapults)
			message += catapult.toString() + ", ";
		message += "], knights: [ ";
		for (Knight knight: this.listKnights)
			message += knight.toString() + ", ";
		message += "], pikemen: [ ";
		for (Pikeman pikeman: this.listPikemen)
			message += pikeman.toString() + ", ";
		message += "] }";
		return message;
	}

	/* GETTER/SETTER **********************************************/

	/**
	 * Getter on listCatapults.
	 * @return List of catapults.
	 */
	public ArrayList<Catapult> getListCatapults() {
		return this.listCatapults;
	}

	/**
	 * Getter on listKnights.
	 * @return List of knights.
	 */
	public ArrayList<Knight> getListKnights() {
		return this.listKnights;
	}

	/**
	 * Getter on listPikemen.
	 * @return List of pikemen.
	 */
	public ArrayList<Pikeman> getListPikemen() {
		return this.listPikemen;
	}
}
