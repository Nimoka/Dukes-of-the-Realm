package tests;

import game.*;
import player.ComputerPlayer;
import player.Player;
import utils.NameGenerator;

import java.util.ArrayList;

public class Playground {
	public static void printBoard() {
		ArrayList<Player> players = new ArrayList<>();
		players.add(new ComputerPlayer());
		Board board = new Board(players);
		System.out.println(board.toString());
	}

	public static void printRandomNames(int nb) {
		for (int i = 0; i < nb; i++)
			System.out.println(NameGenerator.random());
	}

	public static void main(String[] args) {
	}
}
