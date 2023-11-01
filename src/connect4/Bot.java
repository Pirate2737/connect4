package connect4;

public class Bot extends Player {
	
	final int PIECE = 2; // hopefully temporary

	public Bot(){
		super("Bot", 2);
	}
	
	public void doTurn(int turns) {
		if(((int)(Math.random()*1))==1) {
			randTurn();
		}else {
			// turn one claim middle
			if(turns==1) {
				Board.setBoard(0, 3, PIECE);
			}
			else {randTurn();}
			
			
		}
	}
	
	public void randTurn() {
		String input;
		
		do {
			input = String.valueOf((int)(Math.random()*6 + 1));	
		}while(super.isValidMove(input) == -1);
		
		int col = Integer.parseInt(input);
		
		for(int r=Board.getBoard().length-1; r>-1; r--) {
			if(Board.getBoard()[r][col]==0) {
				Board.setBoard(r, col, PIECE);
				break;
			}
		}
		
	}// end of randTurn()

}// end of class
