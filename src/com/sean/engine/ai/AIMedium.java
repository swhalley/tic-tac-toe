package com.sean.engine.ai;

import com.sean.engine.BoardState;
import com.sean.states.GameState;
import org.apache.log4j.Logger;

/**
 * The medium algorithm is based on giving a rank to each spot on the board. <br>
 * The algorithm works as follows
 * <ul>
 * <li>Check if there are any win Scenarios
 * <li>Look for losing Scenarios
 * <li>Return the highest remaining ranked spot
 * </ul><br> 
 * Given the board<br>
 * 0 | 1 | 2<br>
 * ----------<br>
 * 3 | 4 | 5<br>
 * ----------<br>
 * 6 | 7 | 8<br>
 * <br>
 * the weight variable ranks the board. given the list<br>
 * {4,0,2,6,8,1,3,5,7}<br>
 * based on the below algorithm, if 4(center) is available it will take that first. 
 * followed by the corners (0,2,6,8) and finally the sides (1,3,5,7)
 * 
 * @author sean
 *
 */
public class AIMedium extends AIBase{

	private int[] rank = {4,0,2,6,8,1,3,5,7};
	private static final Logger logger = Logger.getLogger(AIMedium.class);
	
	public int optimalMove(final BoardState board) {
		int position;

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

		//search for the highest weighted postion
		for(position = 0; position < 9; position++){
			if(board.isValidMove(rank[position])){
				logger.debug("Found the best possible move @ " + rank[position]);
				return rank[position];
			}
		}
		
		return -1;//nothing found?
	}

	
}
