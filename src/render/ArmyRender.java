package render;

import game.entity.Entity;
import game.entity.group.Army;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A render of an army.
 */
public class ArmyRender extends Render {
	/* VARIABLES **************************************************/

	private Army army;                              /** The army to render. */

	private ArrayList<EntityRender> entityRenders;  /** List of the renders of entities of the army. */

	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct a render for an army.
	 * @param army The army to render.
	 */
	public ArmyRender(Army army) {
		this.army = army;
		this.entityRenders = new ArrayList<>();
		initialize();
	}

	/* METHODS ****************************************************/

	/**
	 * Add an entity to render if it's not already.
	 * @param entity Entity to render.
	 */
	private void addEntityToRender(Entity entity) {
		if (getEntityRenderFromEntity(entity) == null) {
			EntityRender entityRender = new EntityRender(entity);
			this.entityRenders.add(entityRender);
			this.canvas.getChildren().add(entityRender.getCanvas());
		}
	}

	/**
	 * Check each entities if it's dead: if so, it's not rendered anymore.
	 */
	private void checkEntitiesState() {
		ArrayList<Entity> entitiesToRemove = new ArrayList<>();
		for (EntityRender entityRender: this.entityRenders) {
			if (entityRender.getEntity().isDead())
				entitiesToRemove.add(entityRender.getEntity());
		}
		entitiesToRemove.stream().forEach(e -> removeEntityToRender(e));
	}

	/**
	 * Get the render of an entity using itself.
	 * @param entity Entity of the render to find.
	 * @return Render of the entity.
	 */
	private EntityRender getEntityRenderFromEntity(Entity entity) {
		for (EntityRender entityRender: this.entityRenders) {
			if (entityRender.getEntity() == entity)
				return entityRender;
		}
		return null;
	}

	/**
	 * Initialize the render.
	 */
	protected void initialize() {
		super.initialize();
		initializeEntities();
	}

	/**
	 * Initialize the render of the entities.
	 */
	private void initializeEntities() {
		for (Entity entity: army.getListEntities())
			addEntityToRender(entity);
	}

	/**
	 * Remove an entity to render.
	 * @param entity Entity to remove.
	 */
	private void removeEntityToRender(Entity entity) {
		EntityRender entityRender = getEntityRenderFromEntity(entity);
		if (entityRender != null) {
			this.canvas.getChildren().remove(entityRender.getCanvas());
			this.entityRenders.remove(entityRender);
		}
	}

	/**
	 * Update render.
	 * Called at each new turn.
	 */
	public void update() {
		for (EntityRender entityRender: this.entityRenders)
			entityRender.update();
		checkEntitiesState();
	}

	/* GETTER/SETTER **********************************************/

	/**
	 * Getter on army.
	 * @return The army to render.
	 */
	public Army getArmy() {
		return this.army;
	}
}
