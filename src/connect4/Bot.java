package connect4;

public class Bot extends Player {

	public Bot(){
		super("Bot", 2);
	}
	
	public void randTurn() {

		String input = String.valueOf((Math.random()*1 + 1));

		// checks if there's a spot available (isValidMove)
		while(super.isValidMove(input) != -1) {
			Integer friend = (int)Math.random()*1 + 1;
			input = String.valueOf(friend);
		}
		
		int col = Integer.parseInt(input);
		
		for(int r=Board.getBoard()[0].length; r>-1; r--) {
			if(Board.getBoard()[r][col]==0) {
				Board.setBoard(r, col, 2);
				break;
			}
		}
		
	}// end of doTurn()
	
	public boolean isPlayer() {
		return false;
	}

}// end of class
