package com.sean.engine;

import com.sean.states.AILevel;
import org.apache.log4j.Logger;

import com.sean.engine.ai.AIBase;
import com.sean.engine.ai.AIEasy;
import com.sean.engine.ai.AIHard;
import com.sean.engine.ai.AIMedium;

/**
 * The AIEngine is the class which controls the difficulty level of the computer player.
 * The class also directs the abstract class AIBase to determine which algorithm is used.
 * @author swhalley
 *
 */
public class AIEngine {

	private AILevel difficulty;
	private static Logger logger = Logger.getLogger(AIEngine.class);

	/**
	 * The constructer sets the AI level of the engine.
	 * @param ail
	 */
	public AIEngine(AILevel ail) {
		difficulty = ail;
	}

	/**
	 * getMove uses the abstract class AIBase and a switch statement to determine which 
	 * Alogrithm will be used to determin the next move.
	 * @param board BoardState is a snapshot of the board and any moves which may have already been played.
	 * @return An Integer value for the move to play. -1 is returned for any errors.
	 */
	public int getMove(final BoardState board) {
		AIBase ai = null;

		logger.debug("AI Move requested - difficulty:: " + difficulty);
		
		switch (difficulty) {
		case EASY:
			ai = new AIEasy(); break;
		case MEDIUM:
			ai = new AIMedium(); break;
		case HARD:
			ai = new AIHard(); break;
		default:
			logger.error("No Difficulty was defined");
			return -1; // error, no difficulty passed
		}

		return ai.optimalMove(board);
	}

	public AILevel getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(AILevel difficulty) {
		this.difficulty = difficulty;
	}
}
