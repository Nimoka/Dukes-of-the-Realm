package render;

import game.castle.Castle;
import static utils.Settings.*;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * A HUD render.
 */
public class HUDRender extends Render {
	/* VARIABLES **************************************************/

	private HBox hboxCanvas;                /** The base canvas of the HUD render. */

	private Castle currentCastle;           /** Castle whose information are displayed. */

	private Label dukeNameLabel;            /** Label of the duke name. */
	private Label levelLabel;               /** Label of the level. */
	private Label stockLabel;               /** Label of the stock. */
	private Label treasureLabel;            /** Label of the treasure. */
	private Label turnCounter;              /** Label of the turn counter. */

	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct a new HUD render.
	 */
	public HUDRender() {
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

	}

	/**
	 * Disable the action buttons.
	 */
	private void disableCastleActionsBox() {

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
		initializeCastleInformationBox();
		initializeCastleActionsBox();
		initializeTurnCounterBox();
		this.canvas.getChildren().add(this.hboxCanvas);
	}

	/**
	 * Initialize the turn counter box.
	 */
	private void initializeTurnCounterBox() {
		this.turnCounter = new Label(String.valueOf(BOARD_FIRST_TURN));
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
		castleInformationsACanvas.setPrefWidth(200);
		VBox castleInformationsBCanvas = new VBox(this.stockLabel);
		castleInformationsBCanvas.setSpacing(4);
		castleInformationsBCanvas.setPrefWidth(100);
		this.hboxCanvas.getChildren().addAll(castleInformationsACanvas, castleInformationsBCanvas);
	}

	/**
	 * Initialize the castle actions box.
	 */
	private void initializeCastleActionsBox() {
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
			this.currentCastle = castle;
			updateCastleInformation();
			if (withActions)
				enableCastleActionsBox();
			else
				disableCastleActionsBox();
		}
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
		}
	}
}
