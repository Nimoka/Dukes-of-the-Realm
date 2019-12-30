package render;

import game.Board;
import game.entity.Entity;

public class EntityRender extends Render {
	/*** VARIABLES ************************************************/

	private Entity entity;

	/*** CONSTRUCTORS *********************************************/

	public EntityRender(Entity entity) {
		this.entity = entity;
	}

	/*** METHODS **************************************************/

	public void initialize() {

	}

	/*** GETTER/SETTER ********************************************/

	public Entity getEntity() {
		return this.entity;
	}
}
