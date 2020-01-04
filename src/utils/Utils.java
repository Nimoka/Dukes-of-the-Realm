package utils;

/**
 * Some useful elements.
 */
public abstract class Utils {
	/* METHODS ****************************************************/

	/**
	 * Write a plural form text depending on a number.
	 * @param number Number.
	 * @param text Text at a singular form.
	 * @return Text at the right form.
	 */
	public static String interpretNumber(int number, String text) {
		return number + " " + text + ((number < 2) ? "" : "s");
	}
}
