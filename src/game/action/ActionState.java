package game.action;

/**
 * State of an action.
 */
public enum ActionState {
	LAUNCH,                                 /** The army is waiting to go. */
	MOVE,                                   /** The army is moving to the target. */
	ATTACK;                                 /** The army is attacking the target. */
}
