package render;

import game.castle.Castle;
import static utils.Settings.*;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class HUDRender extends Render {
	/*** VARIABLES ************************************************/

	private HBox hboxCanvas;
	private Label turnCounter;

	/*** CONSTRUCTORS *********************************************/

	public HUDRender() {
		initialize();
	}

	/*** METHODS **************************************************/

	protected void initialize() {
		super.initialize();
		this.canvas.setPrefHeight(HUD_STYLE_HEIGHT);
		this.canvas.setPrefWidth(BOARD_CELL_STYLE_WIDTH * BOARD_DIM_WIDTH);
		this.canvas.setTranslateY(BOARD_CELL_STYLE_HEIGHT * BOARD_DIM_HEIGHT);
		this.turnCounter = new Label(String.valueOf(BOARD_FIRST_TURN));
		this.hboxCanvas = new HBox(this.turnCounter);
		this.hboxCanvas.setPadding(HUD_STYLE_PADDING);
		this.canvas.getChildren().add(this.hboxCanvas);
	}

	public void showCastleInformations(Castle castle, boolean withActions) {
	}

	public void update(int turn) {
		this.turnCounter.setText(String.valueOf(turn));
	}
}
