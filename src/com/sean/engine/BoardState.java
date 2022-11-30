package com.sean.engine;


import com.sean.states.GameState;

/**
 * Game engine for a tic tac toe problem. <br>
 * Based on a game board labeled 0-8 <br>
 * 0 | 1 | 2<br>
 * ----------<br>
 * 3 | 4 | 5<br>
 * ----------<br>
 * 6 | 7 | 8<br>
 * <br>
 * Uses bitwise operators to perform quick compare and analysis of game state and moves. <br>
 * Each players board is stored in an Integer value, but the operations are performed in binary. <br>
 * The binary representation of the above table would look like the following.<br>
 * | 8 | 7 | 6 | 5 | 4 | 3 | 2 | 1 | 0 |<br>
 * So if X has a piece in spots 0, 4 and 6. His binary representation would look like: <br>
 * | 0 | 0 | 1 | 0 | 1 | 0 | 0 | 0 | 1 |<br>
 * And the value stored for the xBoard would be the decimal number (2^0 + 2^4 + 2^6) = 73  
 * 
 * @author swhalley
 *
 */
public class BoardState {
	private boolean playerTurn = true;
	private int xBoard;
	private int oBoard;
	private static boolean[] winScenario = new boolean[1<<9]; //512 possible board combinations
    private static final int FULL_BOARD = (1 << 9) - 1; //binary is 111111111
    

	public BoardState(){
		newGame();
	}	
	
	/**
	 * Clear the game board. And start from scratch
	 */
	public void newGame(){
		xBoard = 0;
		oBoard = 0;
		playerTurn = true;
	}
	
	
	/**
	 * The method first checks to see if either the xBoard or oBoard match any of the 
	 * possible winning scenarios. This is simply done be passing the value of the xBoard 
	 * or oBoard to the array and checking if its value is true.<br>
	 * A check is then done to see if the game board is full. using the bitwise operator for
	 * OR (|) we can add the X and O boards together and see if all bits are 1's This is done
	 * by comparing against the maximum size of the board.<br>
	 * Example: xBoard = 101010101, oBoard = 010101010, MAX = (1 << 9) - 1<br>
	 * if((xBoard | oBoard)== MAX)<br>
	 * if( (101010101 | 010101010) == (1 << 9) - 1)<br>
	 * if( (101010101 | 010101010) == 1000000000 - 1)<br>
	 * if( 111111111 == 111111111)<-- Full board <br>
	 * <br>
	 * Otherwise return that more moves are possible and the game continues.
	 * @return GameState object. Could be X or O Win, Draw, or Active game
	 */
	public GameState getGameState(){
		//check if X or O has won
		if(winScenario[xBoard])
			return GameState.XWIN;
		if(winScenario[oBoard])
			return GameState.OWIN;
		
		//Check for a full board
		if((xBoard | oBoard)== FULL_BOARD)
			return GameState.DRAW;
		
		//keep playing there are more moves
		return GameState.ACTIVE;
	}
	
	
	/**
	 * Use the Bitwise operator OR (|) to add the bit to the players board if the
	 * space is not already occupied.<br>
	 * example: player X currently has a board looking like: 000000001 (i.e X in top right corner)<br>
	 * They attempt to add the next X to the bottom right corner (position 8), assuming that the
	 * isValid check returns true, we then use the Bitwise Or to add to the correct spot. Here is the Math<br>
	 * xBoard |= 1 << 8<br>
	 * xBoard = xBoard | (1 <<8)<br>
	 * xBoard = 0000000001 | 100000000<br>
	 * xBoard = 100000001<br>
	 * @param position
	 * @return True if piece was added, False otherwise
	 */
	public boolean addXPiece(int position){
		if(isValidMove(position) && playerTurn){
			xBoard |= 1 << position;
			playerTurn = false;
			return true;
		}
		return false;
	}
	
	public void removeXPiece(int position){
			xBoard ^= 1 << position;
	}
	
	/**
	 * 
	 * @return The integer value of the O-Players board
	 */
	public int getXBoard(){
		return xBoard;
	}
	
	public void setXBoard(int state){
		xBoard = state;
	}
	
	/**
	 * See documentation of addXPiece() for full explanation.
	 * @param position
	 * @return True if piece was added, False otherwise
	 */
	public boolean addOPiece(int position){
		if(isValidMove(position) && !isPlayerTurn()){
			oBoard |= 1 << position;
			playerTurn = true;
			return true;
		}
		return false;
	}
	
	public void removeOPiece(int position){
		oBoard ^= 1 << position;
	}
	
	/**
	 * 
	 * @return The integer value of the O-Players board
	 */
	public int getOBoard(){
		return oBoard;
	}
	
	public void setOBoard(int state){
		oBoard = state;
	}
	
	public boolean[] getWinScenario() {
		return winScenario;
	}

	

	public boolean isPlayerTurn() {
		return playerTurn;
	}
	
	public void setPlayerTurn(boolean flag){
		playerTurn = flag;
	}

	/** 
	 * This method will determine if the move requested by the player is legal.<br>
	 * The first thing to check is the game state. If X/O have already won, or the board
	 * is full we should not allow a move.<br>
	 * We then confirm the move is on the board, position 0-8<br>
	 * Then the bitwise operator will do some work. We add the X and O boards together
	 * and then check to see if that position is already occupied. Here is a math example:<br>
	 * oBoard = 011000000<br>
	 * xBoard = 100100000<br>
	 * Player requests to put an entry in space 7 (lower center) of the board.<br>
	 * The function then becomes the following based on bitwise operations.<br>
	 * if( ((011000000 | 100100000) & 001000000) != 0)<br>
	 * if( (111100000 & 001000000) != 0) <-- result of Bitwise OR<br>
	 * if( 001000000 != 0) <-- result of Bitwise AND<br>
	 * if ( 128 != 0) <-- convert back to decimal(2^7) for easier reading.<br>
	 * By having a bit there, that shows that the position 7 is already taken and will return false.
	 * @param position
	 * @return True if the move is ok, false otherwise. Will also check game state
	 */
	public boolean isValidMove(int position){
		if(getGameState() != GameState.ACTIVE)
			return false;
		if(position < 0 || position > 8)
			return false;
		if( ((oBoard | xBoard) & (1 << position)) != 0)
			return false;
		return true;
	}
	
	
	/**
	 * Static initialization of the possible win scenarios
	 * @param position
	 */
	private static void populateWinScenario(int position) {
    	for (int i = 0 ; i < FULL_BOARD ; i++) {
    	    if ((i & position) == position)
    	    	winScenario[i] = true;
    	}
    }

	//values are based on all possible up/down/diagnol scenarios of a board 
	//labeled 0(top left) to 8(bottom right) 
	//{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}
	static {
		populateWinScenario( 1<<0 | 1<<1 | 1<<2 );
		populateWinScenario( 1<<3 | 1<<4 | 1<<5 );
		populateWinScenario( 1<<6 | 1<<7 | 1<<8 );
		populateWinScenario( 1<<0 | 1<<3 | 1<<6 );
		populateWinScenario( 1<<1 | 1<<4 | 1<<7 );
		populateWinScenario( 1<<2 | 1<<5 | 1<<8 );
		populateWinScenario( 1<<0 | 1<<4 | 1<<8 );
		populateWinScenario( 1<<2 | 1<<4 | 1<<6 );
	}
}
