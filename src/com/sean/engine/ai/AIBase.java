package com.sean.engine.ai;


import com.sean.engine.BoardState;

/**
 * The abstract base class of the AI Algorithms has 3 methods. <br>
 * optimalMove() - abstract method which returns the move based on the difficulty setting<br>
 * checkForWin() - determines if the computer player can win<br>
 * checkForBlock() - determines if the Computer player should block
 * @author swhalley
 *
 */
public abstract class AIBase {

	/**
	 * Abstract method. see individual difficulties for comments
	 * @param board
	 * @return
	 */
	abstract public int optimalMove(final BoardState board);

	/**
	 * The method looks in all 9 board spots for a valid win. <br>
	 * Takes the winScenarios from the board and checks the players board state 
	 * if they were to place a piece in that position
	 * @param board - Current board state of both players
	 * @return - If a win is possible the position on the board will be returned. If no win possible, -1.
	 */
	protected int checkForWin(final BoardState board) {
		boolean winScenario[] = board.getWinScenario();

		for (int position = 0; position < 9; position++) {
			if (board.isValidMove(position)) {
				int pw = board.getOBoard() | (1 << position);
				if (winScenario[pw]) {
					// Computer player (O) can won at this position
					return position;
				}
			}
		}

		return -1; //no win possible for computer
	}

	
	/**
	 * The method will be check if it is possible for the player to win.
	 * If so the Computer player will block that position
	 * @param board Current board state of both players
	 * @return If a block is possible the position on the board will be returned. If no block possible, -1.
	 */
	protected int checkForBlock(final BoardState board) {
		boolean winScenario[] = board.getWinScenario();

		for (int position = 0; position < 9; position++) {
			//See if that spot is taken
			if(board.isValidMove(position)) { 
				//Look at the game state if player take that spot
				int pw = board.getXBoard() | (1 << position);
				if (winScenario[pw]) {
					//Player (X) can win there, take that
					return position;
				}
			}
		}
		return -1; //no win possible for player
	}
	
}
