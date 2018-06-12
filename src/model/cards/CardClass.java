package model.cards;

import java.util.Objects;

public abstract class CardClass implements Cards {

	private final int mana;
	private final String name;

	public CardClass(String name, int mana) {
		this.mana = Objects.requireNonNull(mana);
		this.name = Objects.requireNonNull(name);
	}

	/**
	 * Getter for the mana.
	 * 
	 * @return Magic points
	 */
	public int getMana() {
		return mana;
	}

	/**
	 * @return Name of the card
	 */
	public String getName() {
		return name;
	}
}
