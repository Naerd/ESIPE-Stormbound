package model.cards.spell;

import model.cards.Cards;
import model.cards.Spell;

public class Execution implements Spell {

	private String name;
	private int mana;

	public Execution() {
		this.name = "Execution";
		this.mana = 3;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void effect(Cards c1, Cards c2) {
		// TODO Auto-generated method stub

	}
}
