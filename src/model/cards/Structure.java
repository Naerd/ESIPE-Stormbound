package model.cards;

import java.util.Objects;

public abstract class Structure implements Cards {

	private final String name;
	private final int mana;
	private int health;

	public Structure(String name, int mana, int health) {
		this.mana = Objects.requireNonNull(mana);
		this.name = Objects.requireNonNull(name);
		this.health = health;

	}

	@Override
	public int getMana() {
		return 0;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return specificToString();
	}

	public String specificToString() {
		return "Name :" + getName() + " ; Mana : " + getMana();
	}

}
