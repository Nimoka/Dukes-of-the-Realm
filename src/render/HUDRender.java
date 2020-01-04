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
		VBox castleInformationsBCanvas = new VBox(this.stockLabel);
		castleInformationsBCanvas.setSpacing(4);
		this.hboxCanvas.getChildren().addAll(castleInformationsACanvas, castleInformationsBCanvas);
	}

	private void initializeCastleActionsBox() {
	}

	public void showCastleInformations(Castle castle, boolean withActions) {
		if (castle == null) {
			cleanHUD();
		} else {
			this.currentCastle = castle;
			this.dukeNameLabel.setText(HUD_LABEL_DUKE_NAME(castle));
			this.levelLabel.setText(HUD_LABEL_LEVEL(castle));
			this.stockLabel.setText(HUD_LABEL_STOCK(castle));
			this.treasureLabel.setText(HUD_LABEL_TREASURE(castle));
		}
	}

	public void update(int turn) {
		this.turnCounter.setText(String.valueOf(turn));
		showCastleInformations(this.currentCastle, false);
	}
}
