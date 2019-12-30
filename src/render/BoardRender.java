package render;

import game.Board;

public class BoardRender extends Render {
	/*** VARIABLES ************************************************/

	private Board board;

	/*** CONSTRUCTORS *********************************************/

	public BoardRender(Board board) {
		this.board = board;
		initialize();
	}

	/*** METHODS **************************************************/

	protected void initialize() {
		super.initialize();
	}

	/*** GETTER/SETTER ********************************************/

	public Board getBoard() {
		return this.board;
	}
}
