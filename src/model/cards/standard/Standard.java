package model.cards.standard;

import java.util.Objects;

import model.cards.Cards;

public abstract class Standard implements Cards {
	// A("A", 2, 0, 1), B("B", 2, 1, 2), C("C", 3, 1, 3), D("D", 4, 0, 3), E("E", 2,
	// 2, 4), F("F", 4, 1, 4), G("G", 5, 1,
	// 5), H("H", 7, 1, 7), I("I", 9, 1, 8);

	private final String name;
	private final int mana;
	private int strength;
	private final int move;
	// private Player player;

	public Standard(String a, int strength, int move, int mana) {
		this.name = Objects.requireNonNull(a);
		this.strength = strength;
		this.move = move;
		this.mana = Objects.requireNonNull(mana);
		// this.player = Objects.requireNonNull(player);
	}

	public String getName() {
		return this.name;
	}

	public int getMana() {
		return this.mana;
	}

	public int getStrength() {
		return this.strength;
	}

	public int getMove() {
		return this.move;
	}

	// public Player getPlayer() {
	// return this.player;
	// }
	//
	// public String toString() {
	// return this.player.getName() + ":(" + this.strength + ";" + this.move + ";" +
	// this.mana + ")";
	// }

	public boolean isDead() {
		return (this.strength <= 0);
	}

	public void outch(int dmg) {
		this.strength -= strength - dmg;
	}

}
