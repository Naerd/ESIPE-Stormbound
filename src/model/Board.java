package model;

import java.util.ArrayList;

import model.cards.Cards;
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

	/* TODO : A mettre dans player */
	private int frontLineP1, frontLineP2;

	public Board() {
		this.p1 = Player.PLAYER1;
		this.p2 = Player.PLAYER2;
		this.board = new Square[5][4];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				board[i][j] = new Square(i, j);
			}
		}
		this.frontLineP1 = 4;
		this.frontLineP2 = 0;

	}

	/* TODO : Monter le test pour voir quel joueur est devant (factoriser) */
	public void deplacement(Square square) {
		// Unit cardOnSquare = (Unit) square.getCard();
		Player playerOnSquare = square.getPlayer();
		int directionOnX = square.getX();
		if (playerOnSquare != null) {
			if (playerOnSquare.equals(Player.PLAYER1)) {
				if (directionOnX - 1 >= 0) {
					directionOnX = square.getX() - 1;
				}
			} else {
				if (directionOnX + 1 < 5) {
					directionOnX = square.getX() + 1;
				}
			}
			if (getSquare(directionOnX, square.getY()).getPlayer() != null) {
				if (getSquare(directionOnX, square.getY()).getPlayer() == playerOnSquare) {
					return;
				} else {
					/* TODO : Attaque */

				}
			} else {
				getSquare(directionOnX, square.getY()).setCard(square.getCard());
				getSquare(directionOnX, square.getY()).setPlayer(playerOnSquare);
				getSquare(square.getX(), square.getY()).setPlayer(null);
				getSquare(square.getX(), square.getY()).setCard(null);
			}
		}
	}

	/**
	 * @return
	 */
	public void deplacementEachRound() {
		ArrayList<Square> alreadyDeplace = new ArrayList<Square>();
		/* Joueur 2 */
		for (Square[] squares : board) {
			for (Square square : squares) {
				System.out.println("Case [" + square.getX() + "," + square.getY());
				Player playerOnCard = square.getPlayer();
				/* Ce premier test pour ne pas planter */
				if (playerOnCard != null) {
					/* TODO : Gerer les "unit" et les "structures" */
					if (playerOnCard.equals(Player.PLAYER2)) {
						Cards cardTested = square.getCard();
						if (!(alreadyDeplace.contains(square)) && (cardTested instanceof Unit)) {
							while (((Unit) cardTested).getMove() > -1) {
								int movePoint = ((Unit) cardTested).getMove();
								deplacement(square);
								((Unit) cardTested).setMove(movePoint--);
							}
							((Unit) cardTested).setMove(0);
							alreadyDeplace.add(new Square(square.getX() + 1, square.getY()));
						}
					}
				}
			}
		}
		/* Joueur 1 */
		for (int i = 4; i == 0; i--) {
			for (int j = 0; j < 4; j++) {
				System.out.println("Case [" + getSquare(i, j).getX() + "," + getSquare(i, j).getY());
				Player playerOnCard = getSquare(i, j).getPlayer();
				/* Ce premier test pour ne pas planter */
				if (playerOnCard != null) {
					/* TODO : Gerer les "unit" et les "structures" */
					if (playerOnCard.equals(Player.PLAYER1)) {
						Cards cardTested = getSquare(i, j).getCard();
						if (!(alreadyDeplace.contains(getSquare(i, j))) && (cardTested instanceof Unit)) {
							while (((Unit) cardTested).getMove() > 0) {
								int movePoint = ((Unit) cardTested).getMove();
								deplacement(getSquare(i, j));
								((Unit) cardTested).setMove(movePoint--);
							}
							((Unit) cardTested).setMove(0);

							alreadyDeplace.add(new Square(getSquare(i, j).getX() - 1, getSquare(i, j).getY()));
						}
					}
				}
			}
		}
	}

	public void updateFrontLine() {
		for (Square[] squares : board) {
			for (Square square : squares) {
				Player playerOnSquare = square.getPlayer();
				int line = square.getX();
				if (playerOnSquare != null) {
					if (playerOnSquare.equals(Player.PLAYER1)) {
						if (line < this.frontLineP1) {
							this.frontLineP1 = line;
						}
					} else {
						if (line > this.frontLineP2) {
							this.frontLineP2 = line;
						}
					}
				}
			}
		}
	}

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

	public int getFrontLineP1() {
		return this.frontLineP1;
	}

	public int getFrontLineP2() {
		return this.frontLineP2;
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
