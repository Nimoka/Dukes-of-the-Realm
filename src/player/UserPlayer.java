package player;

import game.duke.Duke;
import game.duke.DukeType;

public class UserPlayer extends Player {
	/*** CONSTRUCTORS *********************************************/

	public UserPlayer() {
		this.duke = new Duke(DukeType.PLAYER);
	}
}
