package stormboundESIPE;

import model.Board;
import model.Card;
import model.Player;

public class Main {

	public static void main(String[] args) {
		Player p1 = Player.PLAYER1;
		Player p2 = Player.PLAYER2;
		Player courant = p1;

		Board board = new Board();

		boolean isOver = false;
		boolean tour = true;
		boolean valideDeplacement;

		while (!isOver) {
			if (tour) {
				/* A chaque tour augmenté le mana */
				courant = p1;
			} else {
				courant = p2;
			}
			valideDeplacement = false;
		}

		board.display();
		Card c2 = Card.A;
		board.setUnit(4, 2, c2, p2);
		board.getBase(1).outch(3);
		board.display();
		Card c = Card.B;
		board.setUnit(5, 3, c, p1);
		board.display();

	}

}
