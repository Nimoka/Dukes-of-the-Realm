package utils;

import game.castle.Castle;
import game.duke.DukeType;
import static utils.Utils.*;
import static utils.Utils.interpretNumber;

import javafx.geometry.Insets;
import javafx.scene.paint.Color;

public abstract class Settings {
	/* CONSTANTS **************************************************/

	// change values to make the game playable
	// have smaller cells to make the game smooth

	public static double BOARD_CELL_STYLE_HEIGHT = 24;          /** Display height of a board's cell (in pixels). */
	public static double BOARD_CELL_STYLE_WIDTH = 24;           /** Display width of a board's cell (in pixels). */
	public static int BOARD_DIM_HEIGHT = 32;                    /** Height of the board (in cells). */
	public static int BOARD_DIM_WIDTH = 48;                     /** Width of the board (in cells). */
	public static int BOARD_FIRST_TURN = 1;                     /** Default turn number of the board. */
	public static int BOARD_NB_DUKES_PLAYERS = 8;               /** Number of dukes computer players. */
	public static int BOARD_NB_DUKES_BARONS = 16;               /** Number of dukes computer not-players (barons). */
	public static Color BOARD_STYLE_BACKGROUND_COLOR = Color.rgb(0, 165, 0);        /** Color of the background of the board. */
	public static Color BOARD_STYLE_LINES_COLOR = Color.rgb(0, 145, 0);             /** Color of the lines of the board. */
	public static double BOARD_STYLE_LINES_WIDTH = 1.;          /** Width of the lines of the board (in pixels). */

	public static int CASTLE_BARON_LEVEL_MIN = 5;               /** Minimum level of a baron's castle. */
	public static int CASTLE_BARON_LEVEL_MAX = 30;              /** Maximum level of a baron's castle. */
	public static int CASTLE_BARON_NB_CATAPULT_MIN = 10;        /** Minimum number of catapults of a baron's castle. */
	public static int CASTLE_BARON_NB_CATAPULT_MAX = 30;        /** Maximum number of catapults of a baron's castle. */
	public static int CASTLE_BARON_NB_KNIGHT_MIN = 10;          /** Minimum number of knights of a baron's castle. */
	public static int CASTLE_BARON_NB_KNIGHT_MAX = 30;          /** Maximum number of knights of a baron's castle. */
	public static int CASTLE_BARON_NB_PIKEMAN_MIN = 10;         /** Minimum number of pikeman of a baron's castle. */
	public static int CASTLE_BARON_NB_PIKEMAN_MAX = 30;         /** Maximum number of pikeman of a baron's castle. */
	public static int CASTLE_BARON_TREASURE_MIN = 10000;        /** Minimum treasure of a baron's castle. */
	public static int CASTLE_BARON_TREASURE_MAX = 100000;       /** Maximum treasure of a baron's castle. */
	public static int CASTLE_DEFAULT_LEVEL = 1;                 /** Default level of a castle. */
	public static int CASTLE_DEFAULT_NB_CATAPULT = 5;           /** Default number of catapult of a castle. */
	public static int CASTLE_DEFAULT_NB_KNIGHT = 5;             /** Default number of knight of a castle. */
	public static int CASTLE_DEFAULT_NB_PIKEMAN = 5;            /** Default number of pikeman of a castle. */
	public static int CASTLE_DISTANCE = 4;                      /** Minimum distance between two castles. */
	public static Color CASTLE_STYLE_FILL_COLOR = Color.rgb(150, 150, 150);         /** Color of a castle. */
	public static Color CASTLE_STYLE_STROKE_COLOR = Color.rgb(120, 120, 120);       /** Color of the wall of a castle. */
	public static double CASTLE_STYLE_STROKE_WIDTH = 4.;        /** Width of the wall of a castle (in pixels). */
	public static double CASTLE_STYLE_CASTLE_HEIGHT = BOARD_CELL_STYLE_HEIGHT * .8; /** Height of a castle (in pixels). */
	public static double CASTLE_STYLE_CASTLE_WIDTH = BOARD_CELL_STYLE_WIDTH * .8;   /** Width of a castle (in pixels). */
	public static double CASTLE_STYLE_DOOR_HEIGHT = CASTLE_STYLE_STROKE_WIDTH * 2;  /** Height of the door of a castle (in pixels). */
	public static double CASTLE_STYLE_DOOR_WIDTH = BOARD_CELL_STYLE_WIDTH * .4;     /** Width of the door of a castle (in pixels). */
	public static Color CASTLE_STYLE_SELECTED_FILL_COLOR = Color.rgb(255, 255, 255);    /** Color of a selected castle. */

