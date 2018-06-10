package model.cards;

public abstract class Unit extends Structure implements Spell {

	private final int move;

	public Unit(String name, int strength, int move, int mana) {
		super(name, strength, mana);
		this.move = move;
	}

	public int getMove() {
		return this.move;
	}

	// public String toString() {
	// return this.player.getName() + ":(" + this.strength + ";" + this.move + ";" +
	// this.mana + ")";
	// }

	public String specificToString() {
		return "Name : " + super.getName() + " ; Mana : " + super.getMana() + " ; Move :" + getMove() + " ; Strength : "
				+ super.getStrength();
	}

	@Override
	public abstract void effect(Cards c1, Cards c2);

	@Override
	public String toString() {
		return specificToString();
	}

	// @Override
	// public abstract void effect(Cards c1, Cards c2);

}
