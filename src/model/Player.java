package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

import model.cards.Cards;
import model.cards.standard.A;
import model.cards.standard.B;
import model.cards.standard.C;
import model.cards.standard.D;
import model.cards.standard.E;
import model.cards.standard.F;
import model.cards.standard.G;
import model.cards.standard.H;
import model.cards.standard.I;
import stormboundESIPE.Main;

public enum Player {

	PLAYER1("A"), PLAYER2("B");

	private final String name;

	// private final String color;

	private ArrayList<Cards> deck;
	private ArrayList<Cards> hand;
	private int health;
	private int mana;

	Player(String name) {
		this.name = name;
		this.deck = initDeck();
		this.hand = initHand();
		this.health = 10;
		this.mana = 3;
	}

	private ArrayList<Cards> initDeck() {
		ArrayList<Cards> deck = new ArrayList<Cards>();
		String nameFile;
		BufferedReader br = null;
		if (this.name == "A") {
			nameFile = "deckP1.txt";
		} else {
			nameFile = "deckP2.txt";
		}
		System.out.println(nameFile);
		try {
			br = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("deckP1.txt"), "UTF-8"));
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				Cards card;
				switch (currentLine) {
				case "A":
					card = new A();
					deck.add(card);
					break;
				case "B":
					card = new B();
					deck.add(card);
					break;
				case "C":
					card = new C();
					deck.add(card);
					break;
				case "D":
					card = new D();
					deck.add(card);
					break;
				case "E":
					card = new E();
					deck.add(card);
					break;
				case "F":
					card = new F();
					deck.add(card);
					break;
				case "G":
					card = new G();
					deck.add(card);
					break;
				case "H":
					card = new H();
					deck.add(card);
					break;
				case "I":
					card = new I();
					deck.add(card);
					break;
				default:
					/* Not a good value */
					break;
				}
			}
		} catch (UnsupportedEncodingException e) {
			/* Decode pas l'UTF-8 */
			e.printStackTrace();
			deck = null;
		} catch (IOException e) {
			/* Ne lit pas la ligne */
			e.printStackTrace();
			deck = null;
		}
		return deck;
	}

	public ArrayList<Cards> initHand() {
		ArrayList<Cards> hand = new ArrayList<Cards>();
		Random rand = new Random();
		for (int numberCard = 0; numberCard < 4; numberCard++) {
			int indexDeck = rand.nextInt(this.deck.size());
			hand.add(deck.get(indexDeck));
			deck.remove(indexDeck);
		}
		return hand;
	};

	public String getName() {
		return this.name;
	}

	public int getMana() {
		return mana;
	}

	public ArrayList<Cards> getDeck() {
		return deck;
	}

	public int getHealth() {
		return health;
	}

	@Override
	public String toString() {
		return getName().toString() + " (HP : " + this.getHealth() + " ; Mana : " + this.getMana() + ");";
	}

	public void outch(int damage) {
		health -= damage;
	}

	public ArrayList<Cards> getHand() {
		return hand;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public void exchange(Cards cardHand) {
		this.deck.add(cardHand);
		this.hand.remove(cardHand);
		drawACard();
	}

	public void drawACard() {
		if (!this.getDeck().isEmpty()) {
			int indexDraw = new Random().nextInt(this.getDeck().size());
			Cards cardDraw = this.getDeck().get(indexDraw);
			/* Piochement de la carte */
			this.getHand().add(cardDraw);
			/* Suppression dans le deck */
			this.getDeck().remove(cardDraw);
		}
	}

}
