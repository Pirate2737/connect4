package connect4;
import java.util.Scanner;

public class Board {
	
	/* __To Dos:__
	 * rewrite win conditions for efficiency
	 * colors
	 * randomize who starts
	 * win counter (includes method refactoring bc of p2)
	 
	     __Active Work:__
	 1) work on improved bot algorithm
	 2) fix the tester classes lol (eeky)
	 
	 */
	
	private static int[][] board = new int[6][7];
	private static boolean isOver = false;
	private static int numTurns = 0;
	private static int numPlayers;
	private static final int xToWin = 4; 
	
	public static void main(String[] args) {
		String input;
		Scanner scan = new Scanner(System.in);	
		
		//while(true) {
			System.out.print("Would you like to play with 1 or 2 people? ");
			input = scan.nextLine();
	
			while(!(input.equals("1") || input.equals("2") || input.equals("11") || input.equals("12"))) {
				System.out.println("Invalid Answer. Try Again.");
				input = scan.nextLine();
			}
			numPlayers = Integer.parseInt(input);
	
			// tester
			if(numPlayers==11 || numPlayers==12) {
				numPlayers-=10;
				Tester.prompt(scan);
			}
			
			// Player 1 Setup
			System.out.print("Player 1 type your name: ");
			Player p1 = new Player(scan.nextLine(), 1);
			
		while(true) {
			// Gameplay
			if(numPlayers==1) {
				onePlayerGame(p1, scan);
			}else if(numPlayers==2){
				twoPlayerGame(p1, scan);
			}
			
			System.out.print("Would you like to play again? ");
			input = scan.nextLine().toLowerCase();
			while(!(input.equals("yes") || input.equals("no"))) {
				System.out.println("Invalid Answer. Try Again");
				input = scan.nextLine().toLowerCase();
			}
			if(input.equals("no")) {
				break;
			}
			isOver = false;
			clearBoard();
		}
		System.out.println("\nThanks for playing!\n");
		//System.out.println("End Scores\n" + p1.getName() + ": " + p1.getWins());

		scan.close();
	}// end of main method
	
	
	private static void twoPlayerGame(Player p1, Scanner scan) {
		
		// Player 2 Setup
		System.out.print("Player 2 type your name: ");
		Player p2 = new Player(scan.nextLine(), 2);
		
		System.out.println("Connect 4 Game Started with \"" + p1.getName() + "\" and \"" + p2.getName() + "\". Type \"exit\" at any time to stop the game");
		
		// Game loop
		gameLoop(p1, p2, scan);
		
		System.out.println("Final board: ");
		printBoard();
		System.out.println();
		
		if(checkOver(xToWin)==0) {
			System.out.println("It's a tie!");
		}else if(checkOver(xToWin)==1) {
			System.out.println("Congrats to \"" + p1.getName() + "\" for winning!!");
			p1.incWins();
		}else {
			System.out.println("Congrats to \"" + p2.getName() + "\" for winning!!");
			p2.incWins();
		}
	}// end of two player game
	
	private static void onePlayerGame(Player p1, Scanner scan) {
		Bot p2 = new Bot();
		
		System.out.println("Connect 4 Game Started with \"" + p1.getName() + "\" and \"" + p2.getName() + "\". Type \"exit\" at any time to stop the game");
		
		// Game loop
		botGameLoop(p1, p2, scan);
		
		// End Game
		System.out.println("Final board: ");
		printBoard();
		System.out.println();
		
		// Checks if Game is Over
		if(checkOver(xToWin)==0) {
			System.out.println("It's a tie!");
		}else if(checkOver(xToWin)==1) {
			System.out.println("Congrats to \"" + p1.getName() + "\" for winning!!");
			p1.incWins();
		}else {
			System.out.println("Congrats to \"" + p2.getName() + "\" for winning!!");
			p2.incWins();
		}
		
	}// end of one player game
	
	
	private static void gameLoop(Player p1, Player p2, Scanner scan) {
		while(!isOver) {
			numTurns++;
			printBoard();
			System.out.print("\nPlayer \"" + p1.getName() + "\" Turn " + numTurns + ": Which column would you like to place your piece? (1-7) ");
			p1.doTurn(scan);
			checkOver(xToWin);
			
			// if "exit" or win
			if(isOver) {
				break;
			}
			
			numTurns++;
			printBoard();
			System.out.print("\nPlayer \"" + p2.getName() + "\" Turn " + numTurns + ": Which column would you like to place your piece? (1-7) ");
			p2.doTurn(scan);
			checkOver(xToWin);
			
			if(numTurns==84) {
				break;
			}
			
		}// main game loop
	}// end of method gameloop 2p
	
	private static void botGameLoop(Player p1, Bot p2, Scanner scan) {
		while(!isOver) {
			numTurns++;
			printBoard();
			System.out.print("\nPlayer \"" + p1.getName() + "\" Turn " + numTurns + ": Which column would you like to place your piece? (1-7) ");
			p1.doTurn(scan);
			checkOver(xToWin);
			
			if(isOver) {
				break;
			}
			
			numTurns++;
			printBoard();
			System.out.println("\nPlayer \"" + p2.getName() + "\" Turn " + numTurns);
			p2.doTurn(numTurns);
			checkOver(xToWin);
			
			if(numTurns==84) {
				break;
			}
			
		}// main game loop
	}// end of method gameloop 1p
	
	
	
	
	public static int[][] getBoard(){
		return board;
	}
	
	public static void printBoard() {
		for(int r=0; r<board.length; r++) {
			for(int c=0; c<board[0].length; c++) {
				
				if(board[r][c]==1) {
					System.out.print("\u001B[36m");
				}else if(board[r][c] == 2) {
					System.out.print("\u001B[32m");
				}
				
				System.out.print(board[r][c] + " ");
				System.out.print("\u001B[0m");
			}
			System.out.println();
		}
	}// end of printBoard()
	
	public static void setBoard(int r, int c, int xo) {
		board[r][c] = xo;
	}
	
	public static void clearBoard() {
		for(int r=0; r<board.length; r++) {
			for(int c=0; c<board[0].length; c++) {			
				
				board[r][c] = 0;
				
			}
		}
	}// end of clearBoard()
	
	public static void setOver() {
		isOver = true;
	}
	
	public static int checkOver(int x) {
		// if to check, then set isOver
		int cond = 0;
		int num = board[5][0];
		
		// checks for vertical win
		for(int c=0; c<board[0].length; c++) {
			for(int r=board.length-1; r>=0; r--) {
				
				cond++;
				if(board[r][c] != num || num==0) {
					num=board[r][c];
					cond = 0;
					if(board[r][c]!=0) {
						cond=1;
					}
				}
				//System.out.println("Col: " + c + " | Row: " + r + " | num + cond: " + num + ": " + cond);
				if(cond==x) {
					if(x==xToWin) {
						setOver();
					}
					return num;
				}			
			}
			cond = 0;
		}// end of vertical win
		num = board[5][0];
		
		
		// checks for horizontal win
		for(int r=board.length-1; r>=0; r--) {
			for(int c=0; c<board[0].length; c++) {
				
				cond++;
				if(board[r][c] != num || num==0) {
					num=board[r][c];
					cond = 0;
					if(board[r][c]!=0) {
						cond=1;
					}
				}
				if(cond==x) {
					if(x==xToWin) {
						setOver();
					}
					return num;
				}
			}
			cond = 0;
		}// end of horizontal win
		
		
		return 0;
	}// end of checkOver()
	
}// end of class
