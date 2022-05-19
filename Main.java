import java.util.*;

class Main {

	private static String[][] board = { { " ^ ", " ^ ", " ^ ", " ^ ", " ^ ", " ^ " },
			{ " ^ ", " ^ ", " ^ ", " ^ ", " ^ ", " ^ " },
			{ " ^ ", " ^ ", " ^ ", " ^ ", " ^ ", " ^ " },
			{ " ^ ", " ^ ", " ^ ", " ^ ", " ^ ", " ^ " },
			{ " ^ ", " ^ ", " ^ ", " ^ ", " ^ ", " ^ " },
			{ " ^ ", " ^ ", " ^ ", " ^ ", " ^ ", " ^ " } };
	private static int[] xcordc = new int[2];
	private static int[] ycordc = new int[2];
	private static int playerShip = 2;
	private static int computerShip = 2;

	public static void main(String[] args) {
		// game startup: display board
		System.out.println();
		System.out.println("Welcome to Shipwars: You vs. The Computer!\nTo Play: O means miss, ^ means water, \nSD means your ship is down, ~*~ is your ship, \nand x means you hit an enemy ship!");
		printBoard();

		// get player's coordinates & set up ships
		displayPlayerShips();
		displayPlayerShips();

		// set up computer's ships
		computerShips();

		// FIGHT!!
		playersGuess();

	}

	// prints board
	public static void printBoard() {
		System.out.println();
		System.out.println();
		System.out.println("   1  2  3  4  5  6");

		for (int row = 0; row < board.length; row++) {
			System.out.print(row + 1 + " ");
			for (int col = 0; col < board[0].length; col++) {
				System.out.print(board[row][col]);
			}
			System.out.println();
		}
	}

	// takes coords w/ scanner & sets up ships
	public static void displayPlayerShips() {
		Scanner scan = new Scanner(System.in);
		int xcoord = 0;
		int ycoord = 0;

		// prevent letters in x coord
		System.out.println("Enter the x-coordinate for your ship.");
		try {
			xcoord = scan.nextInt();
		} catch (Exception e) {
			System.out.println();
			System.out.println("Please enter valid a number.");
			displayPlayerShips();
			return;
		}

		// prevent letters in y coord
		System.out.println("Enter the y-coordinate for your ship.");
		try {
			ycoord = scan.nextInt();
		} catch (Exception e) {
			System.out.println();
			System.out.println("Please enter a valid number.");
			displayPlayerShips();
			return;
		}

		// displays ship on grid/checks if coordinate is in bounds
		if ((xcoord > 0 && xcoord < 7) && (ycoord > 0 && ycoord < 7)) {
			board[ycoord - 1][xcoord - 1] = "~*~";
			printBoard();
		} else {
			System.out.println();
			System.out.println();
			System.out.println("Your coordinate is out of bounds, try again.");
			displayPlayerShips();
		}

	}

	//method to randomize computer coords for ships & store them to compare to later
	public static void computerShips(){
		for(int i = 0; i < xcordc.length; i++){
			xcordc[i] = (int)(Math.random()*6) + 1;
			ycordc[i] = (int)(Math.random()*6) + 1;
		}
		System.out.println();
		System.out.println("The Computer's ships have been released as well!");
	}

	
	// method for computer
	public static void computersGuess() {
		if(playerShip == 0){
			endGame(false, true);
		} else {
			int cxguess = -1;
			int cyguess = -1;
	
			// prevent letters to enter x guess
				cxguess = (int)(Math.random() * 6) + 1;
				cyguess = (int)(Math.random() * 6) + 1;
				
			// changes grid to display a hit or miss
			if (board[cyguess - 1][cxguess - 1] == "~*~") {
			board[cyguess - 1][cxguess - 1] = " SD ";
	      	printBoard();
		    System.out.println("Computer's turn");
		    System.out.println();
		    System.out.println("Computer Guessed: " + cxguess +", "+ cyguess);
		    System.out.println();
		    System.out.println("Ship Down!");
			Main.playerShip--;
        	System.out.println();
				
        	//delays the next guess
			try { 
          	Thread.sleep(1000); 
          	computersGuess();
        	} catch (Exception e) { 
       		System.out.println();
        	}
      		//text to see board and computer input
			} else {
				printBoard();
	        	System.out.println("Computer's turn");
	        	System.out.println();
	        	System.out.println("Computer Guessed: " + cxguess + ", " + cyguess);
	        	System.out.println();
				System.out.println("The Computer missed!");
	        	System.out.println();
	        	//delays the board for player
	        	try { 
	          		Thread.sleep(2000); 
	          		playersGuess();
	        	} catch (Exception e) { 
	       			System.out.println();
        		}
			}
	    }
	}

	// player input
	public static void playersGuess() {
		if(computerShip == 0){
			endGame(true, false);
		}
		else{
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter your guess for an x-coordinate.");
			int xguess = 0;
			int yguess = 0;
			boolean success = true;
	
			// prevent letters to enter x guess
			try {
				xguess = scan.nextInt();
			} catch (Exception e) {
				success = false;
				System.out.println();
				System.out.println("Please enter a valid number.");
				playersGuess();
				return;
			}
	
			// prevent letters to enter y guess
			if (success)
				;
				System.out.println("Enter your guess for a y-coordinate");
			try {
				yguess = scan.nextInt();
			} catch (Exception e) {
				success = false;
				System.out.println();
				System.out.println("Please enter a valid number.");
				playersGuess();
				return;
			}
			if (success)
				;
	
			// changes grid to display a hit or miss
			if ((xguess > 0 && xguess < 7) && (yguess > 0 && yguess < 7)) {
				if ((xguess == xcordc[0] && yguess == ycordc[0]) || (xguess == xcordc[1] && yguess == ycordc[1])) {
					board[yguess - 1][xguess - 1] = " x ";
					printBoard();
					System.out.println("Hit!");
	        		Main.computerShip--;
          			//delays the player input
					try { 
          				Thread.sleep(1000); 
          				playersGuess();
        			} catch (Exception e) { 
       					System.out.println();
        			}
				} else {
					board[yguess - 1][xguess - 1] = " O ";
					printBoard();
					System.out.println("You missed!");
          			System.out.println();
          			//delays computer input
					try { 
          				Thread.sleep(2000); 
         				computersGuess();
        			} catch (Exception e) { 
       					System.out.println();
        			}
				}
        	//runs if player inputs number that is out of the board
			} else {
				System.out.println();
				System.out.println();
				System.out.println("You're out of bounds, try again.");
				playersGuess();
			}
		}
	}

  	//method to end game and decide winner
	public static void endGame(boolean playerWon, boolean computerWon) {
	if (playerWon == true){
    System.out.println();
		System.out.println("Congrats\nYou Win!");
	    }
	else if (computerWon == true){
      System.out.println();
	    System.out.println("Oh No!\nYou Lost!");
	}
	System.out.print("Thanks for playing!");
	}	
}