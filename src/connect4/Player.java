package connect4;
import java.util.Scanner;

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
		for(int r=Board.getBoard().length-1; r > -1; r--) {
			if(Board.getBoard()[r][col]==0) {
				Board.setBoard(r, col, xo);
				break;
			}
		}
		
	}// end of doTurn()
	
	public int isValidMove(String input) {
		int col;
		
		// exit on "exit"
		if(input.toLowerCase().equals("exit")) {
			Board.setOver();
			return 0;
		}
		
		// checks for num
		else if(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5") || input.equals("6") || input.equals("7")) {
			col = Integer.parseInt(input);
		}else {return -1;}
		
		col--;
		
		// invalidates full col
		if(Board.getBoard()[0][col] != 0) {
			return -1;
		}
		
		return col;
	}// end of isValidMove()
	
	// info methods
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getWins() {
		return numWins;
	}
	
	public void incWins(){
		numWins++;
	}
	
}// end of class
