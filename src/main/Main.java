package main;

import exceptions.ExceptionActionAlreadyLaunched;
import exceptions.ExceptionDukeNotPlayer;
import game.Board;
import game.entity.Catapult;
import game.entity.Entity;
import game.entity.Knight;
import game.entity.Pikeman;
import game.entity.group.Army;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
import javafx.scene.control.Spinner;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Main class of the application.
 */
public class Main extends Application {
	/* VARIABLES **************************************************/

	private Group rootGroup;                            /** Root group of the scene. */
	private Scene scene;                                /** Scene of the stage. */
	private Stage stage;                                /** Primary stage. */
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
		this.board = new Board(this.players, this);
		this.boardRender = new BoardRender(this.board, this);
	}

	/**
	 * Create the HUD.
	 */
	private void initializeHUD() {
		this.hudRender = new HUDRender(this);
	}

	/**
	 * Initialize the pop-up dialog when launching an action.
	 * @param dialog Pop-up stage to initialize.
	 */
	private void initializePopUpLaunchActionDialog(Stage dialog, CastleRender castleRender) {
		Spinner catapultSpinner = new Spinner(0, selectedCastleRender.getCastle().getStock().getNbCatapults(), 0, 1);
		Label catapultLabel = new Label("Catapulte");
		HBox catapultCanvas = new HBox(catapultLabel, catapultSpinner);
		catapultCanvas.setSpacing(HUD_STYLE_PADDING.getLeft());

		Spinner knightSpinner = new Spinner(0, selectedCastleRender.getCastle().getStock().getNbKnights(), 0, 1);
		Label knightLabel = new Label("Chevalier");
		HBox knightCanvas = new HBox(knightLabel, knightSpinner);
		knightCanvas.setSpacing(HUD_STYLE_PADDING.getLeft());

		Spinner pikemanSpinner = new Spinner(0, selectedCastleRender.getCastle().getStock().getNbPikemen(), 0, 1);
		Label pikemanLabel = new Label("Piquier");
		HBox pikemanCanvas = new HBox(pikemanLabel, pikemanSpinner);
		pikemanCanvas.setSpacing(HUD_STYLE_PADDING.getLeft());

		Button cancelButton = new Button("Annuler");
		cancelButton.setCancelButton(true);
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dialog.close();
			}
		});
		Button launchButton = new Button("Lancer");
		launchButton.setDefaultButton(true);
		launchButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int nbCatapults = (Integer) catapultSpinner.getValue();
				int nbKnights = (Integer) knightSpinner.getValue();
				int nbPikemen = (Integer) pikemanSpinner.getValue();
				if ((nbCatapults != 0) || (nbKnights != 0) || (nbPikemen != 0)) {
					try {
						selectedCastleRender.getCastle().launchNewAction(castleRender.getCastle(), nbCatapults, nbKnights, nbPikemen);
					} catch (ExceptionDukeNotPlayer e) {
						e.printStackTrace();
					} catch (ExceptionActionAlreadyLaunched e) {
						e.printStackTrace();
					}
				}
				dialog.close();
			}
		});
		HBox buttonsCanvas = new HBox(cancelButton, launchButton);
		buttonsCanvas.setSpacing(HUD_STYLE_PADDING.getLeft());

		VBox canvas = new VBox(catapultCanvas, knightCanvas, pikemanCanvas, buttonsCanvas);
		canvas.setPadding(HUD_STYLE_PADDING);
		canvas.setSpacing(HUD_STYLE_PADDING.getLeft());
		Scene scene = new Scene(canvas);
		dialog.setScene(scene);
		dialog.show();
	}

	/**
	 * Create the players.
	 */
	private void initializePlayers() {
		this.players = new ArrayList<>();
		this.mainPlayer = new UserPlayer();
		this.players.add(this.mainPlayer);
		for (int i = 0; i < BOARD_NB_DUKES_PLAYERS; i++)
			this.players.add(new ComputerPlayer(this));
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

	public void launchArmyRender(Army army) {
		this.boardRender.addArmyToRender(army);
	}

	/**
	 * Select a castle on the render.
	 * @param castleRender Render of the castle selected.
	 * @return Show as selected (true = show).
	 */
	public boolean selectCastle(CastleRender castleRender) {
		if (this.selectedCastleRender == castleRender) {
			castleRender.unselectCastle();
			this.selectedCastleRender = null;
			hudRender.unselectCastle();
		} else if (this.selectedCastleRender == null) {
			this.selectedCastleRender = castleRender;
			this.selectedCastleIsOwnedByMainPlayer = (this.selectedCastleRender.getCastle().getDuke() == this.mainPlayer.getDuke());
			this.hudRender.showCastleInformation(castleRender.getCastle(), selectedCastleIsOwnedByMainPlayer);
			return true;
		} else {
			if (this.selectedCastleRender.getCastle().getDuke() == this.mainPlayer.getDuke()) {
				if (!this.selectedCastleRender.getCastle().haveAction()) {
					Stage dialog = new Stage();
					dialog.setTitle("Nouvelle action");
					dialog.initModality(Modality.APPLICATION_MODAL);
					dialog.initOwner(this.stage);
					initializePopUpLaunchActionDialog(dialog, castleRender);
				}
			}
		}
		return false;
	}

	/**
	 * Start the application and loop.
	 * @param stage Stage of the application.
	 * @throws Exception Thrown by other methods.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
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

	/* GETTER/SETTER **********************************************/

	/**
	 * Getter on board.
	 * @return Board of the game.
	 */
	public Board getBoard() {
		return this.board;
	}

	/**
	 * Getter on mainPlayer.
	 * @return Main player of the game (that controls the UI).
	 */
	public Player getMainPlayer() {
		return this.mainPlayer;
	}

	/**
	 * Getter on stage.
	 * @return Primary stage.
	 */
	public Stage getStage() {
		return this.stage;
	}
}
