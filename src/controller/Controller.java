package controller;

import model.Board;
import model.Square;
import model.cards.Cards;
import view.View;

public class Controller {

	private Board board;
	private View view;

	public Controller(Board board, View view) {
		this.board = board;
		this.view = view;
	}

	public void deplacementCard(Square s1, Square s2, Player p1) {
		// si même joueur alors ok
		if (p1 == board.getSquare(s1.x, s1.y).) {
		    return board.deplacerContenusCase(c1, c2);
		}
		return false;
	}

}
