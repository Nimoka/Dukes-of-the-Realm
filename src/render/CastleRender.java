package render;

import game.Board;
import game.castle.Castle;

public class CastleRender extends Render {
	/*** VARIABLES ************************************************/

	private Castle castle;

	/*** CONSTRUCTORS *********************************************/

	public CastleRender(Castle castle) {
		this.castle = castle;
	}

	/*** METHODS **************************************************/

	public void initialize() {

	}

	/*** GETTER/SETTER ********************************************/

	public Castle getCastle() {
		return this.castle;
	}
}
