package utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * A name generator.
 */
public abstract class NameGenerator {
	/* CONSTANTS **************************************************/

	private static List<String> firstName = Arrays.asList("Jean", "Philippe", "Édouard", "Christophe", "Jean-Pierre", "Michel", "Théo", "Nicolas", "Laurent", "Yann", "Hugues", "Baptiste", "George", "Joseph", "Marcel", "Roland", "Guillaume", "Jean-Louis", "Ernest", "François", "Pierre", "Louis", "Guy", "Clovis", "Antoine", "Daniel", "Branlouis", "Richard", "Samuel", "Jostophe", "Hubert", "Victor", "Napoléon", "Bertrand", "Jacques", "Paul", "Maxime", "Bernard", "Roger", "Robert", "Romain", "Raoul", "André", "Ambroise", "Adrien", "Philibert", "Benjamin", "Léon", "Christian", "Constantin", "Barnabé", "Yves", "Gilles", "René", "Simon", "Florentin", "Didier", "Jérôme", "Charles", "Armand", "Brice", "Igor", "Baltazar", "Ghislain", "Alain", "Cyprien", "Sylvain", "Jean-Baptiste", "Frédérique", "Sébastien", "Matthieu", "Julien"); /** List of first names. */
	private static List<String> lastName = Arrays.asList("le Grand", "le Bon", "le Bref", "le Simple", "le Petit", "le Gentil", "le Moine", "l’Évéillé", "le Nez", "le Bleu", "le Maigre", "le Bègue", "le Moyen", "l’Édenté", "le Duc", "le Preux", "Dupaissay", "McNuggets", "de Bordeaux", "du Chemin", "de la Rue", "aux Grands Pieds", "le Vaillant", "le Gueux", "le Faible", "le Fort", "le Fol", "de la Bouche-en-Biais", "du Mollard", "du Froc", "du Trône", "l’Ahuri", "l’Envieux", "le Brun", "le Goinfre", "le Bâtard", "de Nice", "d’Hossegor", "le Paresseux", "le Grincheux", "le Râleur", "le Pingre", "l’Estropié", "le Borgne", "le Chauve", "le Muet", "le Sourd", "l’Ivrogne", "l’Éventreur", "l’Allemand", "l’Espagnol", "le Breton", "l’Empereur", "l’Évêque", "le Fignard", "le Dégénéré", "la Terreur");   /** List of last names. */

	/* METHODS ****************************************************/

	/**
	 * Get a random name based on random first name and last name.
	 * @return Random name.
	 */
	public static String random() {
		Random generator = new Random();
		return firstName.get(generator.nextInt(firstName.size())) + " " + lastName.get(generator.nextInt(lastName.size()));
	}
}
