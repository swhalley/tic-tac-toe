package com.sean.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sean.controller.GameController;

/**
 * If exit was choosen then exit.
 * @author swhalley
 *
 */
public class ExitListener implements ActionListener {
	GameController gameController;

	public ExitListener(GameController gc) {
		gameController = gc;
	}

	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
