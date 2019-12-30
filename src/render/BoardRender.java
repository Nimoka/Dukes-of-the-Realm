package render;

import game.Board;

public class BoardRender extends Render {
	/*** VARIABLES ************************************************/

	private Board board;

	/*** CONSTRUCTORS *********************************************/

	public BoardRender(Board board) {
		this.board = board;
	}

	/*** METHODS **************************************************/

	public void initialize() {

	}

	/*** GETTER/SETTER ********************************************/

	public Board getBoard() {
		return this.board;
	}
}
