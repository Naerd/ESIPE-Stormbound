package model;

import model.cards.Cards;
import model.cards.Unit;

/**
 * 
 * Base Ã  dÃ©truire.
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

	/**
	 * @param square
	 * 
	 *            Permet de déplacer une unité de 1 case vers l'avant, en fonction
	 *            du joueur sur la case passée en paramètre.
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
	 *            Permet de placer une unité que l'on vient de jouer et de lui faire
	 *            faire ses mouvements initiaux et/ou son action. Ainsi: Tant qu'il
	 *            n'y a pas d'ennemi et qu'on a encore du déplacement, on déplace
	 *            l'unité de 1 vers la base ennemie. Si, pendant cette boucle, on
	 *            rencontre un ennemi, on fait attaquer cette carte et on finit
	 *            l'action.
	 */
	public void setUnit(Square square, Cards card, Player player) {
		int moveUnit = 0;
		int current_x = square.getX();
		int current_y = square.getY();
		Square current_square = this.getSquare(current_x, current_y);
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
			square = this.getSquare(current_x, current_y);
			moveUnit--;
		}
	}

	/**
	 * @param player
	 *            Fonction qui s'effectue à chaque changement de tour en prenant en
	 *            compte le joueur courant. On parcourt le tableau et, pour chaque
	 *            unité appartenant au joueur, on la déplace de 1 ou on la fait
	 *            attaquer s'il y a un ennemi en face d'elle.
	 */

	/*
	 * public void generalMove(Player player) { Square current_square = null; int
	 * dmg = 0; int[][] tabAlreadyMoved = initTabMoves(5, 4); for (int i = 0; i < 5;
	 * i++) { for (int j = 0; j < 4; j++) { current_square = this.getSquare(i, j);
	 * if (current_square.getCard() != null) { if (tabAlreadyMoved[i][j] != 1) { if
	 * (current_square.getPlayer() == player) { if (player.equals(Player.PLAYER1) &&
	 * current_square.getX() == 0) { dmg = ((Unit)
	 * current_square.getCard()).getStrength(); this.p2.outch(dmg);
	 * current_square.setCard(null); current_square.setPlayer(null); } else if
	 * (player.equals(Player.PLAYER2) && current_square.getX() == 4) { dmg = ((Unit)
	 * current_square.getCard()).getStrength(); this.p1.outch(dmg);
	 * current_square.setCard(null); current_square.setPlayer(null); } else if
	 * (isEnnemyFront(i, j, player)) { if (player.equals(Player.PLAYER1)) {
	 * this.attack(current_square, this.getSquare(i - 1, j), player); if
	 * (this.getSquare(i - 1, j).getPlayer().equals(player)) { tabAlreadyMoved[i -
	 * 1][j] = 1; } } else { this.attack(current_square, this.getSquare(i + 1, j),
	 * player); if (this.getSquare(i + 1, j).getPlayer().equals(player)) {
	 * tabAlreadyMoved[i + 1][j] = 1; } }
	 * 
	 * } else { if (player.equals(Player.PLAYER2)) { tabAlreadyMoved[i + 1][j] = 1;
	 * } move(current_square); } } } } } } }
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
	 * A chaque tour, on met à jour la ligne de front, qui permet de savoir où l'on
	 * peut jouer nos unités. Si nous avons une unité positionnée à 1 ligne de la
	 * base ennemie, on pourra placer une autre unité juste avant cette ligne, c'est
	 * à dire sur nos 4 premières lignes.
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
	 *            Fonction d'attaque, en action lorsqu'il y a un ennemi et qu'on
	 *            peut l'attaquer. 3 cas sont disponibles ici: - L'attaquant et le
	 *            défenseur ont les meme points de force, ils s'entretuent donc et
	 *            les deux unités disparaissent du plateau. - L'attaquant est plus
	 *            fort que le défenseur, auquel cas on retranche les points de vie
	 *            du défenseur a l'attaquant, celui-ci prend la place du défenseur,
	 *            et le défenseur est anéanti du plateau. - L'attaquant est plus
	 *            faible que le défenseur: ici, l'attaquand va au suicide et est
	 *            anéanti mais inflige des dégats au défenseur. Le défenseur ne
	 *            prend pas la place de l'attaquant
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
	 * @return
	 * 
	 * 		Fonction intermédiaire, initialise le tableau pour savoir si on a
	 *         déjà déplacé une unité, dans la fonction generalMove()
	 */
	public int[][] initTabMoves(int x, int y) {
		int[][] tab = new int[x][y];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				tab[i][j] = 0;
			}
		}
		return tab;
	}

	/**
	 * @param x
	 * @param y
	 * @param p
	 * @return
	 * 
	 * 		Fonction intermédiaire, renvoyant vrai si un ennemi est détecté à
	 *         gauche (en fonction du joueur)
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
	 * 		Fonction intermédiaire, renvoyant vrai si un ennemi est détecté à
	 *         droite (en fonction du joueur)
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
	 * 		Fonction intermédiaire, renvoyant vrai si un ennemi est détecté en
	 *         face (en fonction du joueur)
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
