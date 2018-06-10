package model.cards;

import java.util.Objects;

/**
 * @author Guillaume GAUJAC
 *
 */
public abstract class Structure implements Cards {

	private final String name;
	private final int mana;
	private int health;

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
	public Structure(String name, int mana, int health) {
		this.mana = Objects.requireNonNull(mana);
		this.name = Objects.requireNonNull(name);
		this.health = health;

	}

	@Override
	public int getMana() {
		return 0;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return specificToString();
	}

	@Override
	public String specificToString() {
		return "Name :" + getName() + " ; Mana : " + getMana();
	}

}
