package render;

import game.Board;
import game.castle.Castle;
import game.entity.group.Army;
import main.Main;
import static utils.Settings.*;

import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * A render of a board.
 */
public class BoardRender extends Render {
	/* VARIABLES **************************************************/

	private Board board;                                        /** The board to render. */
	private Main environment;                                   /** The environment (application) of render. */

	private Rectangle background;                               /** Background shape of the render. */
	private ArrayList<Line> lines;                              /** List of the lines shapes of the render. */

	private Pane castlesCanvas;                                 /** Canvas of the castles renders. */
	private ArrayList<CastleRender> castleRenders;              /** List of the renders of the castles of the board. */

	private Pane armiesCanvas;                                  /** Canvas of the armies renders. */
	private ArrayList<ArmyRender> armyRenders;                  /** List of the renders of the armies of the board. */

	/* CONSTRUCTORS ***********************************************/

	/**
	 * Construct a new render for a board in an environment.
	 * @param board The board to render.
	 * @param environment The environment (application) of render.
	 */
	public BoardRender(Board board, Main environment) {
		this.board = board;
		this.environment = environment;
		initialize();
	}

	/* METHODS ****************************************************/

	/**
	 * Add an army to render.
	 * @param army Army to add.
	 */
	public void addArmyToRender(Army army) {
		if (getArmyRenderFromArmy(army) == null) {
			System.err.println("new army launched to render");
			ArmyRender armyRender = new ArmyRender(army);
			this.armyRenders.add(armyRender);
			this.armiesCanvas.getChildren().add(armyRender.getCanvas());
		}
	}

	/**
	 * Add a castle to render.
	 * @param castle Castle to add.
	 */
	public void addCastleToRender(Castle castle) {
		if (getCastleRenderFromCastle(castle) == null) {
			CastleRender castleRender = new CastleRender(castle, this.environment);
			this.castleRenders.add(castleRender);
			this.castlesCanvas.getChildren().add(castleRender.getCanvas());
		}
	}

	/**
	 * Get the render of an army using itself.
	 * @param army Army of the render to find.
	 * @return Render of the army.
	 */
	private ArmyRender getArmyRenderFromArmy(Army army) {
		for (ArmyRender armyRender: this.armyRenders) {
			if (armyRender.getArmy() == army)
				return armyRender;
		}
		return null;
	}

	/**
	 * Get the render of a castle using itself.
	 * @param castle Castle of the render to find.
	 * @return Render of the castle.
	 */
	private CastleRender getCastleRenderFromCastle(Castle castle) {
		for (CastleRender castleRender: this.castleRenders) {
			if (castleRender.getCastle() == castle)
				return castleRender;
		}
		return null;
	}

	/**
	 * Initialize the render.
	 */
	protected void initialize() {
		super.initialize();
		initializeBackground();
		initializeLines();
		initializeCastleRenders();
		initializeArmyRenders();
		update();
	}

	/**
	 * Initialize the background shape of the render.
	 */
	private void initializeBackground() {
		this.background = new Rectangle();
		this.background.setHeight(BOARD_CELL_STYLE_HEIGHT * BOARD_DIM_HEIGHT);
		this.background.setWidth(BOARD_CELL_STYLE_WIDTH * BOARD_DIM_WIDTH);
		this.background.setFill(BOARD_STYLE_BACKGROUND_COLOR);
		this.canvas.getChildren().add(this.background);
	}

	/**
	 * Initialize the castles renders.
	 */
	private void initializeCastleRenders() {
		this.castleRenders = new ArrayList<>();
		this.castlesCanvas = new Pane();
		this.canvas.getChildren().add(castlesCanvas);
		for (Castle castle: this.board.getCastles())
			addCastleToRender(castle);
	}

	/**
	 * Initialize the army renders.
	 */
	private void initializeArmyRenders() {
		this.armyRenders = new ArrayList<>();
		this.armiesCanvas = new Pane();
		this.canvas.getChildren().add(armiesCanvas);
	}

	/**
	 * Initialize the lines shapes of the render.
	 */
	private void initializeLines() {
		this.lines = new ArrayList<>();
		for (int i = 0; i <= BOARD_DIM_WIDTH; i++) {
			Line line = new Line();
			line.setStartX(i * BOARD_CELL_STYLE_WIDTH);
			line.setEndX(i * BOARD_CELL_STYLE_WIDTH);
			line.setStartY(0);
			line.setEndY(this.background.getHeight());
			this.lines.add(line);
		}
		for (int j = 0; j <= BOARD_DIM_HEIGHT; j++) {
			Line line = new Line();
			line.setStartX(0);
			line.setEndX(this.background.getWidth());
			line.setStartY(j * BOARD_CELL_STYLE_HEIGHT);
			line.setEndY(j * BOARD_CELL_STYLE_HEIGHT);
			this.lines.add(line);
		}
		for (Line line: lines) {
			line.setStroke(BOARD_STYLE_LINES_COLOR);
			line.setStrokeWidth(BOARD_STYLE_LINES_WIDTH);
		}
		this.canvas.getChildren().addAll(this.lines);
	}

	/**
	 * Remove an army to render.
	 * @param army Army to remove.
	 */
	private void removeArmyToRender(Army army) {
		ArmyRender armyRender = getArmyRenderFromArmy(army);
		if (armyRender != null) {
			this.armiesCanvas.getChildren().remove(armyRender.getCanvas());
			this.armyRenders.remove(armyRender);
		}
	}

	/**
	 * Remove a castle to render.
	 * @param castle Castle to remove.
	 */
	private void removeCastleToRender(Castle castle) {
		CastleRender castleRender = getCastleRenderFromCastle(castle);
		if (castleRender != null) {
			this.castlesCanvas.getChildren().remove(castleRender.getCanvas());
			this.castleRenders.remove(castleRender);
		}
	}

	/**
	 * Update the castles and armies.
	 * Called at each new turn.
	 */
	public void update() {
		updateCastles();
		updateArmy();
	}

	/**
	 * Call update() on each armies.
	 */
	private void updateArmy() {
		for (ArmyRender armyRender: this.armyRenders)
			armyRender.update();
	}

	/**
	 * Call update() on each castles.
	 */
	private void updateCastles() {
		for (CastleRender castleRender: this.castleRenders)
			castleRender.update();
	}

	/* GETTER/SETTER **********************************************/

	/**
	 * Getter on board.
	 * @return The board to render.
	 */
	public Board getBoard() {
		return this.board;
	}
}
