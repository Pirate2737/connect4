package connect4;
import java.util.Scanner;
//import java.util.Random;
public class Player {
	
	private String name;
	private int numWins;
	private int xo;
	
	public Player(String newName, int xo) {
		numWins = 0;
		name = newName;
		this.xo = xo;
	}
	
	public void doTurn(Scanner scan) {
		String input;
		int col;
		
		// input loop
		while(true) {
			input = scan.nextLine();
			
			if(isValidMove(input)!=-1) {
				col = isValidMove(input);
				break;
			}
			System.out.println("Invalid move. Try again");
		}// end of while loop
		
		
		// placing the piece
		for(int r=Board.getBoard()[0].length; r>-1; r--) {
			if(Board.getBoard()[r][col]==0) {
				Board.setBoard(r, col, xo);
				break;
			}
		}
		//while() <-- what was i doing here
		
	}// end of doTurn
	
	public int isValidMove(String input) { // needs to check if works
		int col;
		
		// exit on "exit"
		if(input.toLowerCase().equals("exit")) {
			Board.setOver();
			return 0;
		}
		
		// checks for num
		else if(input.equals("0") || input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5") || input.equals("6") || input.equals("7") || input.equals("8") || input.equals("9")) {
			col = Integer.parseInt(input);
		}else {return -1;}
		
		col--;
		
		// invalidates out of range or full col
		if(!(col > -1 && col < 6)) {
			return -1;
		}if(Board.getBoard()[0][col] != 0) {
			return -1;
		}
		
		return col;
	}// end of isValidMove
	
	// things
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int numWins() {
		return numWins;
	}
	
	public void incWins(){
		numWins++;
	}
	
	
}// end of class
