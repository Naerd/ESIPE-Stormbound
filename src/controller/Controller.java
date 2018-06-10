package controller;

import model.Board;
import model.Player;
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

	public void game() {
		Player p1 = Player.PLAYER1;
		Player p2 = Player.PLAYER2;

		Player courant = p1;

		boolean isOver = false;
		boolean valideDeplacement;

		while (!isOver) {
			this.view.window(this.board);
			int choice = this.view.choix(courant);
			switch (choice) {
			case 1:
				// Cards cardSelected = view.selectCardOnHand(courant);
				// Square squareSelected = view.selectSquareOnBoard(courant, board);
				// placeCardOnBoard(squareSelected, cardSelected);
				break;
			case 2:
				view.showStats(courant);
				break;
			default:
				return;
			}

			if (courant == p1) {
				/* A chaque tour augmenté le mana */
				courant = p2;
			} else {
				courant = p1;
			}
		}

	}

	public boolean placeCardOnBoard(Square square, Cards card) {
		// // si même joueur alors ok
		// if (p == board.getSquare(s1.getX(), s1.getY()).getPlayer()) {
		// return board.setUnit(,);
		// }
		return false;
	}

}