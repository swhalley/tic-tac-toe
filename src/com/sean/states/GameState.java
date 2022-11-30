package com.sean.states;

/**
 * The Game state object holds the various states which the game can be in
 * XWIN - Player X has Won
 * OWIN - Player O has Won
 * DRAW - Board is full and there is no further moves
 * ACTIVE - There are free spaces on the board and the game should continue.
 * 
 * @author swhalley
 *
 */
public enum GameState {
	XWIN, OWIN, DRAW, ACTIVE;
}
