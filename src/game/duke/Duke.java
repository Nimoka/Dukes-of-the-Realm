package game.duke;

import utils.NameGenerator;

public class Duke {
	/*** VARIABLES ************************************************/

	private String name;
	private DukeType type;

	/*** CONSTRUCTORS *********************************************/

	public Duke(DukeType type) {
		this.name = NameGenerator.random();
		this.type = type;
	}

	/*** METHODS **************************************************/

	public String toString() {
		return "Duke { name: " + this.name + ", type: " + this.type + " }";
	}

	/*** GETTER/SETTER ********************************************/

	public String getName() {
		return this.name;
	}

	public DukeType getType() {
		return this.type;
	}
}
