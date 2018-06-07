package stormboundESIPE;

public class Square {
	private final int x;
	private final int y;
	private Card card;
	
	public Square(int x, int y) {
		this.x = x;
		this.y = y;
		this.card = null;
	}
	
	public String toString(){
		if(this.card == null){
			return "|      o      |";
		}
		else {
			return "|  "+this.card.toString()+"  |";
		}
	}
	
	public Card getCard() {
		return this.card;
	}
	
	public void addCard(Card card) {
		this.card = card;
	}

}
