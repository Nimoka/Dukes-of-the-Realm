package player;

import game.duke.Duke;
import game.duke.DukeType;

/**
 * A user player (leads by a human using keyboard or mouse).
 */
public class UserPlayer extends Player {
	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct a user player.
	 */
	public UserPlayer() {
		this.duke = new Duke(DukeType.PLAYER);
	}
}
