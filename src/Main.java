import game.Board;
import render.BoardRender;
import static utils.Settings.*;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.Position;
import utils.Settings;

import java.util.ArrayList;

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
		this.scene = new Scene(rootGroup, (BOARD_CELL_STYLE_WIDTH * BOARD_DIM_WIDTH), (BOARD_CELL_STYLE_HEIGHT * BOARD_DIM_HEIGHT));
	}

	private void initializeStage(Stage stage, boolean useDefault) {
		stage.setTitle(WINDOW_TITLE);
		if (useDefault) {
			stage.setHeight(WINDOW_DEFAULT_HEIGHT);
			stage.setWidth(WINDOW_DEFAULT_WIDTH);
			stage.setMinHeight(WINDOW_MIN_HEIGHT);
			stage.setMinWidth(WINDOW_MIN_WIDTH);
			stage.setResizable(true);
		} else {
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
		this.rootGroup.getChildren().add(this.boardRender.getCanvas());

		// add timer (ApplicationHandler) to update the game
		// add a status bar (on-click: duke, level (+ revenue), nb entities, treasure)
		// add actions (status bar: add/remove production, on-click x2: send entities from first to second)
		// add pause (space bar)
		// check user interactions


		for (int i = 0; i < BOARD_NB_DUKES - 1; i++) {
			ArrayList<Position> route = board.computeArmyRoute(board.getCastles().get(i), board.getCastles().get(i + 1));
			String message = "Route from " + board.getCastles().get(i).getPosition() + " to " + board.getCastles().get(i + 1).getPosition() + ":\n";
			for (Position position : route) {
				message += "\t> " + position + "\n";
			}
			System.out.println(message);
		}
	}
}
