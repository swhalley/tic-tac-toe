package com.sean.ui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.sean.controller.GameController;

/**
 * Bottom status bar which shows the status and difficulty of the current game
 * @author swhalley
 *
 */
public class StatusBar extends JPanel{
	private static final Logger logger = Logger.getLogger(StatusBar.class);
	private final static String STATUS_LABEL = "Game Status: ";
	private final static String DIFFICULTY_LABEL = "Difficulty: ";
	
	private JLabel status;
	private JLabel difficulty;
	private GameController gameController;
	
	public StatusBar(GameController gameController){
		logger.debug("Initializing Status bar");
		this.gameController = gameController;
		status = new JLabel(STATUS_LABEL + gameController.getBoardState().getGameState().toString());
		difficulty = new JLabel(DIFFICULTY_LABEL + gameController.getAiEngine().getDifficulty());
		
		setLayout(new GridLayout(0,2));
		add(status);
		add(difficulty);
	}


	public void updateStatusBar() {
		status.setText( STATUS_LABEL + gameController.getBoardState().getGameState());	
		difficulty.setText(DIFFICULTY_LABEL + gameController.getAiEngine().getDifficulty());
		logger.debug("Status bar updated");
	}
	
	
	
	
}