	public static int ENTITY_CATAPULT_ATTACK = 10;              /** Attack points of a catapult. */
	public static int ENTITY_CATAPULT_HEALTH = 5;               /** Health points of a catapult. */
	public static int ENTITY_CATAPULT_PROD_COST = 1000;         /** Production cost of a catapult (in florins). */
	public static int ENTITY_CATAPULT_PROD_TIME = 50;           /** Production time of a catapult (in turns). */
	public static int ENTITY_CATAPULT_SPEED = 1;                /** Speed of a catapult (in cells/turn). */
	public static Color ENTITY_CATAPULT_STYLE_COLOR = Color.rgb(100, 50, 20);       /** Color of a catapult. */
	public static double ENTITY_CATAPULT_STYLE_HEIGHT = BOARD_CELL_STYLE_HEIGHT * .12;  /** Height of a catapult (in pixels). */
	public static double ENTITY_CATAPULT_STYLE_WIDTH = BOARD_CELL_STYLE_WIDTH * .3; /** Width of a catapult (in pixels). */

	public static int ENTITY_KNIGHT_ATTACK = 5;                 /** Attack points of a knight. */
	public static int ENTITY_KNIGHT_HEALTH = 3;                 /** Health points of a knight. */
	public static int ENTITY_KNIGHT_PROD_COST = 500;            /** Production cost of a knight (in florins). */
	public static int ENTITY_KNIGHT_PROD_TIME = 20;             /** Production time of a knight (in turns). */
	public static int ENTITY_KNIGHT_SPEED = 6;                  /** Speed of a knight (in cells/turn). */
	public static Color ENTITY_KNIGHT_STYLE_COLOR = Color.rgb(170, 170, 190);       /** Color of a kngiht. */
	public static double ENTITY_KNIGHT_STYLE_HEIGHT = BOARD_CELL_STYLE_HEIGHT * .14;    /** Height of a knight (in pixels). */
	public static double ENTITY_KNIGHT_STYLE_WIDTH = BOARD_CELL_STYLE_WIDTH * .05;  /** Width of a knight (in pixels). */

	public static int ENTITY_PIKEMAN_ATTACK = 1;                /** Attack points of a pikeman. */
	public static int ENTITY_PIKEMAN_HEALTH = 1;                /** Health points of a pikeman. */
	public static int ENTITY_PIKEMAN_PROD_COST = 100;           /** Production cost of a pikeman (in florins). */
	public static int ENTITY_PIKEMAN_PROD_TIME = 5;             /** Production time of a pikeman (in turns). */
	public static int ENTITY_PIKEMAN_SPEED = 2;                 /** Speed of a pikeman (in cells/turn). */
	public static Color ENTITY_PIKEMAN_STYLE_COLOR = Color.rgb(180, 40, 0);         /** Color of a pikeman. */
	public static double ENTITY_PIKEMAN_STYLE_HEIGHT = BOARD_CELL_STYLE_HEIGHT * .14;   /** Height of a pikeman (in pixels). */
	public static double ENTITY_PIKEMAN_STYLE_WIDTH = BOARD_CELL_STYLE_WIDTH * .05; /** Width of a pikeman (in pixels). */

	public static double GAME_TURN_DURATION = 1.2;              /** Duration of a turn (in seconds). */

