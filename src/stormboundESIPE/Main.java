package stormboundESIPE;

import javax.smartcardio.Card;

import model.Board;
import model.Player;
import model.cards.Cards;
import view.View;

public class Main {

	public static void main(String[] args) {
		View vue = new View();

		Player p1 = Player.PLAYER1;
		Player p2 = Player.PLAYER2;
		Player courant = p1;

		Board board = new Board();

		boolean isOver = false;
		boolean tour = true;
		boolean valideDeplacement;

		// while (!isOver) {
		// if (tour) {
		// /* A chaque tour augmenté le mana */
		// courant = p1;
		// } else {
		// courant = p2;
		// }
		// valideDeplacement = false;
		// }

		vue.display(board);
		Cards c2 = Card.A;
		board.setUnit(4, 2, c2, p2);
		board.getPlayer(1).outch(3);
		vue.display(board);
		Card c = Card.B;
		board.setUnit(5, 3, c, p1);
		vue.display(board);

	}

}
