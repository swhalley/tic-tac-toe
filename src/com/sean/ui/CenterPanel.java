package com.sean.ui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.sean.controller.GameController;
import com.sean.ui.listener.ButtonListener;

/**
 * GUI element for Tic-Tac-Toe. This generates the 9 squares for the application and adds their action listeners.
 * @author swhalley
 *
 */
public class CenterPanel extends JPanel {

	private JButton[] buttons = new JButton[9];
	
	public CenterPanel(GameController gameController){
		GridLayout layout = new GridLayout(3,3);
		setLayout(layout);
		
		
		for(int i=0; i<9; i++){
			buttons[i] = new JButton("");
			buttons[i].addActionListener(new ButtonListener(gameController));			
			buttons[i].setActionCommand(Integer.toString(i));
			
			this.add(buttons[i]);
		}
		
	}
	
	public void clearButtons(){
		for(int i=0; i<9; i++){
			buttons[i].setText("");
		}
	}
	
	public void setButton(int index, String text){
		if (index >=0 && index<9){
			buttons[index].setText(text);
		}
	}
	
}
