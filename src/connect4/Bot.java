package connect4;

public class Bot extends Player {

	public Bot(){
		super("Bot", 2);
	}
	
	public void randTurn() {

		String input;
		
		do {
			input = String.valueOf((int)(Math.random()*5 + 1));	
		}while(super.isValidMove(input) == -1);
		
		int col = Integer.parseInt(input);
		
		for(int r=Board.getBoard()[0].length; r>-1; r--) {
			if(Board.getBoard()[r][col]==0) {
				Board.setBoard(r, col, 2);
				break;
			}
		}
		
	}// end of doTurn()

}// end of class
