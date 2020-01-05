package tests;

import game.*;
import main.Main;
import player.ComputerPlayer;
import player.Player;
import utils.NameGenerator;

import java.util.ArrayList;

/**
 * A playground to launch random tests.
 */
public class Playground {
	/* METHODS ****************************************************/

	/**
	 * Create a random board and print his informations.
	 */
	public static void printBoard() {
		ArrayList<Player> players = new ArrayList<>();
		players.add(new ComputerPlayer());
		Board board = new Board(players, new Main());
		System.out.println(board.toString());
	}

	/**
	 * Print some random names.
	 * @param nb Number of names to print.
	 */
	public static void printRandomNames(int nb) {
		for (int i = 0; i < nb; i++)
			System.out.println(NameGenerator.random());
	}

	/**
	 * Main method where tests are launched.
	 * @param args Arguments of the shell.
	 */
	public static void main(String[] args) {
	}
}
