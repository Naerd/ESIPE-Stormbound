package model;

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

	public void move(Square square) {
		Player playerOnSquare = square.getPlayer();
		int x_square = square.getX();
		int y_square = square.getY();
		if (playerOnSquare.equals(Player.PLAYER1)) {
			this.getSquare((x_square - 1), y_square).setCard(square.getCard());
			this.getSquare((x_square - 1), y_square).setPlayer(playerOnSquare);
		} else {
			this.getSquare((x_square + 1), y_square).setCard(square.getCard());
			this.getSquare((x_square + 1), y_square).setPlayer(playerOnSquare);
		}
		this.getSquare(x_square, y_square).setPlayer(null);
		this.getSquare(x_square, y_square).setCard(null);
	}

	public void setUnit(Square square, Cards card, Player player) {
		int moveUnit = 0;
		int current_x = square.getX();
		int current_y = square.getY();
		Square current_square = square;
		if (card instanceof Unit) {
			moveUnit = ((Unit) card).getMove();
		}
		if (square.getCard() != null) {
			throw new IllegalStateException("There is already a card in there!");
		} else if (!this.isInBoard(current_x, current_y)) {
			throw new IllegalArgumentException("This action canno't be achieved, wrong coordinates");
		} else {
			this.getSquare(current_x, current_y).setCard(card);
			this.getSquare(current_x, current_y).setPlayer(player);
		}
		while (moveUnit != 0) {
			if (!isEnnemyFront(current_x, current_y, player) && !isEnnemyLeft(current_x, current_y, player)
					&& !isEnnemyRight(current_x, current_y, player)) {
				move(this.getSquare(current_x, current_y));
			} else if (isEnnemyFront(current_x, current_y, player)) {
				if (player == Player.PLAYER1) {
					// this.attack(FrontOfP1);
				} else {
					// this.attack(FrontOfP2);
				}
				return;
			} else if (isEnnemyLeft(current_x, current_y, player) && isEnnemyRight(current_x, current_y, player)) {
				if (player == Player.PLAYER1) {
					if (current_y == 1) {
						// this.attack(RightOfP1);
					} else {
						// this.attack(LeftOfP1);
					}
					return;
				} else {
					if (current_y == 1) {
						// this.attack(LeftOfP2);
					} else {
						// this.attack(RightOfP2);
					}
					return;
				}
			} else if (isEnnemyLeft(current_x, current_y, player)) {
				if (player == Player.PLAYER1) {
					// this.attack(LeftOfP1);
				} else {
					// this.attack(LeftOfP2);
				}
				return;
			} else if (isEnnemyRight(current_x, current_y, player)) {
				if (player == Player.PLAYER1) {
					// this.attack(RightOfP1);
				} else {
					// this.attack(RightOfP2);
				}
				return;
			}
			if (player == Player.PLAYER1) {
				current_x--;
			} else {
				current_x++;
			}
			square = this.getSquare(current_x, current_y);
			moveUnit--;
		}
	}

	public void generalMove(Player player) {
		Square current_square = null;
		int dmg = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				current_square = this.getSquare(i, j);
				if (current_square.getCard() != null) {
					if (current_square.getPlayer() == player) {
						if (player.equals(Player.PLAYER1) && current_square.getX() == 0) {
							dmg = ((Unit) current_square.getCard()).getStrength();
							this.p2.outch(dmg);
							current_square.setCard(null);
							current_square.setPlayer(null);
						} else if (player.equals(Player.PLAYER2) && current_square.getX() == 4) {
							dmg = ((Unit) current_square.getCard()).getStrength();
							this.p1.outch(dmg);
							current_square.setCard(null);
							current_square.setPlayer(null);
						} else if (isEnnemyFront(i, j, player)) {
							// this.attack(inFrontOf)
						} else {
							move(current_square);
						}
					}
				}
			}
		}
	}

	public void updateFrontLine() {
		setFrontLineP1(4);
		setFrontLineP2(0);
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

	public void attack(Square square, Player player) {

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

	public void setFrontLineP1(int fp) {
		this.frontLineP1 = fp;
	}

	public void setFrontLineP2(int fp) {
		this.frontLineP2 = fp;
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
}
