import game.Board;
import render.BoardRender;
import static utils.Settings.*;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	/*** VARIABLES ************************************************/

	private Group rootGroup;
	private Scene scene;

	private Board board;
	private BoardRender boardRender;

	/*** METHODS **************************************************/

	private void createBoard() {
		this.board = new Board();
		this.boardRender = new BoardRender(this.board);
	}

	private void initializeScene() {
		this.rootGroup = new Group();
		this.scene = new Scene(rootGroup);
	}

	private void initializeStage(Stage stage, boolean useDefault) {
		stage.setTitle(WINDOW_TITLE);
		if (useDefault) {
			stage.setHeight(WINDOW_DEFAULT_HEIGHT);
			stage.setWidth(WINDOW_DEFAULT_WIDTH);
			stage.setMinHeight(WINDOW_MIN_HEIGHT);
			stage.setMinWidth(WINDOW_MIN_WIDTH);
		} else {
			stage.setHeight(DISPLAY_CELL_HEIGHT * BOARD_HEIGHT); // to change
			stage.setWidth(DISPLAY_CELL_WIDTH * BOARD_WIDTH);
			stage.setResizable(false);
		}
		initializeScene();
		stage.setScene(this.scene);
		stage.show();
	}

	@Override
	public void start(Stage stage) throws Exception {
		createBoard();
		initializeStage(stage, false);
	}
}
