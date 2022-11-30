package com.sean.engine.ai;

import java.util.Random;

import com.sean.engine.BoardState;
import com.sean.states.GameState;
import org.apache.log4j.Logger;

/**
 * The easy AI is a random number generator which randomly picks a spot on the board. 
 * The only intelligence the AI has is it will try and win or block the other player.
 * @author sean
 *
 */

public class AIEasy extends AIBase{

	private static final Logger logger = Logger.getLogger(AIEasy.class);
	
	
	/**
	 * The algorithm will check for a Win or block. if neither scenario is 
	 * met, a Random number is returned.
	 * @param board 
	 * @return -1 if the game is not active. 0-8 for the location on the board otherwise
	 */
	public int optimalMove(final BoardState board) {
		Random rng = new Random();
		int position = -1;

		if(board.getGameState() != GameState.ACTIVE){
			logger.debug("Game not active, returning -1");
			return -1;
		}
		
		if ((position = checkForWin(board)) != -1){
			logger.debug("Found a win @ " + position);
			return position;
		}
			
		if ((position = checkForBlock(board)) != -1){
			logger.debug("Found a block @ " + position);
			return position;
		}
		
		//keep getting a random number between 0-9 until you find an empty space
		while (!board.isValidMove(position)) {
			position = rng.nextInt(9);
			logger.debug("getting a random number " + position);
		}

		logger.debug("Found an random opening @ " + position );
		return position;
	}
}
