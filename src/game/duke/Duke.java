package game.duke;

import utils.NameGenerator;

/**
 * Duke of board's castles.
 * Corresponds to a player on the board.
 */
public class Duke {
	/* VARIABLES **************************************************/

	private String name;                    /** Name of the duke. */
	private DukeType type;                  /** Type of the duke. */

	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct a duke of a certain type with a random name.
	 * @param type Type of the duke.
	 */
	public Duke(DukeType type) {
		this.name = NameGenerator.random();
		this.type = type;
	}

	/* METHODS ****************************************************/

	/**
	 * Write a message that contains all information of the duke.
	 * @return Message that contains all information of the duke.
	 */
	public String toString() {
		return "Duke { name: " + this.name + ", type: " + this.type + " }";
	}

	/* GETTER/SETTER **********************************************/

	/**
	 * Getter on name.
	 * @return Name of the duke.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Getter on type.
	 * @return Type of the duke.
	 */
	public DukeType getType() {
		return this.type;
	}
}
