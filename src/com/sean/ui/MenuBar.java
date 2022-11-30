package com.sean.ui;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.sean.controller.GameController;
import com.sean.ui.listener.AboutListener;
import com.sean.ui.listener.ExitListener;
import com.sean.ui.listener.NewGameListener;

/**
 * Top menu bar with the help and file menus
 * @author swhalley
 *
 */
public class MenuBar extends JMenuBar {
	private JMenu file;
	private JMenu help;
	private JMenuItem game;
	private JMenuItem exit;
	private JMenuItem about;
	private NewGameListener newGameListener;
	private ExitListener exitListener;
	private AboutListener aboutListener;

	public MenuBar(GameController parent) {
		
		file = new JMenu("File");
		help = new JMenu("Help");

		game = new JMenuItem("New Game", KeyEvent.VK_N);
		newGameListener = new NewGameListener(parent);
		game.addActionListener(newGameListener);
		
		exit = new JMenuItem("Exit", KeyEvent.VK_X);
		exitListener = new ExitListener(parent);
		exit.addActionListener(exitListener);

		about = new JMenuItem("About", KeyEvent.VK_A);
		aboutListener = new AboutListener(parent);
		about.addActionListener(aboutListener);
		
		file.add(game);
		file.addSeparator();
		file.add(exit);
		help.add(about);

		this.add(file);
		this.add(help);
	}

}
