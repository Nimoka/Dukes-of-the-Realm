package utils;

public abstract class Settings {
	/*** CONSTANTS ************************************************/

	public static int BOARD_HEIGHT = 24;
	public static int BOARD_NB_DUKES = 8;
	public static int BOARD_WIDTH = 32;

	public static int CASTLE_DISTANCE = 4;
	public static int CASTLE_LEVEL = 1;
	public static int CASTLE_NB_CATAPULT = 0;
	public static int CASTLE_NB_KNIGHT = 0;
	public static int CASTLE_NB_PIKEMAN = 0;

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