	public static double HUD_STYLE_HEIGHT = 100.;               /** Height of the HUD (in pixels). */
	public static Insets HUD_STYLE_PADDING = new Insets(15, 15, 15, 15);            /** Padding of the HUD (in pixels). */

	public static double WINDOW_DEFAULT_HEIGHT = 800;           /** Default height of the window (in pixels). */
	public static double WINDOW_DEFAULT_WIDTH = 1280;           /** Default width of the window (in pixels). */
	public static double WINDOW_MIN_HEIGHT = 400;               /** Minimum height of the window (in pixels). */
	public static double WINDOW_MIN_WIDTH = 600;                /** Minimum width of the window (in pixels). */
	public static String WINDOW_TITLE = "Dukes of the Realm";   /** Title of the window. */

	/* METHODS ****************************************************/

	/**
	 * Size of the door of the castle, depending on its level.
	 * @param currentLevel Level of the castle.
	 * @return Size of the door (in entities).
	 */
	public static int CASTLE_DOOR_SIZE(int currentLevel) {
		return 3 + (currentLevel / 10);
	}

	/**
	 * Revenue of a castle depending on its level and the type of its duke
	 * @param currentLevel Level of the castle.
	 * @param dukeType Type of the duke of the castle.
	 * @return Revenue of the castle (in florins).
	 */
	public static int CASTLE_LEVEL_GAIN(int currentLevel, DukeType dukeType) {
		if (dukeType == DukeType.BARON)
			return currentLevel;
		return (10 * currentLevel);
	}

	/**
	 * Production cost of the level of a castle depending on its level.
	 * @param currentLevel Level of the castle.
	 * @return Production cost of the level (in florins).
	 */
	public static int CASTLE_LEVEL_PROD_COST(int currentLevel) {
		return (1000 * (currentLevel + 1));
	}

	/**
	 * Production time of the level of a castle depending on its level.
	 * @param currentLevel Level of the castle.
	 * @return Production time of the level (in turns).
	 */
	public static int CASTLE_LEVEL_PROD_TIME(int currentLevel) {
		return (100 + 50 * (currentLevel + 1));
	}

	/**
	 * Text for the duke name label for a castle.
	 * @param castle Castle to analyze.
	 * @return Text for the label.
	 */
	public static String HUD_LABEL_CASTLE_DUKE_NAME(Castle castle) {
		return ((castle.getDuke().getType() == DukeType.PLAYER) ? "Duc" : "Baron") + " " + castle.getDuke().getName();
	}

	/**
	 * Text for the level label for a castle.
	 * @param castle Castle to analyze.
	 * @return Text for the label.
	 */
	public static String HUD_LABEL_CASTLE_LEVEL(Castle castle) {
		return "Niveau " + castle.getLevel() + " (+ " + interpretNumber(CASTLE_LEVEL_GAIN(castle.getLevel(), castle.getDuke().getType()), "florin") +  "/tour)";
	}

	/**
	 * Text for the stock label for a castle.
	 * @param castle Castle to analyze.
	 * @return Text for the label.
	 */
	public static String HUD_LABEL_CASTLE_STOCK(Castle castle) {
		return interpretNumber(castle.getStock().getNbPikemen(), "piquier") + '\n' + interpretNumber(castle.getStock().getNbKnights(), "chevalier") + '\n' + interpretNumber(castle.getStock().getNbCatapults(), "catapulte");
	}

	/**
	 * Text for the treasure label for a castle.
	 * @param castle Castle to analyze.
	 * @return Text for the label.
	 */
	public static String HUD_LABEL_CASTLE_TREASURE(Castle castle) {
		return interpretNumber(castle.getTreasure(), "florin");
	}

	/**
	 * Text for the turn label.
	 * @param currentTurn Turn.
	 * @return Text for the label.
	 */
	public static String HUD_LABEL_TURN(int currentTurn) {
		return "Tour " + currentTurn;
	}
}
