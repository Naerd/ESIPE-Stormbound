package stormboundESIPE;

public class Main {
	
	public static void main(String[] args){
		Player p1 = new Player("A");
		Player p2 = new Player("B");
		Board board = new Board();
		board.display();
		Card c2 = new Card("A",2,2,4,p2);
		board.setUnit(4, 2, c2,p2);
		board.getBase(1).outch(3);
		board.display();
		Card c = new Card("B",5,1,4,p1);
		board.setUnit(5, 3, c,p1);
		board.display();
		
		
		}

}
