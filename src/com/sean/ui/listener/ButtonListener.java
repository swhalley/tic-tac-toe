package com.sean.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.sean.controller.GameController;
import com.sean.states.GameState;
import org.apache.log4j.Logger;

/**
 * The button listener class handles clicks of the TicTacToe squares.
 * When a user clicks the button the listener will check if the game is active and the square is free. 
 * If the square is free then the X piece is placed. The listener then checks again the status of the
 * game again and performs a Move for the Computer player if needed. <br>
 * Last the status bar is updated to reflect the current status of the game. 
 * @author swhalley
 *
 */
public class ButtonListener implements ActionListener{
	private static final Logger logger = Logger.getLogger(ButtonListener.class);
	GameController gameController;
	
	//HTML text to display formatted buttons.
	private static final String X = "<html><font color=\"black\" size=\"15\">X</font><html>"; 
	private static final String O = "<html><font color=\"red\" size=\"15\">O</font><html>";
	
	public ButtonListener(GameController gc){
		gameController = gc;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		logger.debug("Clicked the button @ " + e.getActionCommand());
		
		String sPos = e.getActionCommand();
		int xPosition = Integer.parseInt(sPos);
		
		if( gameController.getBoardState().addXPiece(xPosition) ){
			((JButton)e.getSource()).setText(X);
			logger.debug("Player move was valid @ " + xPosition);
			
			//Do the Computers Move
			if(GameState.ACTIVE == gameController.getBoardState().getGameState()){
				logger.debug("Game is active. Beggining computer AI");
				int oPosition = gameController.getAiEngine().getMove(gameController.getBoardState());
				if(oPosition != -1){
					gameController.getBoardState().addOPiece(oPosition);
					gameController.getCenterPanel().setButton(oPosition, O);
					logger.debug("Computer AI picked square @ " + oPosition);
				}
			}
		} else {
			logger.debug("Button click @ " + xPosition + " was not valid");
		}
		
		//update the status bar
		gameController.getStatusBar().updateStatusBar();
				
	}
}
