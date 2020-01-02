package render;

import game.castle.Castle;
import javafx.scene.layout.HBox;

import static utils.Settings.*;

public class HUDRender extends Render {
	/*** VARIABLES ************************************************/

	private HBox hboxCanvas;

	/*** CONSTRUCTORS *********************************************/

	public HUDRender() {
		initialize();
	}

	/*** METHODS **************************************************/

	protected void initialize() {
		super.initialize();
		this.canvas.setPrefHeight(HUD_STYLE_HEIGHT);
		this.canvas.setPrefWidth(BOARD_CELL_STYLE_WIDTH * BOARD_DIM_WIDTH);
		this.canvas.setTranslateX(BOARD_CELL_STYLE_HEIGHT * BOARD_DIM_HEIGHT);
		this.hboxCanvas = new HBox();
		this.hboxCanvas.setPadding(HUD_STYLE_PADDING);
	}

	public void showCastleInformations(Castle castle) {
	}

	public void update() {

	}
}
