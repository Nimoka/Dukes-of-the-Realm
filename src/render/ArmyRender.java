package render;

import game.entity.Entity;
import game.entity.group.Army;

import java.util.ArrayList;
import java.util.Iterator;

public class ArmyRender extends Render {
	/*** VARIABLES ************************************************/

	private Army army;

	private ArrayList<EntityRender> entityRenders;

	/*** CONSTRUCTORS *********************************************/

	public ArmyRender(Army army) {
		this.army = army;
		initialize();
	}

	/*** METHODS **************************************************/

	private void addEntityToRender(Entity entity) {
		if (getEntityRenderFromEntity(entity) == null) {
			EntityRender entityRender = new EntityRender(entity);
			this.entityRenders.add(entityRender);
			this.canvas.getChildren().add(entityRender.getCanvas());
		}
	}

	private void checkEntitiesState() {
		Iterator iterator = this.entityRenders.iterator();
		Entity entity = null;
		while (iterator.hasNext()) {
			entity = ((EntityRender) iterator.next()).getEntity();
			if (entity.isDead())
				removeEntityToRender(entity);
		}
	}

	private EntityRender getEntityRenderFromEntity(Entity entity) {
		for (EntityRender entityRender: this.entityRenders) {
			if (entityRender.getEntity() == entity)
				return entityRender;
		}
		return null;
	}

	protected void initialize() {
		super.initialize();
		initializeEntities();
	}

	private void initializeEntities() {
		for (Entity entity: army.getListEntities())
			addEntityToRender(entity);
	}

	private void removeEntityToRender(Entity entity) {
		EntityRender entityRender = getEntityRenderFromEntity(entity);
		if (entityRender != null) {
			this.canvas.getChildren().remove(entityRender.getCanvas());
			this.entityRenders.remove(entityRender);
		}
	}

	public void update() {
		checkEntitiesState();
	}

	/*** GETTER/SETTER ********************************************/

	public Army getArmy() {
		return this.army;
	}
}
