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
			this.view.window(this.board);
			int choice = this.view.choix(courant);
			/* TODO : Montrer ma main */
			switch (choice) {
			case 1:
				/* TODO : Exchange or place */
				try {
					/* TODO : Avant de faire ce choix : afficher les cartes et les cases libres */
					Cards cardSelected = view.selectCardOnHand(courant);
					Square squareSelected = view.selectSquareOnBoard(courant, board);
					placeCardOnBoard(courant, squareSelected, cardSelected);
					/* TODO : A voir si je pioche une carte seulement si je joue */
					courant.drawACard();
				} catch (IllegalStateException illegalStateException) {
					break;
				}
				break;
			case 2:
				Cards cardExchanged = view.exchange(courant);
				courant.exchange(cardExchanged);
				break;
			default:
				/* TODO : Peut-on passer notre tour */
				break;
			}
			if (courant.getMana() < 10)
				courant.setMana(courant.getMana() + 1);
			// deplacement(board);
			if (courant == p1) {
				/* A chaque tour augmenté le mana */
				courant = p2;
			} else {
				courant = p1;
			}
		}

	}

	/**
	 * @param player
	 *            The current player who want to place a card.
	 * @param square
	 *            The case where we want the player want to place his card. We
	 *            change the value of the current player in specification of the
	 *            square.
	 * @param card
	 *            The card we want to place.
	 */
	public void placeCardOnBoard(Player player, Square square, Cards card) {
		// if (p == board.getSquare(s1.getX(), s1.getY()).getPlayer()) {
		// return board.setUnit(,);
		// }
		this.board.getSquare(square.getX(), square.getY()).setCard(card);
		this.board.getSquare(square.getX(), square.getY()).setPlayer(player);
		player.getHand().remove(player.getHand().indexOf(card));
	}

}