package render;

import javafx.scene.layout.Pane;

/**
 * A render.
 */
public abstract class Render {
	/* VARIABLES **************************************************/

	protected Pane canvas;                  /** Base canvas of the render. */

	/* METHODS ****************************************************/

	/**
	 * Initialize the render.
	 */
	protected void initialize() {
		this.canvas = new Pane();
	}

	/**
	 * Update the render.
	 */
	public void update() {}

	/* GETTER/SETTER **********************************************/

	/**
	 * Getter of canvas.
	 * @return Base canvas of the render.
	 */
	public Pane getCanvas() {
		return this.canvas;
	}
}
