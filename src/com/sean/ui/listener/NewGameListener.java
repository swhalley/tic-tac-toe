package com.sean.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.sean.states.AILevel;
import org.apache.log4j.Logger;

import com.sean.controller.GameController;

/**
 * Action listener for "New Game" for Tic-Tac-Toe<br>
 * 
 * @author swhalley
 *
 */
public class NewGameListener implements ActionListener {
	private static final Logger logger = Logger.getLogger(NewGameListener.class);
	GameController gameController;
	
	public NewGameListener(GameController gc){
		gameController = gc;
	}
	
	/**
	 * the action performed method shows a popup window when the operation is choosen.
	 * The window will display all of the difficulties defined in AILevel to the user.
	 * When the user chooses an action and clicks ok, a new game is generated clearing the board and previous state.<br>
	 * If cancel is choosen, nothing happens
	 */
	public void actionPerformed(ActionEvent e) {
		AILevel difficulty = (AILevel) JOptionPane
				.showInputDialog(gameController.getWindow(), "Select the difficulty of the computer",
						"New Game", JOptionPane.PLAIN_MESSAGE, null, AILevel
								.values(), gameController.getAiEngine().getDifficulty());

		if (difficulty != null) {
			gameController.getBoardState().newGame();
			gameController.getAiEngine().setDifficulty(difficulty);
			gameController.getCenterPanel().clearButtons();
			gameController.getStatusBar().updateStatusBar();
			logger.debug("Start a new game: " + difficulty);
		}
	}

}
