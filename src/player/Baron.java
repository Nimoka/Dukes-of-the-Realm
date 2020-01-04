package player;

import game.duke.Duke;
import game.duke.DukeType;

/**
 * A baron (duke that don't play).
 */
public class Baron extends Player {
	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct a baron.
	 */
	public Baron() {
		this.duke = new Duke(DukeType.BARON);
	}
}
