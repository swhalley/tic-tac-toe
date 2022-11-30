package com.sean.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sean.controller.GameController;

/**
 * Dont really need this. decided not to implement. 
 * @author swhalley
 *
 */
public class AboutListener implements ActionListener{
	
GameController gameController;
	
	public AboutListener(GameController gc){
		gameController = gc;
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}
