package com.sean.controller;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.sean.engine.AIEngine;
import com.sean.engine.BoardState;
import com.sean.states.AILevel;
import com.sean.ui.CenterPanel;
import com.sean.ui.MenuBar;
import com.sean.ui.StatusBar;
import org.apache.log4j.Logger;


/**
 * GameController is used to setup the main window and initialize all of the components 
 * which will be used throughout the application including the AIEngine and BoardState
 * @author swhalley
 *
 */
public class GameController {
	private static final Logger logger = Logger.getLogger(GameController.class);
	
	private JFrame window;
	private AIEngine aiEngine;
	private BoardState boardState;
	private MenuBar menuBar;
	private CenterPanel centerPanel;
	private StatusBar statusBar;

	
	public GameController() {
		logger.debug("initializing the game engine and board state");
		aiEngine = new AIEngine(AILevel.EASY);
		boardState = new BoardState();
				
		logger.debug("Setting up the window");
		window = new JFrame("Tic Tac Toe");
		
		menuBar = new MenuBar(this);
		centerPanel = new CenterPanel(this);
		statusBar = new StatusBar(this);
		
		window.setJMenuBar(menuBar);
		window.add(centerPanel, BorderLayout.CENTER);
		window.add(statusBar, BorderLayout.SOUTH);
		
		window.setSize(400, 400);
		window.setLocation(200, 200);
		window.setResizable(false);		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		logger.debug("Done initializing the application");
	}

	
	public JFrame getWindow() {
		return window;
	}

	public void setWindow(JFrame window) {
		this.window = window;
	}

	public AIEngine getAiEngine() {
		return aiEngine;
	}

	public void setAiEngine(AIEngine aiEngine) {
		this.aiEngine = aiEngine;
	}

	public BoardState getBoardState() {
		return boardState;
	}

	public void setBoardState(BoardState boardState) {
		this.boardState = boardState;
	}

	public CenterPanel getCenterPanel() {
		return centerPanel;
	}

	public void setCenterPanel(CenterPanel centerPanel) {
		this.centerPanel = centerPanel;
	}

	public StatusBar getStatusBar() {
		return statusBar;
	}

	public void setStatusBar(StatusBar statusBar) {
		this.statusBar = statusBar;
	}
	
	
	
}
