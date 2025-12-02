import java.util.*;

class valueOfFinalStates
{
	public static final int max = 1;
	public static final int min = -1;
	public static final int isopalia = 0;
}

class Game
{
	public char[][] table = new char[3][3];
	public Game[] children = new Game[24];
	public int nextPlayer;
}

public class Lab2
{
	public static void printGameBoard(char[][] board)
	{
		int numOfRows = 3;
		int numOfColumn = 3;
		
		for (int raw=0; raw<numOfRows; raw++)
		{
			for (int column=0; column<numOfColumn; column++)
			{
				System.out.printf("%c ", board[raw][column]);
			}
			System.out.println();
		}
		 System.out.println();
	}
	
	public static boolean checkForWinner(char[][] table)
	{
		if (table[0][0]=='C' && table[0][1]=='S' && table[0][2]=='E')  		   
		{
			return true;
		}
		else if (table[2][0]=='C' && table[2][1]=='S' && table[2][2]=='E')		
		{
			return true;
		}
		else if (table[0][0]=='C' && table[1][0]=='S' && table[2][0]=='E')		
		{
			return true;
		}
		else if (table[0][1]=='C' &&table[1][1]=='S' && table[2][1]=='E')		
		{
			return true;
		}	
		else if (table[0][2]=='C' && table[1][2]=='S' && table[2][2]=='E')		
		{
			return true;
		}
		else if (table[0][0]=='C' && table[1][1]=='S' && table[2][2]=='E')		
		{
			return true;
		}
		else if (table[0][2]=='C' && table[1][1]=='S' && table[2][0]=='E')		
		{
			return true;
		}
		else if (table[0][0]=='E' && table[0][1]=='S' && table[0][2]=='C')  	
		{
			return true;
		}
		else if (table[2][0]=='E' && table[2][1]=='S' && table[2][2]=='C')		
		{
			return true;
		}
		else if (table[0][0]=='E' && table[1][0]=='S' && table[2][0]=='C')		
		{
			return true;
		}
		else if (table[0][1]=='E' &&table[1][1]=='S' && table[2][1]=='C')		
		{
			return true;
		}	
		else if (table[0][2]=='E' && table[1][2]=='S' && table[2][2]=='C')		
		{
			return true;
		}
		else if (table[0][0]=='E' && table[1][1]=='S' && table[2][2]=='C')		
		{
			return true;
		}
		else if (table[0][2]=='E' && table[1][1]=='S' && table[2][0]=='C')		
		{
			return true;
		}
		
		return false;
	}
	
