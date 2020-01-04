package player;

import game.duke.Duke;
import game.duke.DukeType;

/**
 * A computer player (leads by "Artificial Intelligence").
 */
public class ComputerPlayer extends Player {
	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct a computer player.
	 */
	public ComputerPlayer() {
		this.duke = new Duke(DukeType.PLAYER);
	}

	/* METHODS ****************************************************/

	/**
	 * Launch a new action or production if it can.
	 * Called at each new turn.
	 */
	@Override
	public void nextTurn() {
	}
}
