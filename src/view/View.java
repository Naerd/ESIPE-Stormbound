package view;

import model.Board;
import model.Player;

public class View {

	public void display(Board b) {
		System.out.println(b.getP2().toString());
		for (int i = 1; i < 6; i++) {
			System.out.print("|");
			System.out.println("___________________________________________________________");
			for (int j = 0; j < 4; j++) {
				// TODO : M'applique directement le toString
				System.out.print(b.getSquare(i, j));
			}
			System.out.println("|" + i);
		}
		System.out.println(b.getP1().toString());
	}

	/**
	 * Donne une couleur à notre affichage.
	 * 
	 * @param s
	 *            L'affichage que l'on souhaite.
	 * @param p
	 *            Le joueur que l'on affichera.
	 */
	public void couleur(String s, Player p) {
		if (p == Player.PLAYER1)
			System.out.print((char) 27 + "[31m" + s + " " + (char) 27 + "[0m");
		if (p == Player.PLAYER2)
			System.out.print((char) 27 + "[34m" + s + " " + (char) 27 + "[0m");
	}
}