package stormboundESIPE;

import model.Board;
import model.Player;
import view.View;

public class Main {

	public static void main(String[] args) {
		View vue = new View();

		Player p1 = Player.PLAYER1;
		Player p2 = Player.PLAYER2;
		Player courant = p1;

		Board board = new Board();

		boolean isOver = false;
		boolean roundP1 = true;
		boolean valideDeplacement;

		while (!isOver) {
			if (courant == p1) {
				/* A chaque tour augmenté le mana */
				valideDeplacement = false;
				courant = p2;
			} else {

				valideDeplacement = false;
				courant = p1;
			}
		}
		vue.display(board);
		board.setUnit(4, 2, c2, p2);
		// board.getPlayer(1).outch(3);
		vue.display(board);
		board.setUnit(5, 3, c, p1);
		vue.display(board);

	}

}