	public static int MINIMAX(Game boardState, int currentPlayer)
	{	
		int nextPlayer = 0;    //krataei ton epomeno paikth pou prepei na eksetasoume
		int optimalMove;
		int b;
		int positionOfChildren = 0;
		char[] moves = {'C', 'S', 'E'};
		
		if (currentPlayer == valueOfFinalStates.max)
		{
			nextPlayer = valueOfFinalStates.min;
		}
		
		else if(currentPlayer == valueOfFinalStates.min)
		{
			nextPlayer = valueOfFinalStates.max;
		}
		
		if (currentPlayer == valueOfFinalStates.min && checkForWinner(boardState.table) == true)
		{
			return 1;
		}
		
		else if (currentPlayer == valueOfFinalStates.max && checkForWinner(boardState.table) == true)
		{
			return -1;
		}	
		
		int[] array = new int[24];

		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				if (boardState.table[row][column] == 'X') {
					for (char move : moves) {
						boardState.children[positionOfChildren] = new Game();
						helpMethod(boardState.table, boardState.children[positionOfChildren].table);
						boardState.children[positionOfChildren].table[row][column] = move;
						array[positionOfChildren] = MINIMAX(boardState.children[positionOfChildren], nextPlayer);
						positionOfChildren++;
					}
				}
			}
		}
		
		if (currentPlayer == valueOfFinalStates.max)
		{
			optimalMove = array[0];
			b = 0;
			
			for (int i = 0; i < positionOfChildren; i++)
			{
				if (array[i] > optimalMove)
				{
					optimalMove = array[i];
					b = i;
				}
			}
		} 
		
		else 
		{
			optimalMove = array[0];
			b = 0;
			
			for (int i = 1; i < positionOfChildren; i++)
			{
				if (array[i] < optimalMove)
				{
					optimalMove = array[i];
					b = i;
				}
			}
		}
		
		boardState.nextPlayer = b ;
		return optimalMove;
	}
	
	public static void helpMethod(char[][] firstTable, char[][] secondTable)
	{
	   for (int row = 0; row < 3; row++)
	   {
		 for (int column = 0; column < 3; column++)
		 {
			secondTable[row][column] = firstTable[row][column];
		 }
	   }

	}
	
	public static void main(String[] args) throws InterruptedException 
	{
		Scanner usersAnswer = new Scanner(System.in);
		Game board = new Game();
		int currentPlayer = 1;
		int unoccupiedPosition = 7;
		
		String three = "3";
		String two = "2";
		String one = "1";
		String nothing = "  ";
		String in = "in ";
		
		System.out.printf("Game starts ");
		System.out.printf(in);
		
		System.out.printf(three);
		Thread.sleep(500);
		for (int i = 0; i < three.length(); i++) {
            System.out.print("\b");
        }
		
		System.out.printf(two);
		Thread.sleep(500);
		for (int i = 0; i < two.length(); i++) {
            System.out.print("\b");
        }
		
		System.out.printf(one);
		Thread.sleep(500);
		for (int i = 0; i < one.length(); i++) {
            System.out.print("\b");
        }
		
		System.out.printf(nothing);
		for (int i = 0; i < nothing.length(); i++) {
            System.out.print("\b");
        }
		
		for (int i = 0; i < in.length(); i++) {
            System.out.print("\b");
        }
		
		System.out.printf(nothing);
		for (int i = 0; i < nothing.length(); i++) {
            System.out.print("\b");
        }
		
		System.out.println();
		System.out.println();
		
		for (int row=0; row<board.table.length; row++)
		{
			for (int column=0; column<board.table.length; column++)
			{
				board.table[row][column] = 'X';
			}
		}
		
	    board.table[1][2] = 'S';
	    board.table[1][0] = 'S';
	    printGameBoard(board.table);
		
		while (true)
		{	
			if (currentPlayer == valueOfFinalStates.min && checkForWinner(board.table) == true)
			{
				System.out.println("Computer wins the game!");
				break;
			}
			else if(currentPlayer==valueOfFinalStates.max && checkForWinner(board.table) == true)
			{
				System.out.println("User wins the game!");
				break;
			}
			else if (unoccupiedPosition == 0)
			{
				System.out.println("Game is a draw!");
				break;
			}
			
			if (currentPlayer == valueOfFinalStates.max)
			{
				System.out.println("Computer is playing..");
				MINIMAX(board, valueOfFinalStates.max);
				board = board.children[board.nextPlayer];
				printGameBoard(board.table);
				currentPlayer = valueOfFinalStates.min;
				unoccupiedPosition = unoccupiedPosition-1;
				//System.out.println(unoccupiedPosition);
			}
			
			else if (currentPlayer == valueOfFinalStates.min)
			{		
				System.out.printf("User is playing.. \n");
				System.out.printf("'Choose one of characters (C,S,E)': ");
				char answer = usersAnswer.next().charAt(0);
				while (answer != 'C' && answer != 'S' && answer != 'E')
				{
					System.out.printf("!Enter a valid character (C,S,E): ");
					answer = usersAnswer.next().charAt(0);
				}
				
				System.out.printf("'Choose line (1,2,3)': ");
				int line = usersAnswer.nextInt();
				usersAnswer.nextLine();
				while (line != 1 && line != 2 && line != 3)
				{
					System.out.printf("!Enter a valid number of line(1,2,3): ");
					line = usersAnswer.nextInt();
					usersAnswer.nextLine();
				}
				
				System.out.printf("'Choose column (1,2,3)': ");
				int column = usersAnswer.nextInt();
				usersAnswer.nextLine();
				while (column != 1 && column != 2 && column != 3)
				{
					System.out.printf("!Enter a valid number of column (1,2,3): ");
					column = usersAnswer.nextInt();
					usersAnswer.nextLine();
				}
				
				System.out.println();
				while (board.table[line-1][column-1] == 'X')
				{
					board.table[line-1][column-1] = answer;
					unoccupiedPosition = unoccupiedPosition-1;
					printGameBoard(board.table);
					currentPlayer = valueOfFinalStates.max;
					//System.out.println(unoccupiedPosition);
				}
				continue;
			}
		}
	}
} 