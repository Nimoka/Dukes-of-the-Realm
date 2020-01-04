package render;

import game.Board;
import main.Main;
import static utils.Settings.*;

import java.util.ArrayList;

import game.castle.Castle;
import game.entity.group.Army;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class BoardRender extends Render {
	/*** VARIABLES ************************************************/

	private Board board;
	private Main environment;

	private Rectangle background;
	private ArrayList<Line> lines;

	private Pane castlesCanvas;
	private ArrayList<CastleRender> castleRenders;

	private Pane armiesCanvas;
	private ArrayList<ArmyRender> armyRenders;

	/*** CONSTRUCTORS *********************************************/

	public BoardRender(Board board, Main environment) {
		this.board = board;
		this.environment = environment;
		initialize();
	}

	/*** METHODS **************************************************/

	private void addArmyToRender(Army army) {
		if (getArmyRenderFromArmy(army) == null) {
			ArmyRender armyRender = new ArmyRender(army);
			this.armyRenders.add(armyRender);
			this.armiesCanvas.getChildren().add(armyRender.getCanvas());
		}
	}

	private void addCastleToRender(Castle castle) {
		if (getCastleRenderFromCastle(castle) == null) {
			CastleRender castleRender = new CastleRender(castle, this.environment);
			this.castleRenders.add(castleRender);
			this.castlesCanvas.getChildren().add(castleRender.getCanvas());
		}
	}

	private ArmyRender getArmyRenderFromArmy(Army army) {
		for (ArmyRender armyRender: this.armyRenders) {
			if (armyRender.getArmy() == army)
				return armyRender;
		}
		return null;
	}

	private CastleRender getCastleRenderFromCastle(Castle castle) {
		for (CastleRender castleRender: this.castleRenders) {
			if (castleRender.getCastle() == castle)
				return castleRender;
		}
		return null;
	}

	protected void initialize() {
		super.initialize();
		initializeBackground();
		initializeLines();
		initializeCastleRenders();
		initializeArmyRenders();
		update();
	}

	private void initializeBackground() {
		this.background = new Rectangle();
		this.background.setHeight(BOARD_CELL_STYLE_HEIGHT * BOARD_DIM_HEIGHT);
		this.background.setWidth(BOARD_CELL_STYLE_WIDTH * BOARD_DIM_WIDTH);
		this.background.setFill(BOARD_STYLE_BACKGROUND_COLOR);
		this.canvas.getChildren().add(this.background);
	}

	private void initializeCastleRenders() {
		this.castleRenders = new ArrayList<>();
		this.castlesCanvas = new Pane();
		this.canvas.getChildren().add(castlesCanvas);
		for (Castle castle: this.board.getCastles())
			addCastleToRender(castle);
	}

	private void initializeArmyRenders() {
		this.armyRenders = new ArrayList<>();
		this.armiesCanvas = new Pane();
		this.canvas.getChildren().add(armiesCanvas);
	}

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

	private void removeArmyToRender(Army army) {
		ArmyRender armyRender = getArmyRenderFromArmy(army);
		if (armyRender != null) {
			this.armiesCanvas.getChildren().remove(armyRender.getCanvas());
			this.armyRenders.remove(armyRender);
		}
	}

	private void removeCastleToRender(Castle castle) {
		CastleRender castleRender = getCastleRenderFromCastle(castle);
		if (castleRender != null) {
			this.castlesCanvas.getChildren().remove(castleRender.getCanvas());
			this.castleRenders.remove(castleRender);
		}
	}

	public void update() {
		updateCastles();
		updateArmy();
	}

	private void updateArmy() {
		for (ArmyRender armyRender: this.armyRenders)
			armyRender.update();
	}

	private void updateCastles() {
		for (CastleRender castleRender: this.castleRenders)
			castleRender.update();
	}

	/*** GETTER/SETTER ********************************************/

	public Board getBoard() {
		return this.board;
	}
}
