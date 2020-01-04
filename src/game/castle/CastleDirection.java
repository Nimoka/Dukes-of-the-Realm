package game.castle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Direction of the door of a castle.
 */
public enum CastleDirection {
	NORTH,                                  /** The door is facing north. */
	EAST,                                   /** The door is facing east. */
	SOUTH,                                  /** The door is facing south. */
	WEST;                                   /** The door is facing west. */

	private static final List<CastleDirection> values = Collections.unmodifiableList(Arrays.asList(values()));  /** List of all CastleDirection values. */

	/**
	 * Return a random CastleDirection value.
	 * @return Random CastleDirection value.
	 */
	public static final CastleDirection random() {
		Random generator = new Random();
		return values.get(generator.nextInt(values.size()));
	}
}