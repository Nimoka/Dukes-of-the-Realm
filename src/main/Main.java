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

/**
 * Main class of the application.
 */
public class Main extends Application {
	/* VARIABLES **************************************************/

	private Group rootGroup;                            /** Root group of the scene. */
	private Scene scene;                                /** Scene of the stage. */
	private AnimationTimer timer;                       /** Timer of the game. */

	private Board board;                                /** Board of the game. */
	private BoardRender boardRender;                    /** Render of the game's board. */
	private HUDRender hudRender;                        /** Render of the HUD. */
	private Player mainPlayer;                          /** Main player of the game (that controls the UI). */
	private ArrayList<Player> players;                  /** List of the players. */
	private CastleRender selectedCastleRender;          /** Render of the selected castle. */
	private boolean selectedCastleIsOwnedByMainPlayer;  /** Castle is owned by the main player. */

	/* METHODS ****************************************************/

	/**
	 * Create the board and its render.
	 */
	private void createBoard() {
		this.board = new Board(this.players);
		this.boardRender = new BoardRender(this.board, this);
	}

	/**
	 * Create the HUD.
	 */
	private void initializeHUD() {
		this.hudRender = new HUDRender();
	}

	/**
	 * Create the players.
	 */
	private void initializePlayers() {
		this.players = new ArrayList<>();
		this.mainPlayer = new UserPlayer();
		this.players.add(this.mainPlayer);
		for (int i = 0; i < BOARD_NB_DUKES_PLAYERS; i++)
			this.players.add(new ComputerPlayer());
		for (int i = 0; i < BOARD_NB_DUKES_BARONS; i++)
			this.players.add(new Baron());
	}

	/**
	 * Create the timer, and its handler (called at each new turn).
	 */
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

	/**
	 * Create the scene.
	 */
	private void initializeScene() {
		this.rootGroup = new Group();
		this.scene = new Scene(rootGroup, (BOARD_CELL_STYLE_WIDTH * BOARD_DIM_WIDTH), ((BOARD_CELL_STYLE_HEIGHT * BOARD_DIM_HEIGHT) + HUD_STYLE_HEIGHT));
	}

	/**
	 * Initialize the stage using settings.
	 * @param stage Stage to initialize.
	 * @param useDefault Use default values or customized ones.
	 */
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

	/**
	 * Select a castle on the render.
	 * @param castleRender Render of the castle selected.
	 */
	public void selectCastle(CastleRender castleRender) {
		if (this.selectedCastleRender == castleRender) {
			castleRender.unselectCastle();
			this.selectedCastleRender = null;
		} else {
			if (this.selectedCastleRender != null)
				this.selectedCastleRender.unselectCastle();
			this.selectedCastleRender = castleRender;
			this.selectedCastleIsOwnedByMainPlayer = (this.selectedCastleRender.getCastle().getDuke() == this.mainPlayer.getDuke());
			this.hudRender.showCastleInformation(castleRender.getCastle(), selectedCastleIsOwnedByMainPlayer);
		}
	}

	/**
	 * Start the application and loop.
	 * @param stage Stage of the application.
	 * @throws Exception Thrown by other methods.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		initializePlayers();
		createBoard();
		initializeHUD();
		initializeStage(stage, false);
		initializeTimer();
		this.rootGroup.getChildren().add(this.boardRender.getCanvas());
		this.rootGroup.getChildren().add(this.hudRender.getCanvas());

		// add actions (hud: add/remove production, on-click x2: send entities from first to second)
		// add pause (space bar)
		// check user interactions
	}

	/**
	 * Update the board and its render at each new turn.
	 * Called at each new turn.
	 */
	private void updateGame() {
		this.board.checkMatchState();
		if (this.board.getMatchState() == false) {
			// to continue
		} else {
			this.board.nextTurn();
			this.boardRender.update();
			this.hudRender.update(this.board.getCurrentTurn());
			this.players.stream().forEach(p -> p.nextTurn());
		}
	}
}
