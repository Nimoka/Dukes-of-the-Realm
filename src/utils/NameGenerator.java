package utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class NameGenerator {
	private static List<String> firstName = Arrays.asList("Jean", "Philippe", "Édouard", "Christophe", "Jean-Pierre", "Michel", "Théo", "Nicolas", "Laurent");
	private static List<String> lastName = Arrays.asList("le Grand", "le Bon", "le Bref", "le Simple", "le Petit", "le Gentil", "le Moine", "le Tôt", "le Nez", "le Bleu");

	public static String random() {
		Random generator = new Random();
		return firstName.get(generator.nextInt(firstName.size())) + " " + lastName.get(generator.nextInt(lastName.size()));
	}
}
