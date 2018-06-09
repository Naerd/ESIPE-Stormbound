package controller;

import model.Board;
import model.Player;
import model.Square;
import view.View;

public class Controller {

	private Board board;
	private View view;

	public Controller(Board board, View view) {
		this.board = board;
		this.view = view;
	}

	public boolean deplacementCard(Square s1, Square s2, Player p) {
		// si même joueur alors ok
		if (p == board.getSquare(s1.getX(), s1.getY()).getPlayer()) {
			return board.setUnit(,);
		}
		return false;
	}
}