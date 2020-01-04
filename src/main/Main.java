package main;

import game.Board;
import player.Baron;
import player.ComputerPlayer;
import player.Player;
import player.UserPlayer;
import render.BoardRender;
import render.CastleRender;
import render.HUDRender;
import static utils.Settings.*;

import java.util.ArrayList;
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
	private HUDRender hudRender;
	private Player mainPlayer;
	private ArrayList<Player> players;
	private CastleRender selectedCastleRender;
	private boolean selectedCastleIsOwnedByMainPlayer;

	/*** METHODS **************************************************/

	private void createBoard() {
		this.board = new Board(this.players);
		this.boardRender = new BoardRender(this.board, this);
	}

	private void initializeHUD() {
		this.hudRender = new HUDRender();
	}

	private void initializePlayers() {
		this.players = new ArrayList<>();
		this.mainPlayer = new UserPlayer();
		this.players.add(this.mainPlayer);
		for (int i = 0; i < BOARD_NB_DUKES_PLAYERS; i++)
			this.players.add(new ComputerPlayer());
		for (int i = 0; i < BOARD_NB_DUKES_BARONS; i++)
			this.players.add(new Baron());
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

	public void selectCastle(CastleRender castleRender) {
		if (this.selectedCastleRender == castleRender) {
			castleRender.unselectCastle();
			this.selectedCastleRender = null;
		} else {
			if (this.selectedCastleRender != null)
				this.selectedCastleRender.unselectCastle();
			this.selectedCastleRender = castleRender;
			this.selectedCastleIsOwnedByMainPlayer = (this.selectedCastleRender.getCastle().getDuke() == this.mainPlayer.getDuke());
			this.hudRender.showCastleInformations(castleRender.getCastle(), selectedCastleIsOwnedByMainPlayer);
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		initializePlayers();
		createBoard();
		initializeHUD();
		initializeStage(stage, false);
		initializeTimer();
		this.rootGroup.getChildren().add(this.boardRender.getCanvas());
		this.rootGroup.getChildren().add(this.hudRender.getCanvas());

		// add a hud (on-click: duke, level (+ revenue), nb entities, treasure)
		// add actions (hud: add/remove production, on-click x2: send entities from first to second)
		// add pause (space bar)
		// check user interactions
	}

	private void updateGame() {
		this.board.nextTurn();
		this.boardRender.update();
		this.hudRender.update(this.board.getCurrentTurn());
		this.players.stream().forEach(p -> p.nextTurn());
	}
}
