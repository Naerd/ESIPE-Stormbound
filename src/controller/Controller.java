package controller;

import model.Board;
import model.Player;
import model.Square;
import model.cards.Cards;
import view.View;

public class Controller {

	private Board board;
	private View view;

	/**
	 * @param board
	 *            The model (alias : battlefield)
	 * @param view
	 *            The view (interactions between user and model)
	 */
	public Controller(Board board, View view) {
		this.board = board;
		this.view = view;
	}

	/**
	 * Start a game. Generate 2 player (P2 = AI if specified).
	 */
	public void game() {
		Player p1 = Player.PLAYER1;
		Player p2 = Player.PLAYER2;
		Player courant = p1;
		boolean isOver = false;

		while (!isOver) {
			board.generalMove(courant);
			board.updateFrontLine();
			System.out.println("Front line A = " + board.getFrontLineP1());
			System.out.println("Front line B = " + board.getFrontLineP2());
			boolean round = true;
			this.view.window(this.board);
			int choice = this.view.choix(courant);
			/* TODO : Montrer ma main */
			while (round) {
				switch (choice) {
				case 1:
					/* TODO : Exchange or place */
					try {
						Cards cardSelected = view.selectCardOnHand(courant);
						try {
							Square squareSelected = view.selectSquareOnBoard(courant, board);
							board.setUnit(squareSelected, cardSelected, courant);
							courant.drawACard();
							round = false;
						} catch (IllegalArgumentException illegalArgumentException) {
							System.out.println("Vous n'avez pas de cases disponibles");
						}
						/* TODO : A voir si je pioche une carte seulement si je joue */
					} catch (IllegalStateException illegalStateException) {
						break;
					}
					break;
				case 2:
					Cards cardExchanged = view.exchange(courant);
					courant.exchange(cardExchanged);
					/* TODO : Toujours au tour du courant */
					break;
				default:
					/* TODO : Peut-on passer notre tour */
					round = false;
					break;
				}
				if (courant.getMana() < 10)
					courant.setMana(courant.getMana() + 1);
				if (courant == p1) {
					/* A chaque tour augmenté le mana */
					courant = p2;
				} else {
					courant = p1;
				}
				if (p1.getHealth() <= 0) {
					System.out.println("\n\nPLAYER 2 WON !!!");
					isOver = true;
				}
				if (p2.getHealth() <= 0) {
					System.out.println("\n\nPLAYER 1 WON !!!");
					isOver = true;
				}
			}
		}
	}
}