package tests;

import game.*;
import utils.NameGenerator;

public class Playground {
	public static void printBoard() {
		Board board = new Board();
		System.out.println(board.toString());
	}

	public static void printRandomNames(int nb) {
		for (int i = 0; i < nb; i++)
			System.out.println(NameGenerator.random());
	}

	public static void main(String[] args) {
	}
}
