package render;

import game.entity.Entity;
import game.entity.group.Army;

import java.util.ArrayList;
import java.util.Iterator;

public class ArmyRender extends Render {
	/*** VARIABLES ************************************************/

	private Army army;
	private ArrayList<Entity> entities;
	private ArrayList<EntityRender> entitiesRender;

	/*** CONSTRUCTORS *********************************************/

	public ArmyRender(Army army) {
		this.army = army;
		initialize();
	}

	/*** METHODS **************************************************/

	private void addEntityToRender(Entity entity) {
		if (getEntityRenderFromEntity(entity) == null) {
			EntityRender entityRender = new EntityRender(entity);
			this.entities.add(entity);
			this.entitiesRender.add(entityRender);
			this.canvas.getChildren().add(entityRender.getCanvas());
		}
	}

	private void checkEntitiesState() {
		Iterator iterator = this.entities.iterator();
		Entity entity = null;
		while (iterator.hasNext()) {
			entity = (Entity) iterator.next();
			if (entity.isDead())
				removeEntityToRender(entity);
		}
	}

	protected void initialize() {
		super.initialize();
		initializeEntities();
	}

	private void initializeEntities() {
		for (Entity entity: army.getListEntities())
			addEntityToRender(entity);
	}

	private EntityRender getEntityRenderFromEntity(Entity entity) {
		for (EntityRender entityRender: this.entitiesRender) {
			if (entityRender.getEntity() == entity)
				return entityRender;
		}
		return null;
	}

	private void removeEntityToRender(Entity entity) {
		EntityRender entityRender = getEntityRenderFromEntity(entity);
		if (entityRender != null) {
			this.entitiesRender.remove(entityRender);
			this.entities.remove(entity);
		}
	}

	public void update() {
		checkEntitiesState();
	}
}
