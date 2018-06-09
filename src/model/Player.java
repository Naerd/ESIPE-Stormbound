package model;

import java.util.ArrayList;

import model.cards.Cards;

public enum Player {

	PLAYER1("A", null), PLAYER2("B", null);

	private final String name;

	// private final String color;

	private ArrayList<Cards> deck;
	private ArrayList<Cards> hand;
	private int health;
	private int mana;

	Player(String name, ArrayList<Cards> deck) {
		this.name = name;
		// TODO : Initialiser dans le modèle en début de game. + faire en sorte qu'ils
		// soient aléatoire.
		hand = null;
		this.deck = deck;
		this.health = 10;
		this.mana = 3;
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

	public int getHealth() {
		return health;
	}

	@Override
	public String toString() {
		return getName().toString();
	}

	public void outch(int damage) {
		health -= damage;
	}

}
