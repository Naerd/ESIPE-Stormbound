package stormboundESIPE;

import java.util.ArrayList;

public enum Player {

	PLAYER1("Player 1", null), PLAYER2("Player 2", null);

	private final String name;

	// private final String color;

	private ArrayList<Card> deck;
	private ArrayList<Card> hand;
	private Base base;

	Player(String name, ArrayList<Card> deck) {
		this.name = name;
		// TODO : Initialiser dans le modèle en début de game. + faire en sorte qu'ils
		// soient aléatoire.
		hand = null;
		this.deck = deck;
		// base = initBase();
	}

	// Base initBase() {
	// if(getName() == Player.PLAYER1.getName()) {
	//
	// } else {
	//
	// }
	// }

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return getName().toString();
	}

}
