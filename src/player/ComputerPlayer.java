package player;

import game.duke.Duke;
import game.duke.DukeType;

public class ComputerPlayer extends Player {
	/*** CONSTRUCTORS *********************************************/

	public ComputerPlayer() {
		this.duke = new Duke(DukeType.PLAYER);
	}

	/*** METHODS **************************************************/

	@Override
	public void nextTurn() {
		System.err.println("New turn action asked for " + this.duke.getName());
	}
}
