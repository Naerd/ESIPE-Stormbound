package model;

import model.cards.Cards;
import model.cards.Unit;

/**
 * 
 * 
 * @author Axel ROSTAGNY
 * @author Guillaume GAUJAC
 *
 */
public class Board {
	private Square[][] board;
	private Player p1;
	private Player p2;
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

	/**
	 * @param square
	 * 
	 *            Move a Unit of 1 square towards ennemy base, depends on the player
	 *            in parameter.
	 */
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

	/**
	 * @param square
	 * @param card
	 * @param player
	 * 
	 * 
	 *            Placing unit for the first time in board and doing its movement
	 *            and actions. While there is no ennemy in front and it has still
	 *            movement points -> move of 1 square toward ennemy base If there is
	 *            an ennemy : attack it and finish the action of setting unit/
	 */
	public void setUnit(Square square, Cards card, Player player) {
		int moveUnit = 0;
		int current_x = square.getX();
		int current_y = square.getY();
		Square current_square = this.getSquare(current_x, current_y);
		if (card instanceof Unit) {
			moveUnit = ((Unit) card).getMove();
		}
		if (current_square.getCard() != null) {
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
					this.attack(current_square, this.getSquare(current_x - 1, current_y), player);
					// this.attack(FrontOfP1);
				} else {
					// this.attack(FrontOfP2);
					this.attack(current_square, this.getSquare(current_x + 1, current_y), player);
				}
				return;
			} else if (isEnnemyLeft(current_x, current_y, player) && isEnnemyRight(current_x, current_y, player)) {
				if (player == Player.PLAYER1) {
					if (current_y == 1) {
						this.attack(current_square, this.getSquare(current_x, current_y + 1), player);
						// this.attack(RightOfP1);
					} else {
						// this.attack(LeftOfP1);
						this.attack(current_square, this.getSquare(current_x, current_y - 1), player);
					}
					return;
				} else {
					if (current_y == 1) {
						// this.attack(LeftOfP2);
						this.attack(current_square, this.getSquare(current_x, current_y + 1), player);
					} else {
						// this.attack(RightOfP2);
						this.attack(current_square, this.getSquare(current_x, current_y - 1), player);
					}
					return;
				}
			} else if (isEnnemyLeft(current_x, current_y, player)) {
				if (player == Player.PLAYER1) {
					// this.attack(LeftOfP1);
					this.attack(current_square, this.getSquare(current_x, current_y - 1), player);
				} else {
					// this.attack(LeftOfP2);
					this.attack(current_square, this.getSquare(current_x, current_y + 1), player);
				}
				return;
			} else if (isEnnemyRight(current_x, current_y, player)) {
				if (player == Player.PLAYER1) {
					// this.attack(RightOfP1);
					this.attack(current_square, this.getSquare(current_x, current_y + 1), player);
				} else {
					// this.attack(RightOfP2);
					this.attack(current_square, this.getSquare(current_x, current_y - 1), player);
				}
				return;
			}
			if (player == Player.PLAYER1) {
				current_x--;
			} else {
				current_x++;
			}
			current_square = this.getSquare(current_x, current_y);
			moveUnit--;
		}
	}

	/**
	 * @param player
	 * 
	 *            Do every automatic movement of all the units of a player. Read
	 *            through a table and for each unit of the player, perform a move.
	 *            If there is an ennemy, action of attacking.
	 * 
	 */

	public void generalMove(Player player) {
		Square current_square = null;
		int dmg = 0;
		if (player.equals(Player.PLAYER1)) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 4; j++) {
					current_square = this.getSquare(i, j);
					if (current_square.getCard() != null) {
						if (current_square.getPlayer() == player) {
							if (current_square.getX() == 0) {
								dmg = ((Unit) current_square.getCard()).getStrength();
								this.p2.outch(dmg);
								current_square.setCard(null);
								current_square.setPlayer(null);
							} else if (isEnnemyFront(i, j, player)) {
								this.attack(current_square, this.getSquare(i - 1, j), player);
								if (this.getSquare(i - 1, j).getPlayer().equals(player)) {
								}
							} else {
								move(current_square);
							}
						}
					}
				}
			}
		} else {
			for (int i = 4; i >= 0; i--) {
				for (int j = 3; j >= 0; j--) {
					current_square = this.getSquare(i, j);
					if (current_square.getCard() != null) {
						if (current_square.getPlayer() == player) {
							if (current_square.getX() == 4) {
								dmg = ((Unit) current_square.getCard()).getStrength();
								this.p1.outch(dmg);
								current_square.setCard(null);
								current_square.setPlayer(null);
							} else if (isEnnemyFront(i, j, player)) {
								this.attack(current_square, this.getSquare(i + 1, j), player);
								if (this.getSquare(i + 1, j).getPlayer().equals(player)) {
								}
							} else {
								move(current_square);
							}
						}
					}
				}
			}

		}
	}

	/**
	 * 
	 * Each turn, update the front line of each side to know where the player can
	 * put his units.
	 */
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

	/**
	 * @param attacker
	 * @param target
	 * @param player
	 * 
	 * 
	 *            Function of attacking, when we have enough movement and there is
	 *            an reachable ennemy. 3 cases : 1) Attacker and defender share the
	 *            same strength --> killing each other and both card are destroyed.
	 *            2) Attacker strength > defender strength --> Destroy the defender,
	 *            damaging itself by defender strength but survive, and take the
	 *            defender's place on board. 3) Attacker strength < defender
	 *            strength --> Attacker is destroyed, damage done to defender, and
	 *            defender don't move.
	 */
	public void attack(Square attacker, Square target, Player player) {
		int dmg = ((Unit) attacker.getCard()).getStrength();
		int lifeOfTarget = ((Unit) target.getCard()).getStrength();
		if (dmg == lifeOfTarget) {
			target.setCard(null);
			target.setPlayer(null);
			attacker.setCard(null);
			attacker.setCard(null);
			return;
		} else if ((lifeOfTarget - dmg) < 0) {
			attacker.getCard().outch(lifeOfTarget);
			target.setCard(attacker.getCard());
			target.setPlayer(player);
			attacker.setCard(null);
			attacker.setCard(null);
			return;
		} else if (lifeOfTarget > dmg) {
			target.getCard().outch(dmg);
			attacker.setCard(null);
			attacker.setCard(null);
			return;
		}

	}

	/**
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
	 * 
	 * 		Check if the coordinates are in the board
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

	/**
	 * @param square
	 * @return
	 * 
	 * 		get the player that is on the square in parameter
	 */
	public Player getPlayerOnSquare(Square square) {
		return board[square.getX()][square.getY()].getPlayer();
	}

	/**
	 * @param x
	 * @param y
	 * @param p
	 * @return
	 * 
	 * 
	 * 		Intermediate function : checked if an ennemy is on the left of the
	 *         square(x,y). Change depends on current_player in parameter.
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
	 * 
	 * 		Intermediate function : checked if an ennemy is on the right of the
	 *         square(x,y). Change depends on current_player in parameter.
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
	 * 
	 * 		Intermediate function : checked if an ennemy is in front of the
	 *         square(x,y). Change depends on current_player in parameter.
	 */
	public boolean isEnnemyFront(int x, int y, Player p) {
		if (p == Player.PLAYER1) {
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
