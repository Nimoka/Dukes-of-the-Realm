package player;

import game.duke.Duke;
import game.duke.DukeType;

public class Baron extends Player {
	/*** CONSTRUCTORS *********************************************/

	public Baron() {
		this.duke = new Duke(DukeType.BARON);
	}
}
