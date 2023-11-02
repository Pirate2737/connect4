package connect4;

public class Bot extends Player {
	
	final int PIECE = 2; // hopefully temporary
	static int[][] sandbox = Board.getBoard();

	public Bot(){
		super("Bot", 2);
	}
	
	public void doTurn(int turns) {
		sandbox = Board.getBoard();
		
		for(int r=sandbox.length-1; r>-1; r--) {
			for(int c=0; c<sandbox[0].length; c++) {
				int temp = sandbox[r][c];
				sandbox[r][c] = PIECE;
				if(Board.checkOver(sandbox, 4)==PIECE) {
					Board.setBoard(r, c, PIECE);
					Board.setOver(false);
					return;
				}
				sandbox[r][c] = temp;
			}
		}
		
		if(((int)(Math.random()*2))==1) {
			randTurn();
		}else {
			// turn one claim middle
			if(turns==1) {
				Board.setBoard(0, 3, PIECE);
				return;
			}
			for(int r=sandbox.length-1; r>-1; r--) {
				for(int c=0; c<sandbox[0].length; c++) {
					int temp = sandbox[r][c];
					sandbox[r][c] = PIECE-1;
					if(Board.checkOver(sandbox, 4)==PIECE-1) {
						Board.setBoard(r, c, PIECE);
						Board.setOver(false);
						return;
					}
					sandbox[r][c] = temp;
				}
			}
			
			randTurn();
			
			
		}
	}
	
	public void randTurn() {
		String input;
		
		do {
			input = String.valueOf((int)(Math.random()*6 + 1));	
		} while(super.isValidMove(input) == -1);
		
		int col = Integer.parseInt(input);
		
		for(int r=Board.getBoard().length-1; r>-1; r--) {
			if(Board.getBoard()[r][col]==0) {
				Board.setBoard(r, col, PIECE);
				break;
			}
		}
		
	}// end of randTurn()

}// end of class
