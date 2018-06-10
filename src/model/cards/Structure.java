package model.cards;

import java.util.Objects;

/**
 * @author Guillaume GAUJAC
 *
 */
public abstract class Structure implements Cards {

	private final String name;
	private final int mana;
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
		this.mana = Objects.requireNonNull(mana);
		this.name = Objects.requireNonNull(name);
		this.strength = Objects.requireNonNull(strength);
	}

	public boolean isDead() {
		return (this.strength <= 0);
	}

	public void outch(int dmg) {
		this.strength -= strength - dmg;
	}

	@Override
	public int getMana() {
		return this.mana;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public int getStrength() {
		return strength;
	}

}
