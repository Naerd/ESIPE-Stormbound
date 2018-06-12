package model.cards;

import java.util.Objects;

/**
 * @author Guillaume GAUJAC
 *
 */
public abstract class Structure extends CardClass {

	private int strength;

	/**
	 * Constructor of the structure.
	 * 
	 * @param name
	 *            Name of the card
	 * @param mana
	 *            Magic points.
	 * @param health
	 *            Health points.
	 */
	public Structure(String name, int strength, int mana) {
		super(name, mana);
		this.strength = Objects.requireNonNull(strength);
	}

	public boolean isDead() {
		return (this.strength <= 0);
	}

	public void outch(int dmg) {
		this.strength -= strength - dmg;
	}

	public int getStrength() {
		return strength;
	}

}