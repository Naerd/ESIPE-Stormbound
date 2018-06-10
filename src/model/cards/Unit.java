package model.cards;

public abstract class Unit extends Structure implements Spell {

	private int strength;
	private final int move;

	public Unit(String name, int strength, int move, int mana) {
		super(name, mana, strength);
		this.strength = strength;
		this.move = move;
	}

	public int getStrength() {
		return this.strength;
	}

	public int getMove() {
		return this.move;
	}

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

	@Override
	public String specificToString() {
		return super.specificToString() + "\n Move :" + getMove() + " ; Strength : " + getStrength();
	}

	@Override
	public abstract void effect(Cards c1, Cards c2);

}
