package render;

import game.castle.Castle;
import static utils.Settings.*;

import javafx.scene.paint.Color;
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
		this.castleShape.setX(DISPLAY_CELL_WIDTH * .1);
		this.castleShape.setY(DISPLAY_CELL_HEIGHT * .1);
		this.castleShape.setHeight(DISPLAY_CELL_HEIGHT * .8);
		this.castleShape.setWidth(DISPLAY_CELL_WIDTH * .8);
		this.castleShape.setFill(Color.LIGHTGREY);
		this.castleShape.setStroke(Color.DARKGREY);
		this.castleShape.setStrokeWidth(4);
		this.canvas.getChildren().add(this.castleShape);
	}

	private void initializeCastleDoorShape() {
		this.castleDoorShape = new Rectangle();
		this.castleDoorShape.setX(DISPLAY_CELL_WIDTH * .3);
		this.castleDoorShape.setY(this.castleShape.getX() - this.castleShape.getStrokeWidth());
		this.castleDoorShape.setHeight(this.castleShape.getStrokeWidth() * 2);
		this.castleDoorShape.setWidth(DISPLAY_CELL_WIDTH * .4);
		this.castleDoorShape.setFill(Color.DARKGREY);
		this.canvas.getChildren().add(this.castleDoorShape);
	}

	public void update() {
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
