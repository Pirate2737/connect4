package connect4;
import java.util.Scanner;

public class Board {
	
	/* __To Dos:__
	 1) FIX win conditions (not 1st row/col wins not counting)
	 * rewrite win conditions for efficiency
	 * fix the intro to be cleaner and to accept non ints
	 * work on improved bot algorithm (working on random rn)
	 * 
	 * __Active Work:__
	 1) main code refactoring <-- *this has to come first*
	 --> the bot is being treated like a player (yikers)
	 2) rng bot work
	 3) fix the tester classes lol (eeky)
	 */
	
	private static int[][] board = new int[7][6];
	private static boolean isOver = false;
	private static int numTurns = 0;
	private static int numPlayers;
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);	
		
		System.out.print("Would you like to play with 1 or 2 people? ");
		numPlayers = scan.nextInt();
		scan.nextLine();
		
		// tester
		if(numPlayers==11 || numPlayers==12) {
			numPlayers-=10;
			Tester.prompt(scan);
		}
		
		// Player 1 Setup
		System.out.print("Player 1 type your name: ");
		Player p1 = new Player(scan.nextLine(), 1);
		
		// Gameplay
		if(numPlayers==1) {
			onePlayerGame(p1, scan);
		}else if(numPlayers==2){
			twoPlayerGame(p1, scan);
		}
		
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
		
		if(checkOver()==0) {
			System.out.println("It's a tie!");
		}else if(checkOver()==1) {
			System.out.println("Congrats to \"" + p1.getName() + "\" for winning!!");
		}else {
			System.out.println("Congrats to \"" + p2.getName() + "\" for winning!!");
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
		if(checkOver()==0) {
			System.out.println("It's a tie!");
		}else if(checkOver()==1) {
			System.out.println("Congrats to \"" + p1.getName() + "\" for winning!!");
		}else {
			System.out.println("Congrats to \"" + p2.getName() + "\" for winning!!");
		}
		
	}// end of one player game
	
	
	private static void gameLoop(Player p1, Player p2, Scanner scan) {
		while(!isOver) {
			numTurns++;
			printBoard();
			System.out.print("Player \"" + p1.getName() + "\" Turn " + numTurns + ": Which column would you like to place your piece? (1-6) ");
			p1.doTurn(scan);
			checkOver();
			
			// if "exit" or win
			if(isOver) {
				break;
			}
			
			printBoard();
			
			System.out.print("Player \"" + p2.getName() + "\" Turn " + numTurns + ": Which column would you like to place your piece? (1-6) ");
			p2.doTurn(scan);
			checkOver();
			
			if(numTurns==42) {
				break;
			}
			
		}// main game loop
	}// end of method gameloop 2p
	
	private static void botGameLoop(Player p1, Bot p2, Scanner scan) {
		while(!isOver) {
			numTurns++;
			printBoard();
			System.out.print("Player \"" + p1.getName() + "\" Turn " + numTurns + ": Which column would you like to place your piece? (1-6) ");
			p1.doTurn(scan);
			checkOver();
			
			if(isOver) {
				break;
			}
			
			printBoard();
			
			System.out.print("Player \"" + p2.getName() + "\" Turn " + numTurns);
			p2.randTurn();
			checkOver();
			
			if(numTurns==42) {
				break;
			}
			
		}// main game loop
	}// end of method gameloop 1p
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static int[][] getBoard(){
		return board;
	}
	
	public static void printBoard() {
		//char circle = '\u2744';
		for(int r=0; r<board.length; r++) {
			for(int c=0; c<board[0].length; c++) {
				
				System.out.print(board[r][c] + " ");
//				if(board[r][c]==1) {
//					System.out.print("\u001B[31m" + circle);
//					System.out.print("\u001B[0m" + circle);
//				}
				
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
	
	public static int checkOver() {
		//if to check, then set isOver
		int cond = 0;
		int num = board[6][0];
		
		// checks for vertical win
		for(int c=0; c<board[0].length; c++) {
			for(int r=board.length-1; r>=0; r--) {
				
				cond++;
				if(board[r][c] != num || num==0) {
					num=board[r][c];
					cond = 0;
				}
				if(cond==4) {
					setOver();
					return num;
				}			
			}
			cond = 0;
		}// end of vertical win
		
		num = board[6][0];
		// checks for horizontal win
		for(int r=board.length-1; r>=0; r--) {
			for(int c=0; c<board[0].length; c++) {
				
				cond++;
				if(board[r][c] != num || num==0) {
					num=board[r][c];
					cond = 0;
				}
				if(cond==4) {
					setOver();
					return num;
				}
			}
			cond = 0;
		}// end of horizontal win
		
		return 0;
	}// end of checkOver()
	
}// end of class
