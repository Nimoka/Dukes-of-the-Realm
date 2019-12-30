import game.Board;
import game.Duke;
import game.castle.Castle;
import game.entity.Catapult;
import game.entity.Knight;
import game.entity.Pikeman;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import render.BoardRender;
import render.CastleRender;
import render.EntityRender;
import utils.Position;
import utils.Settings;

public class Main extends Application {
	/*** VARIABLES ************************************************/

	private Group rootGroup;
	private Scene scene;

	private Board board;
	private BoardRender boardRender;

	/*** METHODS **************************************************/

	private void createBoard() {
		this.board = new Board();
		this.boardRender = new BoardRender(this.board);
	}

	private void initializeScene() {
		this.rootGroup = new Group();
		this.scene = new Scene(rootGroup);
	}

	private void initializeStage(Stage stage) {
		stage.setTitle(Settings.WINDOW_TITLE);
		stage.setHeight(Settings.WINDOW_DEFAULT_HEIGHT);
		stage.setWidth(Settings.WINDOW_DEFAULT_WIDTH);
		stage.setMinHeight(Settings.WINDOW_MIN_HEIGHT);
		stage.setMinWidth(Settings.WINDOW_MIN_WIDTH);
		initializeScene();
		stage.setScene(this.scene);
		stage.show();
	}

	@Override
	public void start(Stage stage) throws Exception {
		createBoard();
		initializeStage(stage);
	}
}
