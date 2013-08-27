package org.JSPPlay.Test;

import java.util.Random;

//import java.io.Console;
/**
 * Simple TicTacToe:
 * @author Jason Berger
 *
 */
public class TicTacToe {
	// public String Last; //last move by user.
	public int nextindex = -1;

	/**
	 * Checks if the given letter has a solution.
	 * 
	 * @param str
	 *            : the board data.
	 * @param ltr
	 *            : The letter to check for game over. o or x.
	 * @return: true/false. Whether the game is over and the letter passed is
	 *          the winner.
	 */
	public boolean isGameOverByLetter(String str, char ltr) {
		char[][] array = stringToArray(str);
		//check for solutions.
		for(int i = 0; i < 3; i++)
		{
			if(array[0][i] == ltr && array[1][i] == ltr && array[2][i]==ltr )
				return true;
			if(array[i][0] == ltr && array[i][1] == ltr && array[i][2]==ltr)
				return true;
		}
		// check diagonal
		if (str.charAt(0)==ltr
				&& str.charAt(4) ==ltr
				&& str.charAt(8) ==ltr)
			return true;
		else if (str.charAt(2)==ltr
				&& str.charAt(4) ==ltr
				&& str.charAt(6) ==ltr)
			return true;
		
		return false;
	}
	/**
	 *  Replaces a char at a given position.
	 * @param position: index to replace character.
	 * @param ch: the char to be inserted.
	 * @param str: the string that will be modified.
	 * @return the modified line.
	 */
	@SuppressWarnings("finally")
	public String changeCharInPosition(int position, char ch, String str) {
		String result = "";
		try {
			char[] charArray = str.toCharArray();
			charArray[position] = ch;
			result = new String(charArray);
		} catch (Exception ex) {
			System.out.println("position" + Integer.toString(position)
					+ "was out of range.");
			result = str;
		} finally {
			return result;
		}

	}

	/**
	 * returns the number of occurances of the char ch in str.
	 * 
	 * @param str:  board data
	 * @param ch: the character to search for.
	 * @return number of occurances of ch in str.
	 */
	public int getMovecount(String str, char ch) {
		int count = 0;
		for (int i = 0; i < str.length(); i++)
			if (str.charAt(i) == ch)
				count++;
		return count;
	}

	/**
	 * Check for a winning move horizontal.
	 * @param board board data.
	 * @param ch the player char (x or o)
	 * @return true if a move exists diagonally that will win.
	 */
	public boolean checkWinHorizontal(String board, char ch) {
		char[][] array = stringToArray(board);

		// check col 1.
		int foundid = -1;
		int matchcount = 0;

		for (int y = 0; y < 3; y++) {
			foundid = -1;
			matchcount = 0;
			for (int x = 0; x < 3; x++) {
				char c = array[x][y];
				if (c == 'e') {
					foundid = (y * 3) + x;
				} else if (c == ch) {
					matchcount++;
				}
			}
			if (matchcount == 2 && foundid > -1) {
				this.nextindex = foundid;
				return true;
			}
		}
		nextindex = -1;
		return false;
	}

	/**
	 * Converts board string into two dimensional array [3][3].
	 * @param board board data.
	 * @return converted string to two dimensional array.
	 */
	public char[][] stringToArray(String board) {
		char[][] array = new char[3][3];
		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 3; x++) {
				array[x][y] = board.charAt((y * 3) + x);
			}
		return array;
	}

	/**
	 * Check for a winning move vertical.
	 * @param board board data.
	 * @param ch the player char (x or o)
	 * @return true if a move exists diagonally that will win.
	 */
	public boolean checkWinVertical(String board, char ch) {
		char[][] array = stringToArray(board);

		// check col 1.
		int foundid = -1;
		int matchcount = 0;

		for (int x = 0; x < 3; x++) {
			foundid = -1;
			matchcount = 0;
			for (int y = 0; y < 3; y++) {
				char c = array[x][y];
				if (c == 'e') {
					foundid = (y * 3) + x;
				} else if (c == ch) {
					matchcount++;
				}
			}
			if (matchcount == 2 && foundid > -1) {
				this.nextindex = foundid;
				return true;
			}
		}
		nextindex = -1;
		return false;
	}
	
	
