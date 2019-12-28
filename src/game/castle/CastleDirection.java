package game.castle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum CastleDirection {
	NORTH,
	EAST,
	SOUTH,
	WEST;

	private static final List<CastleDirection> values = Collections.unmodifiableList(Arrays.asList(values()));

	public static final CastleDirection getRandom() {
		Random generator = new Random();
		return values.get(generator.nextInt(values.size()));
	}
}