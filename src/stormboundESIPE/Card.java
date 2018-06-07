package stormboundESIPE;

import java.util.Objects;

public class Card {
	private String name;
	private final int mana;
	private int strength;
	private final int move;
	private Player player;

	public Card(String name, int strength, int move, int mana, Player player) {
		this.name = Objects.requireNonNull(name);
		this.strength = strength;
		this.move = move;
		this.mana = Objects.requireNonNull(mana);
		this.player = Objects.requireNonNull(player);
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

	public Player getPlayer() {
		return this.player;
	}

	public String toString() {
		return this.player.getName() + ":(" + this.strength + ";" + this.move + ";" + this.mana + ")";
	}

	public boolean isDead() {
		return (this.strength <= 0);
	}

	public void outch(int dmg) {
		this.strength -= dmg;
	}

}
