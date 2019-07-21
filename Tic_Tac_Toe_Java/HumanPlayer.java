
import java.io.PrintWriter;
import java.util.Scanner;

public class HumanPlayer extends player{
		
		private PrintWriter output;
		private Scanner input;
	
		/**
		 * @param name a String denotes the name of this player
		 * @param symbol a String denotes the symbol of this player, will be X and O
		 */
		public HumanPlayer(String name, int symbol,Scanner input,PrintWriter output) {
			super(name, symbol);
			this.input = input;
			this.output = output;
		}

		/**
		 * @param gameboard the gameboard
		 */
		public void play(board gameboard) {
			//boolean c;
			int row,col;//store the player's next move
			
			do {
				output.println("REQUEST Please enter x-axis (row):  (0, 1, 2)");
				row = input.nextInt();
				
				output.println("REQUEST Please enter y-axis (col): (0, 1, 2)");
				col = input.nextInt();
				
				
				if(row < 0 || row >2 || col <0 || col >2)
					continue;
				
				}while(!gameboard.checkEmpty(row, col));
			
			gameboard.makeMove(row, col, returnsym());
			
		}
}