package render;

import javafx.scene.layout.Pane;

public abstract class Render {
	/*** VARIABLES ************************************************/

	protected Pane canvas;

	/*** METHODS **************************************************/

	protected void initialize() {
		this.canvas = new Pane();
	}

	public void update() {}

	/*** GETTER/SETTER ********************************************/

	public Pane getCanvas() {
		return this.canvas;
	}
}