/**
 * Check for a winning move diagonal.
 * @param board board data.
 * @param ch the player char (x or o)
 * @return true if a move exists diagonally that will win.
 */
	public boolean checkWinDiagonal(String board, char ch) {
		char[][] array = stringToArray(board);

		int foundid = -1;
		int matchcount = 0;
		// do test for back slash \
		for (int i = 0; i < 3; i++) {
			char c = array[i][i];
			if (c == 'e') {
				foundid = (i * 3) + i;
			} else if (c == ch) {
				matchcount++;
			}
		}
		// do we have a solution?
		if (matchcount == 2 && foundid > -1) {
			this.nextindex = foundid;
			return true;
		}

		// reset variables.
		foundid = -1;
		matchcount = 0;
		// do test in reverse. /
		for (int i = 0; i < 3; i++) {
			char c = array[2 - i][i];
			if (c == 'e') {
				foundid = (i * 3) + (2 - i);
			} else if (c == ch) {
				matchcount++;
			}
		}
		// do we have a solution?
		if (matchcount == 2 && foundid > -1) {
			this.nextindex = foundid;
			return true;
		}
		return false;
	}

	/**
	 * Generate a random number within and including the min/max.
	 * @param min minimum result.
	 * @param max maximum result.
	 * @return random number.
	 */
	public int randInt(int min, int max) {

		// Usually this can be a field rather than a method variable
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}
	/**
	 * Check all scenarios for a winning move by a player (ch).
	 * if true, will set the nextindex variable.
	 * @param board board data.
	 * @ch char (x or o)
	 * @return boolean, whether a solution exists.
	 */
	public boolean checkForWinningMove(String board,char ch) {
		if (this.checkWinHorizontal(board, ch)
				|| this.checkWinVertical(board, ch)
				|| this.checkWinDiagonal(board, ch)) {
			return true;
		}
		return false;
	}

	/**
	 * Do Move Logic Algorithm.
	 * 
	 * @param board board data. "eeeeeeeee"
	 * @return new board data. "eeeeeeeee"
	 */
	public String doMove(String board) {
		String result = board;
		nextindex = -1;
		// 1. Have I gone twice? I don't want to store this so just give me a
		// count of o's
		int count = getMovecount(board, 'o');
		if (count >= 2) {
			// 2. Do I have a winning move?
			if (this.checkForWinningMove(board,'o')) {
				// take the move.
				result = this.changeCharInPosition(nextindex, 'o', result);
				return result;
			}

		}
		// 3. Does player have a winning move?
		if (this.checkForWinningMove(board,'x')) {
			// block the player.
			result = this.changeCharInPosition(nextindex, 'o', result);
			return result;
		} else {

			
			//4. Check for corners and middle.all evens.
			for (int i = 0; i < 9; i += 2) {
				if (board.charAt(i) == 'e') {
					nextindex = i;
					break;
				}
			}

			// 5. Randomly pick a number in range of 0 and 8.
			if (nextindex == -1) {
				// go pick a random move.
				while (true) {
					nextindex = this.randInt(0, 8);
					// check it.
					if (board.charAt(nextindex) == 'e') {
						break;
					}

				}
			}

			// take the move.
			result = this.changeCharInPosition(nextindex, 'o', result);
			return result;
		}

	}
	/**
	 * Supporting function to be used by client to set the user's move in the data.
	 * @param action the xy move collected from UI.
	 * @param data the board data.
	 * @return the new string.
	 */
	public String SetAction(String action,String data)
	{
		//calculate the index.
		int x = Character.getNumericValue(action.charAt(0));
		int y = Character.getNumericValue(action.charAt(1));
		//SetActionX = Integer.toString(x) + "," + Integer.toString(y);
		//now set the data.
		int index = (y * 3) + x;
		//SetActionIndex = Integer.toString(index);
		data = changeCharInPosition(index,'x',data);
		return data;
	}

}
