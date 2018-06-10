package model.cards.spell;

import model.cards.Cards;
import model.cards.Spell;

public class PotionOfGrowth implements Spell {

	private final String name;
	private final int mana;

	public PotionOfGrowth(String name, int mana) {
		this.name = "Potion of Growth";
		this.mana = 4;
	}

	@Override
	public void effect(Cards c1, Cards c2) {
		// TODO Auto-generated method stub

	}

}
