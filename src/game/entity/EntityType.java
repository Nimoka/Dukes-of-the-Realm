package game.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Type of an entity.
 */
public enum EntityType {
	CATAPULT,                               /** Entity is a catapult. */
	KNIGHT,                                 /** Entity is a knight. */
	PIKEMAN;                                /** Entity is a pikeman. */

	private static final List<EntityType> values = Collections.unmodifiableList(Arrays.asList(values()));  /** List of all EntityType values. */

	/**
	 * Return a random EntityType value.
	 * @return Random EntityType value.
	 */
	public static final EntityType random() {
		Random generator = new Random();
		return values.get(generator.nextInt(values.size()));
	}
}
