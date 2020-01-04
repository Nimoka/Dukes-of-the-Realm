package render;

import game.castle.Castle;
import static utils.Settings.*;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HUDRender extends Render {
	/*** VARIABLES ************************************************/

	private HBox hboxCanvas;

	private Castle currentCastle;

	private Label dukeNameLabel;
	private Label levelLabel;
	private Label stockLabel;
	private Label treasureLabel;

	private Label turnCounter;

	/*** CONSTRUCTORS *********************************************/

	public HUDRender() {
		initialize();
	}

	/*** METHODS **************************************************/

	private void cleanHUD() {
		this.dukeNameLabel.setText("");
		this.levelLabel.setText("");
		this.stockLabel.setText("");
		this.treasureLabel.setText("");
	}

	protected void initialize() {
		super.initialize();
		this.canvas.setPrefHeight(HUD_STYLE_HEIGHT);
		this.canvas.setPrefWidth(BOARD_CELL_STYLE_WIDTH * BOARD_DIM_WIDTH);
		this.canvas.setTranslateY(BOARD_CELL_STYLE_HEIGHT * BOARD_DIM_HEIGHT);
		this.hboxCanvas = new HBox();
		this.hboxCanvas.setPadding(HUD_STYLE_PADDING);
		initializeCastleInformationsBox();
		initializeCastleActionsBox();
		initializeTurnCounterBox();
		this.canvas.getChildren().add(this.hboxCanvas);
	}

	private void initializeTurnCounterBox() {
		this.turnCounter = new Label(String.valueOf(BOARD_FIRST_TURN));
		this.hboxCanvas.getChildren().add(this.turnCounter);
	}

	private void initializeCastleInformationsBox() {
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

	private void initializeCastleActionsBox() {
	}

	public void showCastleInformations(Castle castle, boolean withActions) {
		if (castle == null) {
			cleanHUD();
		} else {
			this.currentCastle = castle;
			updateCastleInformations();
		}
	}

	public void update(int turn) {
		this.turnCounter.setText(HUD_LABEL_TURN(turn));
		updateCastleInformations();
	}

	private void updateCastleInformations() {
		if (this.currentCastle != null) {
			this.dukeNameLabel.setText(HUD_LABEL_CASTLE_DUKE_NAME(this.currentCastle));
			this.levelLabel.setText(HUD_LABEL_CASTLE_LEVEL(this.currentCastle));
			this.stockLabel.setText(HUD_LABEL_CASTLE_STOCK(this.currentCastle));
			this.treasureLabel.setText(HUD_LABEL_CASTLE_TREASURE(this.currentCastle));
		}
	}
}
