package connect4;
import java.util.Scanner;

public class Tester {
	
	public static void prompt(Scanner scan) {
		System.out.println("\nWhich method would you like to call? hWin, vWin, dWin, fillArray, over");
		String input = scan.nextLine().toLowerCase();
		
		if(input.equals("hwin")) {
			System.out.print("1/2, amount: ");
			hWin(scan.nextInt(), scan.nextInt());
			scan.nextLine();
			
		}else if(input.equals("vwin")) {
			System.out.print("1/2, amount: ");
			vWin(scan.nextInt(), scan.nextInt());
			scan.nextLine();
			
		}else if(input.equals("dwin")) {
			System.out.print("1/2, amount: ");
			dWin(scan.nextInt(), scan.nextInt());
			scan.nextLine();
			
		}else if(input.equals("fillarray")) {
			fillArray(Board.getBoard());
			
		}else if(input.equals("over")) {
			setOver();
			
		}else {
			System.out.println("nuh uh");
		}
		
	}// end of prompt()
	
	public static int[][] fillArray (int[][] board) {
		for(int r=0; r<board.length; r++) {
			for(int w=0; w<board[0].length; w++) {
				board[r][w] = (r*7)+w;
			}
		}
		return board;
	}// end of fillArray
	
	public static void hWin(int num, int x) {
		if(x==-1 || x>6) {
			x=Board.getBoard()[0].length;
		}
		
		for(int c=0; c<Board.getBoard()[0].length; c++) {
			for(int r=x; r>=0; r--) {
				Board.setBoard(r, c, num);
			}
		}
	}// end of hWin()
	
	public static void vWin(int num, int x) {
		if(x==-1 || x>5) {
			x=Board.getBoard().length;
		}
		
		for(int r=x; r>=0; r--) {
			for(int c=0; c<Board.getBoard()[0].length; c++) {
				Board.setBoard(r, c, num);
			}
		}
	}// end of vWin()
	
	public static void dWin(int num, int x) {
		if(x==-1 || x>7) {
			x=6;
		}
		
		for(int c=0; c<Board.getBoard()[0].length; c++) {
			Board.setBoard(c, c, num);
		}
	}// end of dWin()
	
	private static void setOver() {
		Board.setOver(true);
	}// end of setOver()
	
}// end of tester class
