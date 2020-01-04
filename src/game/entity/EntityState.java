package game.entity;

/**
 * State of an entity.
 */
public enum EntityState {
	SLEEP,                                  /** The entity is sleeping. */
	MOVE,                                   /** The entity is moving to the target. */
	ATTACK,                                 /** The entity is attacking the target. */
	DIE;                                    /** The entity is dead. */
}
