package com.sean;

import com.sean.controller.GameController;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Main program used to kick off Tic-Tac-Toe.<br>
 * pass -Dlog4j.configuration=<filename> to configure Log4J Appenders.
 * 
 * @author swhalley
 *
 */
public class TicTacToe {
	private static final Logger logger = Logger.getLogger(TicTacToe.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//configure logging
		if(System.getProperty("log4j.configuration") != null){
			PropertyConfigurator.configure(System.getProperty("log4j.configuration"));
		} else {
			BasicConfigurator.configure();
			Logger.getRootLogger().setLevel(Level.INFO);
		}
			
		logger.debug("Application Starting....");
		
		//kick it all off
		new GameController();

	}

}
