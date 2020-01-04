package render;

import game.castle.Castle;
import main.Main;
import static utils.Settings.*;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class CastleRender extends Render {
	/*** VARIABLES ************************************************/

	private Main environment;
	private Castle castle;

	private Rectangle castleShape;
	private Rectangle castleDoorShape;

	/*** CONSTRUCTORS *********************************************/

	public CastleRender(Castle castle, Main environment) {
		this.castle = castle;
		this.environment = environment;
		initialize();
	}

	/*** METHODS **************************************************/

	protected void initialize() {
		super.initialize();
		initializeCanvas();
		initializeCastleShape();
		initializeCastleDoorShape();
		initializeCastlePosition();
		update();
	}

	private void initializeCanvas() {
		this.canvas.setPrefWidth(BOARD_CELL_STYLE_WIDTH);
		this.canvas.setPrefHeight(BOARD_CELL_STYLE_HEIGHT);
		this.canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				selectCastle();
			}
		});
	}

	private void initializeCastleShape() {
		this.castleShape = new Rectangle();
		this.castleShape.setX((BOARD_CELL_STYLE_WIDTH - CASTLE_STYLE_CASTLE_HEIGHT) / 2);
		this.castleShape.setY((BOARD_CELL_STYLE_HEIGHT - CASTLE_STYLE_CASTLE_WIDTH) / 2);
		this.castleShape.setHeight(CASTLE_STYLE_CASTLE_HEIGHT);
		this.castleShape.setWidth(CASTLE_STYLE_CASTLE_WIDTH);
		this.castleShape.setFill(CASTLE_STYLE_FILL_COLOR);
		this.castleShape.setStroke(CASTLE_STYLE_STROKE_COLOR);
		this.castleShape.setStrokeWidth(CASTLE_STYLE_STROKE_WIDTH);
		this.canvas.getChildren().add(this.castleShape);
	}

	private void initializeCastleDoorShape() {
		this.castleDoorShape = new Rectangle();
		this.castleDoorShape.setX((BOARD_CELL_STYLE_WIDTH - CASTLE_STYLE_DOOR_WIDTH) / 2);
		this.castleDoorShape.setY((BOARD_CELL_STYLE_WIDTH - CASTLE_STYLE_CASTLE_HEIGHT) / 2 - CASTLE_STYLE_STROKE_WIDTH);
		this.castleDoorShape.setHeight(CASTLE_STYLE_DOOR_HEIGHT);
		this.castleDoorShape.setWidth(CASTLE_STYLE_DOOR_WIDTH);
		this.castleDoorShape.setFill(CASTLE_STYLE_STROKE_COLOR);
		this.canvas.getChildren().add(this.castleDoorShape);
	}

	public void initializeCastlePosition() {
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

	public void selectCastle() {
		showSelected();
		this.environment.selectCastle(this);
	}

	private void showNotSelected() {
		this.castleShape.setFill(CASTLE_STYLE_FILL_COLOR);
	}

	private void showSelected() {
		this.castleShape.setFill(CASTLE_STYLE_SELECTED_FILL_COLOR);
	}

	public void unselectCastle() {
		showNotSelected();
	}

	public void update() {
	}

	/*** GETTER/SETTER ********************************************/

	public Castle getCastle() {
		return this.castle;
	}
}
