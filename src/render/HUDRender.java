package render;

import exceptions.ExceptionDukeNotPlayer;
import exceptions.ExceptionEmptyProductionQueue;
import game.castle.Castle;
import static utils.Settings.*;

import game.entity.Catapult;
import game.entity.Entity;
import game.entity.Knight;
import game.entity.Pikeman;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;
import utils.Utils;

/**
 * A HUD render.
 */
public class HUDRender extends Render {
	/* VARIABLES **************************************************/

	private Main environment;               /** The environment (application) of render. */

	private HBox hboxCanvas;                /** The base canvas of the HUD render. */

	private Castle currentCastle;           /** Castle whose information are displayed. */

	private Label dukeNameLabel;            /** Label of the duke name. */
	private Label levelLabel;               /** Label of the level. */
	private Label stockLabel;               /** Label of the stock. */
	private Label treasureLabel;            /** Label of the treasure. */
	private Label turnCounter;              /** Label of the turn counter. */

	private boolean enableActions;                              /** Actions are enabled. */
	private Button clearProductionQueueButton;                  /** Button of the "clear production queue" action. */
	private Button launchProductionButton;                      /** Button of the "launch production" action. */
	private Button removeLastProductionButton;                  /** Button of the "remove last production" action. */

	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct a new HUD render.
	 * @param environment The environment (application) of render.
	 */
	public HUDRender(Main environment) {
		this.environment = environment;
		initialize();
	}

	/* METHODS ****************************************************/

	/**
	 * Clean the castle-based labels.
	 */
	private void cleanHUD() {
		this.dukeNameLabel.setText("");
		this.levelLabel.setText("");
		this.stockLabel.setText("");
		this.treasureLabel.setText("");
	}

	/**
	 * Enable the action buttons.
	 */
	private void enableCastleActionsBox() {
		this.launchProductionButton.setDisable(false);
		if (currentCastle.haveProduction()) {
			this.removeLastProductionButton.setDisable(false);
			this.clearProductionQueueButton.setDisable(false);
		} else {
			this.removeLastProductionButton.setDisable(true);
			this.clearProductionQueueButton.setDisable(true);
		}
	}

	/**
	 * Disable the action buttons.
	 */
	private void disableCastleActionsBox() {
		this.launchProductionButton.setDisable(true);
		this.removeLastProductionButton.setDisable(true);
		this.clearProductionQueueButton.setDisable(true);
	}

	/**
	 * Initialize the render.
	 */
	protected void initialize() {
		super.initialize();
		this.canvas.setPrefHeight(HUD_STYLE_HEIGHT);
		this.canvas.setPrefWidth(BOARD_CELL_STYLE_WIDTH * BOARD_DIM_WIDTH);
		this.canvas.setTranslateY(BOARD_CELL_STYLE_HEIGHT * BOARD_DIM_HEIGHT);
		this.hboxCanvas = new HBox();
		this.hboxCanvas.setPadding(HUD_STYLE_PADDING);
		this.hboxCanvas.setSpacing(HUD_STYLE_PADDING.getLeft());
		this.hboxCanvas.setAlignment(Pos.CENTER_LEFT);
		initializeTurnCounterBox();
		initializeCastleInformationBox();
		initializeCastleActionsBox();
		this.canvas.getChildren().add(this.hboxCanvas);
	}

