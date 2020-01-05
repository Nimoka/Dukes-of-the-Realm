package render;

import game.entity.*;
import static utils.Settings.*;

import javafx.scene.shape.Rectangle;
import utils.Position;

/**
 * A render of an entity.
 */
public class EntityRender extends Render {
	/* VARIABLES **************************************************/

	private Entity entity;                  /** The entity to render. */
	private Rectangle entityShape;          /** Shape of the entity. */

	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct a new render of the entity.
	 * @param entity The entity to render.
	 */
	public EntityRender(Entity entity) {
		this.entity = entity;
		initialize();
	}

	/* METHODS ****************************************************/

	/**
	 * Initialize the render.
	 */
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

	/**
	 * Initialize the shape as a catapult.
	 */
	private void initializeShapeAsCatapult() {
		this.entityShape = new Rectangle();
		this.entityShape.setFill(ENTITY_CATAPULT_STYLE_COLOR);
		this.entityShape.setHeight(ENTITY_CATAPULT_STYLE_HEIGHT);
		this.entityShape.setWidth(ENTITY_CATAPULT_STYLE_WIDTH);
		this.canvas.getChildren().add(this.entityShape);
	}

	/**
	 * Initialize the shape as a knight.
	 */
	private void initializeShapeAsKnight() {
		this.entityShape = new Rectangle();
		this.entityShape.setFill(ENTITY_KNIGHT_STYLE_COLOR);
		this.entityShape.setHeight(ENTITY_KNIGHT_STYLE_HEIGHT);
		this.entityShape.setWidth(ENTITY_KNIGHT_STYLE_WIDTH);
		this.canvas.getChildren().add(this.entityShape);
	}

	/**
	 * Initialize the shape as a pikeman.
	 */
	private void initializeShapeAsPikeman() {
		this.entityShape = new Rectangle();
		this.entityShape.setFill(ENTITY_PIKEMAN_STYLE_COLOR);
		this.entityShape.setHeight(ENTITY_PIKEMAN_STYLE_HEIGHT);
		this.entityShape.setWidth(ENTITY_PIKEMAN_STYLE_WIDTH);
		this.canvas.getChildren().add(this.entityShape);
	}

	/**
	 * Update the position of the shape.
	 * Called at each new turn.
	 */
	public void update() {
		if (this.entity.getCurrentState() == EntityState.MOVE) {
			Position displayPosition = this.entity.getPosition().convertBoardToDisplay();
			displayPosition.translate(Position.random(BOARD_CELL_STYLE_WIDTH, BOARD_CELL_STYLE_HEIGHT));
			this.entityShape.setX(displayPosition.getX());
			this.entityShape.setY(displayPosition.getY());
		}
	}

	/* GETTER/SETTER **********************************************/

	/**
	 * Getter on entity.
	 * @return The entity to render.
	 */
	public Entity getEntity() {
		return this.entity;
	}
}
