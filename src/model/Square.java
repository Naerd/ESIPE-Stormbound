package model;

import model.cards.Cards;

public class Square {
	private final int x;
	private final int y;
	private Cards card;

	public Square(int x, int y) {
		this.x = x;
		this.y = y;
		this.card = null;
	}

	public String toString() {
		if (this.card == null) {
			return "|      o      |";
		} else {
			return "|  " + this.card.toString() + "  |";
		}
	}

	public Cards getCard() {
		return this.card;
	}

	public void addCard(Cards card) {
		this.card = card;
	}

}
