package render;

import game.castle.Castle;
import static utils.Settings.*;

import javafx.scene.shape.Rectangle;

public class CastleRender extends Render {
	/*** VARIABLES ************************************************/

	private Castle castle;

	private Rectangle castleShape;
	private Rectangle castleDoorShape;

	/*** CONSTRUCTORS *********************************************/

	public CastleRender(Castle castle) {
		this.castle = castle;
		initialize();
	}

	/*** METHODS **************************************************/

	protected void initialize() {
		super.initialize();
		initializeCastleShape();
		initializeCastleDoorShape();
		update();
	}

	private void initializeCastleShape() {
		this.castleShape = new Rectangle();
		this.castleShape.setX(BOARD_CELL_STYLE_WIDTH * .1);
		this.castleShape.setY(BOARD_CELL_STYLE_HEIGHT * .1);
		this.castleShape.setHeight(BOARD_CELL_STYLE_HEIGHT * .8);
		this.castleShape.setWidth(BOARD_CELL_STYLE_WIDTH * .8);
		this.castleShape.setFill(CASTLE_STYLE_FILL_COLOR);
		this.castleShape.setStroke(CASTLE_STYLE_STROKE_COLOR);
		this.castleShape.setStrokeWidth(CASTLE_STYLE_STROKE_WIDTH);
		this.canvas.getChildren().add(this.castleShape);
	}

	private void initializeCastleDoorShape() {
		this.castleDoorShape = new Rectangle();
		this.castleDoorShape.setX(BOARD_CELL_STYLE_WIDTH * .3);
		this.castleDoorShape.setY(this.castleShape.getX() - this.castleShape.getStrokeWidth());
		this.castleDoorShape.setHeight(CASTLE_STYLE_STROKE_WIDTH * 2);
		this.castleDoorShape.setWidth(BOARD_CELL_STYLE_WIDTH * .4);
		this.castleDoorShape.setFill(CASTLE_STYLE_STROKE_COLOR);
		this.canvas.getChildren().add(this.castleDoorShape);
	}

	public void update() {
		this.canvas.setTranslateX(this.castle.getPosition().convertBoardToDisplay().getX());
		this.canvas.setTranslateY(this.castle.getPosition().convertBoardToDisplay().getY());
		switch (this.castle.getDirection()) {
			case EAST:
				this.canvas.setRotate(90);
				break;
			case SOUTH:
				this.canvas.setRotate(180);
				break;
			case WEST:
				this.canvas.setRotate(270);
				break;
		}
	}

	/*** GETTER/SETTER ********************************************/

	public Castle getCastle() {
		return this.castle;
	}
}
