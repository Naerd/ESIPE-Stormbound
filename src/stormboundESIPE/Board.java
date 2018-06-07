package stormboundESIPE;

public class Board {
	private Square[][] board;
	private Base j1;
	private Base j2;
	
	public Board(){
		this.j1 = new Base();
		this.j2 = new Base();
		this.board = new Square[7][4];
		for(int i = 0 ; i < 7 ; i++){
			for(int j = 0 ; j < 4; j++) {
				board[i][j] = new Square(i,j);
			}
		}
	}
	
	public Base getBase(int player) {
		if(player < 1 && player > 2 )
			throw new IllegalArgumentException("There is only two players : 1 and 2");
		if(player == 1)
			return this.j1;
		else
			return this.j2;
	}
	
	public void display(){
		System.out.println(this.j2.toString());
		for(int i = 1 ; i < 6 ; i++){
			System.out.print("|");
			System.out.println("___________________________________________________________");
			for(int j = 0 ; j < 4; j++) {
				System.out.print(this.board[i][j].toString());
			}
			System.out.println("|"+i);
		}
		System.out.println(this.j1.toString());
	}
	
	public boolean setUnit(int x, int y, Card card, Player p) {
		if(this.board[x][y].getCard() != null) {
			System.out.println("Cette case est déjà utilisée !");
			return false;
		}
		else if(!isEnnemyFront(x,y,p) && !isEnnemyLeft(x,y,p) && !isEnnemyRight(x,y,p)) {
			this.board[x][y].addCard(card);
			return true;
		}
		else {
			if(p.getName() == "A" && (y == 0 || y == 1)){
				if(isEnnemyRight(x,y,p)){
					
				}
				
			}
				
				
				
				
			this.board[x][y].addCard(card);
			return true;
		}
	}
	
	public boolean isInBoard(int x, int y) {
		if(x >= 0 && x < 7) {
			if(y >= 0 && y < 4)
				return true;
			else 
				return false;
		}
		else 
			return false;
	}
	
	public boolean isEnnemyLeft(int x, int y, Player p) {
		if(p.getName() == "A") {
			if (isInBoard(x,y-1) && this.board[x][y-1].getCard() != null) {
				return true;
			}
			else 
				return false;
		}
		else {
			if (isInBoard(x,y+1) && this.board[x][y+1].getCard() != null) {
				return true;
			}
			else 
				return false;
		}
	}
	
	public boolean isEnnemyRight(int x, int y, Player p) {
		if(p.getName() == "A") {
			if (isInBoard(x,y+1) && this.board[x][y+1].getCard() != null) {
				return true;
			}
			else 
				return false;
		}
		else {
			if (isInBoard(x,y-1) && this.board[x][y-1].getCard() != null) {
				return true;
			}
			else 
				return false;
		}
	}
	
	public boolean isEnnemyFront(int x, int y, Player p) {
		if(p.getName() == "A") {
			if (isInBoard(x-1,y) && this.board[x-1][y].getCard() != null) {
				return true;
			}
			else 
				return false;
		}
		else {
			if (isInBoard(x+1,y) && this.board[x+1][y].getCard() != null) {
				return true;
			}
			else 
				return false;
		}
	}
	
	//public boolean isInBase(int x, int y);
	
	
}

