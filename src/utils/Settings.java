package utils;

import javafx.geometry.Insets;
import javafx.scene.paint.Color;

public abstract class Settings {
	/*** CONSTANTS ************************************************/

	// change values to make the game playable
	// have smaller cells to make the game smooth
	// have a number of non-player dukes

	public static double BOARD_CELL_STYLE_HEIGHT = 24;
	public static double BOARD_CELL_STYLE_WIDTH = 24;
	public static int BOARD_DIM_HEIGHT = 32;
	public static int BOARD_DIM_WIDTH = 48;
	public static int BOARD_NB_DUKES_PLAYERS = 8;
	public static int BOARD_NB_DUKES_BARONS = 16;
	public static Color BOARD_STYLE_BACKGROUND_COLOR = Color.rgb(0, 165, 0);
	public static Color BOARD_STYLE_LINES_COLOR = Color.rgb(0, 145, 0);
	public static double BOARD_STYLE_LINES_WIDTH = 1.;

	public static int CASTLE_BARON_LEVEL_MIN = 5;
	public static int CASTLE_BARON_LEVEL_MAX = 30;
	public static int CASTLE_BARON_NB_CATAPULT_MIN = 10;
	public static int CASTLE_BARON_NB_CATAPULT_MAX = 30;
	public static int CASTLE_BARON_NB_KNIGHT_MIN = 10;
	public static int CASTLE_BARON_NB_KNIGHT_MAX = 30;
	public static int CASTLE_BARON_NB_PIKEMAN_MIN = 10;
	public static int CASTLE_BARON_NB_PIKEMAN_MAX = 30;
	public static int CASTLE_BARON_TREASURE_MIN = 10000;
	public static int CASTLE_BARON_TREASURE_MAX = 100000;
	public static int CASTLE_DEFAULT_LEVEL = 1;
	public static int CASTLE_DEFAULT_NB_CATAPULT = 0;
	public static int CASTLE_DEFAULT_NB_KNIGHT = 0;
	public static int CASTLE_DEFAULT_NB_PIKEMAN = 0;
	public static int CASTLE_DISTANCE = 4;
	public static Color CASTLE_STYLE_FILL_COLOR = Color.rgb(150, 150, 150);
	public static Color CASTLE_STYLE_STROKE_COLOR = Color.rgb(120, 120, 120);
	public static double CASTLE_STYLE_STROKE_WIDTH = 4.;
	public static double CASTLE_STYLE_CASTLE_HEIGHT = BOARD_CELL_STYLE_HEIGHT * .8;
	public static double CASTLE_STYLE_CASTLE_WIDTH = BOARD_CELL_STYLE_WIDTH * .8;
	public static double CASTLE_STYLE_DOOR_HEIGHT = CASTLE_STYLE_STROKE_WIDTH * 2;
	public static double CASTLE_STYLE_DOOR_WIDTH = BOARD_CELL_STYLE_WIDTH * .4;

	public static int ENTITY_CATAPULT_ATTACK = 10;
	public static int ENTITY_CATAPULT_HEALTH = 5;
	public static int ENTITY_CATAPULT_PROD_COST = 1000;
	public static int ENTITY_CATAPULT_PROD_TIME = 50;
	public static int ENTITY_CATAPULT_SPEED = 1;
	public static Color ENTITY_CATAPULT_STYLE_COLOR = Color.rgb(100, 50, 20);
	public static double ENTITY_CATAPULT_STYLE_HEIGHT = BOARD_CELL_STYLE_HEIGHT * .12;
	public static double ENTITY_CATAPULT_STYLE_WIDTH = BOARD_CELL_STYLE_WIDTH * .3;

	public static int ENTITY_KNIGHT_ATTACK = 5;
	public static int ENTITY_KNIGHT_HEALTH = 3;
	public static int ENTITY_KNIGHT_PROD_COST = 500;
	public static int ENTITY_KNIGHT_PROD_TIME = 20;
	public static int ENTITY_KNIGHT_SPEED = 6;
	public static Color ENTITY_KNIGHT_STYLE_COLOR = Color.rgb(170, 170, 190);
	public static double ENTITY_KNIGHT_STYLE_HEIGHT = BOARD_CELL_STYLE_HEIGHT * .14;
	public static double ENTITY_KNIGHT_STYLE_WIDTH = BOARD_CELL_STYLE_WIDTH * .05;

	public static int ENTITY_PIKEMAN_ATTACK = 1;
	public static int ENTITY_PIKEMAN_HEALTH = 1;
	public static int ENTITY_PIKEMAN_PROD_COST = 100;
	public static int ENTITY_PIKEMAN_PROD_TIME = 5;
	public static int ENTITY_PIKEMAN_SPEED = 2;
	public static Color ENTITY_PIKEMAN_STYLE_COLOR = Color.rgb(180, 40, 0);
	public static double ENTITY_PIKEMAN_STYLE_HEIGHT = BOARD_CELL_STYLE_HEIGHT * .14;
	public static double ENTITY_PIKEMAN_STYLE_WIDTH = BOARD_CELL_STYLE_WIDTH * .05;

	public static double HUD_STYLE_HEIGHT = 80.;
	public static Insets HUD_STYLE_PADDING = new Insets(15, 15, 15, 15);

	public static double WINDOW_DEFAULT_HEIGHT = 800;
	public static double WINDOW_DEFAULT_WIDTH = 1280;
	public static double WINDOW_MIN_HEIGHT = 400;
	public static double WINDOW_MIN_WIDTH = 600;
	public static String WINDOW_TITLE = "Dukes of the Realm";

	/*** METHODS **************************************************/

	public static int CASTLE_DOOR_SIZE(int currentLevel) {
		return 3 + (currentLevel / 10);
	}

	public static int CASTLE_LEVEL_GAIN(int currentLevel) {
		return (10 * currentLevel);
	}

	public static int CASTLE_LEVEL_PROD_COST(int currentLevel) {
		return (1000 * (currentLevel + 1));
	}

	public static int CASTLE_LEVEL_PROD_TIME(int currentLevel) {
		return (100 + 50 * (currentLevel + 1));
	}
}
