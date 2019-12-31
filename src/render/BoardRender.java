package render;

import game.Board;
import static utils.Settings.*;

import java.util.ArrayList;

import game.castle.Castle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class BoardRender extends Render {
	/*** VARIABLES ************************************************/

	private Board board;
	private Rectangle background;
	private ArrayList<Line> lines;
	private ArrayList<CastleRender> castleRenders;

	/*** CONSTRUCTORS *********************************************/

	public BoardRender(Board board) {
		this.board = board;
		initialize();
	}

	/*** METHODS **************************************************/

	protected void initialize() {
		super.initialize();
		initializeBackground();
		initializeLines();
		initializeCastleRenders();
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

	public void update() {
		for (Castle castle: this.board.getCastles()) {
			boolean needCreation = true;
			for (CastleRender castleRender: this.castleRenders) {
				if (castleRender.getCastle() == castle)
					needCreation = false;
			}
			if (needCreation) {
				CastleRender castleRender = new CastleRender(castle);
				this.canvas.getChildren().add(castleRender.getCanvas());
				this.castleRenders.add(castleRender);
			}
		}
	}

	/*** GETTER/SETTER ********************************************/

	public Board getBoard() {
		return this.board;
	}
}
