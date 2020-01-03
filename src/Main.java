import game.Board;
import render.BoardRender;
import static utils.Settings.*;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	/*** VARIABLES ************************************************/

	private Group rootGroup;
	private Scene scene;
	private AnimationTimer timer;

	private Board board;
	private BoardRender boardRender;

	/*** METHODS **************************************************/

	private void createBoard() {
		this.board = new Board();
		this.boardRender = new BoardRender(this.board);
	}

	private void initializeTimer() {
		timer = new AnimationTimer() {
			long start = 0;
			@Override
			public void handle(long now) {
				if ((now - start) >= (GAME_TURN_DURATION * 1000000000)) {
					updateGame();
					start = now;
				}
			}
		};
		timer.start();
	}

	private void initializeScene() {
		this.rootGroup = new Group();
		this.scene = new Scene(rootGroup, (BOARD_CELL_STYLE_WIDTH * BOARD_DIM_WIDTH), ((BOARD_CELL_STYLE_HEIGHT * BOARD_DIM_HEIGHT) + HUD_STYLE_HEIGHT));
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
		initializeTimer();
		this.rootGroup.getChildren().add(this.boardRender.getCanvas());

		// add timer (ApplicationHandler) to update the game
		// add a hud (on-click: duke, level (+ revenue), nb entities, treasure)
		// add actions (hud: add/remove production, on-click x2: send entities from first to second)
		// add pause (space bar)
		// check user interactions
	}

	private void updateGame() {
		this.board.nextTurn();
		this.boardRender.update();
	}
}
