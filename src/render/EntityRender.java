package render;

import game.entity.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utils.Position;

import static utils.Settings.*;
import static utils.Settings.ENTITY_KNIGHT_STYLE_WIDTH;

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
		this.entityShape.setFill(ENTITY_CATAPULT_STYLE_COLOR);
		this.entityShape.setHeight(ENTITY_CATAPULT_STYLE_HEIGHT);
		this.entityShape.setWidth(ENTITY_CATAPULT_STYLE_WIDTH);
		this.canvas.getChildren().add(this.entityShape);
	}

	private void initializeShapeAsKnight() {
		this.entityShape = new Rectangle();
		this.entityShape.setFill(ENTITY_KNIGHT_STYLE_COLOR);
		this.entityShape.setHeight(ENTITY_KNIGHT_STYLE_HEIGHT);
		this.entityShape.setWidth(ENTITY_KNIGHT_STYLE_WIDTH);
		this.canvas.getChildren().add(this.entityShape);
	}

	private void initializeShapeAsPikeman() {
		this.entityShape = new Rectangle();
		this.entityShape.setFill(ENTITY_PIKEMAN_STYLE_COLOR);
		this.entityShape.setHeight(ENTITY_PIKEMAN_STYLE_HEIGHT);
		this.entityShape.setWidth(ENTITY_PIKEMAN_STYLE_WIDTH);
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