	/**
	 * Initialize the pop-up dialog when launching a production.
	 * @param dialog Pop-up stage to initialize.
	 */
	private void initializePopUpLaunchProductionDialog(Stage dialog) {
		ToggleGroup typeGroup = new ToggleGroup();

		RadioButton pikemanButton = new RadioButton("Piquier (+ " + Utils.interpretNumber(ENTITY_PIKEMAN_PROD_COST, "florin") + ", " + Utils.interpretNumber(ENTITY_PIKEMAN_PROD_TIME, "tour") + ")");
		pikemanButton.setToggleGroup(typeGroup);
		pikemanButton.setDisable(this.currentCastle.getTreasure() < ENTITY_PIKEMAN_PROD_COST);

		RadioButton knightButton = new RadioButton("Chevalier (+ " + Utils.interpretNumber(ENTITY_KNIGHT_PROD_COST, "florin") + ", " + Utils.interpretNumber(ENTITY_KNIGHT_PROD_TIME, "tour") + ")");
		knightButton.setToggleGroup(typeGroup);
		knightButton.setDisable(this.currentCastle.getTreasure() < ENTITY_KNIGHT_PROD_COST);

		RadioButton catapultButton = new RadioButton("Catapulte (+ " + Utils.interpretNumber(ENTITY_CATAPULT_PROD_COST, "florin") + ", " + Utils.interpretNumber(ENTITY_CATAPULT_PROD_TIME, "tour") + ")");
		catapultButton.setToggleGroup(typeGroup);
		catapultButton.setDisable(this.currentCastle.getTreasure() < ENTITY_CATAPULT_PROD_COST);

		RadioButton levelButton = new RadioButton("Niveau supérieur (+ " + Utils.interpretNumber(CASTLE_LEVEL_PROD_COST(this.currentCastle.getLevel()), "florin") + ", " + Utils.interpretNumber(CASTLE_LEVEL_PROD_TIME(this.currentCastle.getLevel()), "tour") + ")");
		levelButton.setToggleGroup(typeGroup);
		levelButton.setDisable(this.currentCastle.getTreasure() < CASTLE_LEVEL_PROD_COST(this.currentCastle.getLevel()));

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
		launchButton.setDisable(pikemanButton.isDisable() && knightButton.isDisable() && catapultButton.isDisable() && levelButton.isDisable());
		launchButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!(catapultButton.isDisable() && knightButton.isDisable() && pikemanButton.isDisable() && levelButton.isDisable())) {
					if (levelButton.isSelected()) {
						try {
							currentCastle.launchLevelProduction();
						} catch (ExceptionDukeNotPlayer e) {
							e.printStackTrace();
						}
					} else {
						Class<? extends Entity> type = null;
						if (catapultButton.isSelected())
							type = Catapult.class;
						else if (knightButton.isSelected())
							type = Knight.class;
						else if (pikemanButton.isSelected())
							type = Pikeman.class;
						try {
							currentCastle.launchEntityProduction(type);
						} catch (ExceptionDukeNotPlayer e) {
							e.printStackTrace();
						}
					}
				}
				dialog.close();
			}
		});
		HBox buttonsCanvas = new HBox(cancelButton, launchButton);
		buttonsCanvas.setSpacing(HUD_STYLE_PADDING.getLeft());


		VBox canvas = new VBox(pikemanButton, knightButton, catapultButton, levelButton, buttonsCanvas);
		canvas.setPadding(HUD_STYLE_PADDING);
		canvas.setSpacing(HUD_STYLE_PADDING.getLeft() / 2);
		Scene scene = new Scene(canvas);
		dialog.setScene(scene);
		dialog.show();
	}

	/**
	 * Initialize the turn counter box.
	 */
	private void initializeTurnCounterBox() {
		this.turnCounter = new Label(String.valueOf(BOARD_FIRST_TURN));
		this.turnCounter.setStyle("-fx-font-size: 2em;");
		this.turnCounter.setMinWidth(100);
		this.hboxCanvas.getChildren().add(this.turnCounter);
	}

	/**
	 * Initialize the castle information box.
	 */
	private void initializeCastleInformationBox() {
		this.dukeNameLabel = new Label();
		this.levelLabel = new Label();
		this.stockLabel = new Label();
		this.treasureLabel = new Label();
		VBox castleInformationsACanvas = new VBox(this.dukeNameLabel, this.levelLabel, this.treasureLabel);
		castleInformationsACanvas.setSpacing(4);
		castleInformationsACanvas.setMinWidth(200);
		VBox castleInformationsBCanvas = new VBox(this.stockLabel);
		castleInformationsBCanvas.setSpacing(4);
		castleInformationsBCanvas.setMinWidth(200);
		this.hboxCanvas.getChildren().addAll(castleInformationsACanvas, castleInformationsBCanvas);
	}

	/**
	 * Initialize the castle actions box.
	 */
	private void initializeCastleActionsBox() {
		this.launchProductionButton = new Button("Production +");
		this.launchProductionButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				final Stage dialog = new Stage();
				dialog.setTitle("Nouvelle production");
				dialog.initModality(Modality.APPLICATION_MODAL);
				dialog.initOwner(environment.getStage());
				initializePopUpLaunchProductionDialog(dialog);
			}
		});

		this.removeLastProductionButton = new Button("Production -");
		this.removeLastProductionButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					currentCastle.removeLastProduction(false);
				} catch (ExceptionEmptyProductionQueue e) {
					e.printStackTrace();
				} catch (ExceptionDukeNotPlayer e) {
					e.printStackTrace();
				}
			}
		});

		this.clearProductionQueueButton = new Button("Production --");
		this.clearProductionQueueButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				currentCastle.clearProductionQueue();
			}
		});

		HBox castleActionCanvas = new HBox(this.launchProductionButton, this.removeLastProductionButton, this.clearProductionQueueButton);
		disableCastleActionsBox();
		this.hboxCanvas.getChildren().add(castleActionCanvas);
	}

	/**
	 * Display the castle information.
	 * @param castle Castle to display.
	 * @param withActions Enable the action buttons.
	 */
	public void showCastleInformation(Castle castle, boolean withActions) {
		if (castle == null) {
			cleanHUD();
			disableCastleActionsBox();
		} else {
			this.enableActions = withActions;
			this.currentCastle = castle;
			updateCastleInformation();
			if (withActions)
				enableCastleActionsBox();
			else
				disableCastleActionsBox();
		}
	}

	/**
	 * Show the end of the game when the main player loses.
	 */
	public void showEndLose() {
		this.turnCounter.setText("Perdu.");
	}

	/**
	 * Show the end of the game when the main player wins.
	 */
	public void showEndWin() {
		this.turnCounter.setText("Gagné !");
	}

	/**
	 * Update the labels.
	 * @param turn Current turn.
	 */
	public void update(int turn) {
		this.turnCounter.setText(HUD_LABEL_TURN(turn));
		updateCastleInformation();
	}

	/**
	 * Update the castle information labels.
	 */
	private void updateCastleInformation() {
		if (this.currentCastle != null) {
			this.dukeNameLabel.setText(HUD_LABEL_CASTLE_DUKE_NAME(this.currentCastle));
			this.levelLabel.setText(HUD_LABEL_CASTLE_LEVEL(this.currentCastle));
			this.stockLabel.setText(HUD_LABEL_CASTLE_STOCK(this.currentCastle));
			this.treasureLabel.setText(HUD_LABEL_CASTLE_TREASURE(this.currentCastle));
			if (this.enableActions)
				enableCastleActionsBox();
		}
	}

	/**
	 * Clear the current castle value and update the labels.
	 */
	public void unselectCastle() {
		this.currentCastle = null;
		showCastleInformation(null, false);
	}
}
