package utils;

import javafx.scene.paint.Color;

public abstract class Settings {
	/*** CONSTANTS ************************************************/

	public static int BOARD_CELL_STYLE_HEIGHT = 48;
	public static int BOARD_CELL_STYLE_WIDTH = 48;
	public static int BOARD_DIM_HEIGHT = 24;
	public static int BOARD_DIM_WIDTH = 32;
	public static int BOARD_NB_DUKES = 8;
	public static Color BOARD_STYLE_BACKGROUND_COLOR = Color.rgb(140, 220, 0);
	public static Color BOARD_STYLE_LINES_COLOR = Color.rgb(100, 125, 25);
	public static double BOARD_STYLE_LINES_WIDTH = 1.;

	public static int CASTLE_DEFAULT_LEVEL = 1;
	public static int CASTLE_DEFAULT_NB_CATAPULT = 0;
	public static int CASTLE_DEFAULT_NB_KNIGHT = 0;
	public static int CASTLE_DEFAULT_NB_PIKEMAN = 0;
	public static int CASTLE_DISTANCE = 4;
	public static Color CASTLE_STYLE_FILL_COLOR = Color.LIGHTGREY;
	public static Color CASTLE_STYLE_STROKE_COLOR = Color.DARKGREY;
	public static double CASTLE_STYLE_STROKE_WIDTH = 4.;

	public static int ENTITY_CATAPULT_ATTACK = 10;
	public static int ENTITY_CATAPULT_HEALTH = 5;
	public static int ENTITY_CATAPULT_PROD_COST = 1000;
	public static int ENTITY_CATAPULT_PROD_TIME = 50;
	public static int ENTITY_CATAPULT_SPEED = 1;
	public static int ENTITY_KNIGHT_ATTACK = 5;
	public static int ENTITY_KNIGHT_HEALTH = 3;
	public static int ENTITY_KNIGHT_PROD_COST = 500;
	public static int ENTITY_KNIGHT_PROD_TIME = 20;
	public static int ENTITY_KNIGHT_SPEED = 6;
	public static int ENTITY_PIKEMAN_ATTACK = 1;
	public static int ENTITY_PIKEMAN_HEALTH = 1;
	public static int ENTITY_PIKEMAN_PROD_COST = 100;
	public static int ENTITY_PIKEMAN_PROD_TIME = 5;
	public static int ENTITY_PIKEMAN_SPEED = 2;

	public static int WINDOW_DEFAULT_HEIGHT = 800;
	public static int WINDOW_DEFAULT_WIDTH = 1280;
	public static int WINDOW_MIN_HEIGHT = 400;
	public static int WINDOW_MIN_WIDTH = 600;
	public static String WINDOW_TITLE = "Dukes of the Realm";

	/*** METHODS **************************************************/

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
