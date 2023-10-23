package connect4;
public class Tester {
	
	public static int[][] fillArray (int[][] board) {
		for(int r=0; r<board.length; r++) {
			for(int w=0; w<board[0].length; w++) {
				board[r][w] = (r*7)+w;
			}
		}
		return board;
	}// end of fillArray
	
	public static void hWin(int num, int x) {
		if(x==-1 || x>5) {
			x=Board.getBoard()[0].length;
		}
		
		for(int c=0; c<Board.getBoard()[0].length; c++) {
			for(int r=x; r>=0; r--) {
				Board.setBoard(r, c, num);
			}
		}
	}// end of hWin()
	
	public static void vWin(int num, int x) {
		if(x==-1 || x>6) {
			x=Board.getBoard().length;
		}
		
		for(int r=x; r>=0; r--) {
			for(int c=0; c<Board.getBoard()[0].length; c++) {
				Board.setBoard(r, c, num);
			}
		}
	}// end of vWin()
	
	public static void dWin(int num, int x) {
		if(x==-1 || x>6) {
			x=6;
		}
		
		for(int c=0; c<Board.getBoard()[0].length; c++) {
			Board.setBoard(c, c, num);
		}
	}
	
	
}// end of tester class
