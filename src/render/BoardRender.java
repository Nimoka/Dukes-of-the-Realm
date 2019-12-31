package render;

import game.Board;
import static utils.Settings.*;

import java.util.ArrayList;

import game.castle.Castle;
import javafx.scene.paint.Color;
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
		this.background.setHeight(DISPLAY_CELL_HEIGHT * BOARD_HEIGHT);
		this.background.setWidth(DISPLAY_CELL_WIDTH * BOARD_WIDTH);
		this.background.setFill(Color.LAWNGREEN);
		this.canvas.getChildren().add(this.background);
	}

	private void initializeCastleRenders() {
		this.castleRenders = new ArrayList<>();
	}

	private void initializeLines() {
		this.lines = new ArrayList<>();
		for (int i = 0; i <= BOARD_WIDTH; i++) {
			Line line = new Line();
			line.setStartX(i * DISPLAY_CELL_WIDTH);
			line.setEndX(i * DISPLAY_CELL_WIDTH);
			line.setStartY(0);
			line.setEndY(this.background.getHeight());
			this.lines.add(line);
		}
		for (int j = 0; j <= BOARD_HEIGHT; j++) {
			Line line = new Line();
			line.setStartX(0);
			line.setEndX(this.background.getWidth());
			line.setStartY(j * DISPLAY_CELL_HEIGHT);
			line.setEndY(j * DISPLAY_CELL_HEIGHT);
			this.lines.add(line);
		}
		for (Line line: lines) {
			line.setStroke(Color.DARKGREEN);
			line.setStrokeWidth(1);
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
