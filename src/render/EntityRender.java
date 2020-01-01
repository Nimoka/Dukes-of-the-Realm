package render;

import game.entity.*;

import static utils.Settings.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utils.Position;

public class EntityRender extends Render {
	/*** VARIABLES ************************************************/

	private Entity entity;
	private Rectangle entityShape;

	/*** CONSTRUCTORS *********************************************/

	public EntityRender(Entity entity) {
		this.entity = entity;
		initialize();
	}

	/*** METHODS **************************************************/

	protected void initialize() {
		super.initialize();
		if (Catapult.class.equals(this.entity.getClass())) {
			initializeShapeAsCatapult();
		} else if (Knight.class.equals(this.entity.getClass())) {
			initializeShapeAsKnight();
		} else if (Pikeman.class.equals(this.entity.getClass())) {
			initializeShapeAsPikeman();
		}
		update();
	}

	private void initializeShapeAsCatapult() {
		this.entityShape = new Rectangle();
		this.entityShape.setWidth(BOARD_CELL_STYLE_WIDTH * .3);
		this.entityShape.setHeight(BOARD_CELL_STYLE_HEIGHT * .12);
		this.entityShape.setFill(Color.BROWN);
		this.canvas.getChildren().add(this.entityShape);
	}

	private void initializeShapeAsKnight() {
		this.entityShape = new Rectangle();
		this.entityShape.setWidth(BOARD_CELL_STYLE_WIDTH * .2);
		this.entityShape.setHeight(BOARD_CELL_STYLE_HEIGHT * .1);
		this.entityShape.setFill(Color.BLACK);
		this.canvas.getChildren().add(this.entityShape);
	}

	private void initializeShapeAsPikeman() {
		this.entityShape = new Rectangle();
		this.entityShape.setWidth(BOARD_CELL_STYLE_WIDTH * .05);
		this.entityShape.setHeight(BOARD_CELL_STYLE_HEIGHT * .14);
		this.entityShape.setFill(Color.BLUE);
		this.canvas.getChildren().add(this.entityShape);
	}

	public void update() {
		Position displayPosition = this.entity.getPosition().convertBoardToDisplay();
		displayPosition.translate(Position.random(BOARD_CELL_STYLE_WIDTH, BOARD_CELL_STYLE_HEIGHT));
		this.entityShape.setX(displayPosition.getX());
		this.entityShape.setY(displayPosition.getY());
	}

	/*** GETTER/SETTER ********************************************/

	public Entity getEntity() {
		return this.entity;
	}
}
