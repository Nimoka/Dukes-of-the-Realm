package utils;

public abstract class Utils {
	public static String interpretNumber(int number, String text) {
		return number + " " + text + ((number < 2) ? "" : "s");
	}
}
