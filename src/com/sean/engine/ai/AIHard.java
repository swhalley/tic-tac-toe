package com.sean.engine.ai;

import com.sean.engine.BoardState;
import com.sean.states.GameState;
import org.apache.log4j.Logger;

/**
 * Unbeatable algorithm for Tic-Tac-toe.
 * Using Minimax
 * @author swhalley
 *
 */
public class AIHard extends AIBase{
	private static final Logger logger = Logger.getLogger(AIHard.class);
	
	/**
	 * The method optimal move checks for a win then a block. If no win or block is found
	 * then the game will determine the position based on the Minimax algorithm
	 * 
	 */
	public int optimalMove(BoardState board) {
			int pos;

			if ((pos = checkForWin(board)) != -1)
				return pos;
			if ((pos = checkForBlock(board)) != -1)
				return pos;

			pos = MiniMax(board);
			return pos;
	}

	/**
	 * Not going to explain this. RTFM http://en.wikipedia.org/wiki/Minimax
	 * @param board
	 * @return
	 */
	private int MiniMax(BoardState board) {
		//create a temprary board to pass around
		//stupid pass by reference....grumbles....
		BoardState state = new BoardState();
		state.setOBoard(board.getOBoard());
		state.setXBoard(board.getXBoard());
		state.setPlayerTurn(false); //it is the CPU turn
		
		int bestMove = -1; //some random seed number
		int bestScore = -5; //some random seed number
		
		logger.info("@ Start Minimax, XBoard = " + state.getXBoard());
		logger.info("@ Start Minimax, OBoard = " + state.getOBoard());
		
		long start = System.currentTimeMillis();
		for(int i=0; i<9; i++){
			if(!state.isValidMove(i))
				continue;
			
			state.addOPiece(i);
			int score = Min(state);
			if(score > bestScore){
				bestScore = score;
				bestMove = i;
			}
			state.removeOPiece(i);
			state.setPlayerTurn(false);
		}
		long end = System.currentTimeMillis();
		
		logger.info(">>>Minimax returning: " + bestMove + " in " + ((end-start)/1000) + " seconds");
		return bestMove;
	}
	
	private int Max(BoardState boardState){
		BoardState state = new BoardState();
		state.setOBoard(boardState.getOBoard());
		state.setXBoard(boardState.getXBoard());
		state.setPlayerTurn(false);
		
		int bestScore = -1;
		int score;
		
		score = Evaluate(state);
		
		if ( state.getGameState() != GameState.ACTIVE)
			return score; //O can win. or this is a draw
		
		for(int i=0; i<9; i++){
			if(!state.isValidMove(i))
				continue;
			state.addOPiece(i);
			score = Min(state);
			
			if(score > bestScore)
				bestScore = score;
			//clean up and check the next node
			state.removeOPiece(i);
			state.setPlayerTurn(false);
		}
		
		logger.debug("Max returning: " + bestScore);
		return bestScore;
	}
	
	
	/**
	 * Player perspective. Lets minimize their damage
	 * @param boardState
	 * @return
	 */
	private int Min(BoardState boardState){
		BoardState state = new BoardState();
		state.setOBoard(boardState.getOBoard());
		state.setXBoard(boardState.getXBoard());
		state.setPlayerTurn(true);
		
		int bestScore = 1;
		int score;
		
		score = Evaluate(state);
		
		if ( state.getGameState() != GameState.ACTIVE)
			return score; //X can win. or this is a draw
		
		for(int i=0; i<9; i++){
			if(!state.isValidMove(i))
				continue;
			state.addXPiece(i);
			score = Max(state);
			
			if(score < bestScore)
				bestScore = score;
			//cleanup and test the nest square
			state.removeXPiece(i);
			state.setPlayerTurn(true);
		}	
		
		logger.debug("Min Returning: " + bestScore );
		return bestScore;
	}
	
	/**
	 * helper for minimax.
	 * @param state
	 * @return Return 1 for Computer win. -1 for Player win. 0 otherwise.
	 */
	private int Evaluate(BoardState state){
		switch(state.getGameState()){
		case OWIN: return 1;
		case XWIN: return -1;
		default: return 0;
		}
	}
}
