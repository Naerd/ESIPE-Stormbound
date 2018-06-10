package model;

import model.cards.Unit;

/**
 * 
 * Base à détruire.
 * 
 * @author Axel ROSTAGNY
 * @author Guillaume GAUJAC
 *
 */
public class Board {
	private Square[][] board;
	private Player p1;
	private Player p2;

	public Board() {
		this.p1 = Player.PLAYER1;
		this.p2 = Player.PLAYER2;
		this.board = new Square[5][4];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				board[i][j] = new Square(i, j);
			}
		}
	}

	// /**
	// * Return hp of the player
	// *
	// * @param player
	// * @return
	// */
	// public Player getPlayer(int player) {
	// if (player > 2)
	// throw new ArrayIndexOutOfBoundsException(player + " is out of bound");
	// if (player < 1) {
	// throw new IllegalArgumentException(player + " is to short");
	// }
	// if (player == 1)
	// return this.p1;
	// else
	// return this.p2;
	// }

	/**
	 * @param x
	 * @param y
	 * @param card
	 * @param p
	 * @return
	 */
	// public boolean deplacement(Square init, Square obj, Player p) {
	// if (this.board[x][y].getCard() != null) {
	// System.out.println("Cette case est deja utilisee !");
	// return false;
	// } else if (!isEnnemyFront(x, y, p) && !isEnnemyLeft(x, y, p) &&
	// !isEnnemyRight(x, y, p)) {
	// this.board[x][y].addCard(card);
	// return true;
	// } else {
	// if (p == Player.PLAYER1 && (y == 0 || y == 1)) {
	// if (isEnnemyRight(x, y, p)) {
	//
	// }
	// }
	// this.board[x][y].addCard(card);
	// return true;

	// if (this.getSquare(obj.getX(), obj.getY()))

	// if (this.getSquare(obj.getX(), obj.getY()).getPlayer() == p) {
	// System.out.println("Vous êtes déjà là");
	// }

	// }

	public void attack(Unit c1, Unit c2) {
		/* Tester si trop éloigné */
		// if(c2.getStrength())
	}

	/**
	 * Pour l'affichage sur le plateau
	 * 
	 * @return
	 */
	public Player getP1() {
		return p1;
	}

	/**
	 * @return
	 */
	public Player getP2() {
		return p2;
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isInBoard(int x, int y) {
		/** TODO : le faire avec les excpetions */
		if (x >= 0 && x < 5) {
			if (y >= 0 && y < 4)
				return true;
			else
				return false;
		} else
			return false;
	}

	public Square getSquare(int x, int y) {
		return board[x][y];
	}

	public Player getPlayerOnSquare(Square square) {
		return board[square.getX()][square.getY()].getPlayer();
	}

	/**
	 * @param x
	 * @param y
	 * @param p
	 * @return
	 */
	public boolean isEnnemyLeft(int x, int y, Player p) {
		if (p == Player.PLAYER1) {
			if (isInBoard(x, y - 1) && this.board[x][y - 1].getCard() != null) {
				return true;
			} else
				return false;
		} else {
			if (isInBoard(x, y + 1) && this.board[x][y + 1].getCard() != null) {
				return true;
			} else
				return false;
		}
	}

	/**
	 * @param x
	 * @param y
	 * @param p
	 * @return
	 */
	public boolean isEnnemyRight(int x, int y, Player p) {
		if (p == Player.PLAYER1) {
			if (isInBoard(x, y + 1) && this.board[x][y + 1].getCard() != null) {
				return true;
			} else
				return false;
		} else {
			if (isInBoard(x, y - 1) && this.board[x][y - 1].getCard() != null) {
				return true;
			} else
				return false;
		}
	}

	/**
	 * @param x
	 * @param y
	 * @param p
	 * @return
	 */
	public boolean isEnnemyFront(int x, int y, Player p) {
		if (p == p.PLAYER1) {
			if (isInBoard(x - 1, y) && this.board[x - 1][y].getCard() != null) {
				return true;
			} else
				return false;
		} else {
			if (isInBoard(x + 1, y) && this.board[x + 1][y].getCard() != null) {
				return true;
			} else
				return false;
		}
	}

	// public boolean isInBase(int x, int y);

}
