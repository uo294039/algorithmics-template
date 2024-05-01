package algstudent.s7;

import java.util.ArrayList;
import java.util.UUID;



public class NumericalSquareBab extends BranchAndBound {	
	/**
	 * Constructor for Numerical Square objects
	 * @param board Representation of the board for playing the puzzle
	 */
    public NumericalSquareBab(GameBoard board) {
    	rootNode = board; //we create the puzzle to start playing
    }
}
/***************************************************/


/***************************************************/
@SuppressWarnings("unused")
class GameBoard extends Node {
	private String[][] board; //board for playing
	private int row; //current row of this board
	private int column; //current column of this board

	/**
	 * Constructor for Pyramid puzzle objects (root node)
	 * @param n Size of the board
	 */
	public GameBoard(int n) { //Generates an empty board	 	
		board = new String[n][n];
		row = n-1;
		column = n-1;
	}
	
	/**
	 * Inserts the values of a line from the board
	 * It is call once per every row of the board to initialize all the values
	 * @param values Values of a row of the board
	 * @param row Number of the current row
	 */
	public void insertValues(String[] values, int row) {
		if(row % 2 == 0 && row != board.length-1) {
			for (int i=0; i<board.length; i++) { 
				board[row][i] = values[i];
			}
		}
		else {
			int count = 0;
			for (int i=0; i<board.length; i++) { 
				if(i % 2 == 0 && i < board.length-2) {
					board[row][i] = values[count];
					count++;
				}
				else {
					board[row][i] = "%";//to avoid nulls on the board
				}
				
			}
		}
		
	}
		
    @Override
    public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<board.length; i++) { 
			for(int j = 0; j < board.length; j++) {
				if(board[i][j].equals("%")) {
					sb.append("  ");
				}
				else {
					sb.append(board[i][j] + " ");
				}
			}
			sb.append("\n");
		}
		return sb.toString();
    }

    /**
     * Counts the number of blanks that are not yet filled
     */
    @Override
    public void calculateHeuristicValue() {
    	int counter = 0;
    	if(prune()) {
    		heuristicValue = Integer.MAX_VALUE; //with this value the node won't be added in the priority queue
    	}
    	else {
    		for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) { 
					if(board[i][j].equals("?")) counter++;
				}
			}
    		heuristicValue = counter;
    	}
    }
    
	/**
	 * Checks if we should prune when the conditions are not longer met
	 * @return True if we should prune. False otherwise
	 */
	private boolean prune() {//Correct equations isValid()
		String[] col = new String[board.length];
		for(int i = 0; i < board.length; i++) {
			col[i] = board[i][column];
		}
		if(checkIndividual(board[row]) && checkIndividual(col)) {
			return false;
		}
		return true;

	}
	
	boolean checkIndividual(String[] aux) {
		boolean aux1 = true;
	
		for(int i=0; i < aux.length-2;i++) {
			if(aux[i].equals("?")) {
				aux1 = false;
			}
		}
		if(!aux1) {
			return true;
		}
		else {
			int solution = Integer.parseInt(aux[aux.length - 1]);
			ArrayList<Integer> numbers = new ArrayList<Integer>();
			ArrayList<String> operations = new ArrayList<String>();
			for(int i=0; i < aux.length-2;i++) {
					if(i % 2 == 0) {
						numbers.add(Integer.parseInt(aux[i]));
					}
					else {
						operations.add(aux[i]);
					}
			}
			double partial = numbers.get(0);
			for(int k=0; k < operations.size(); k++) {
				if(operations.get(k).equals("+")) {
					partial += numbers.get(k+1);
				}
				else if(operations.get(k).equals("-")) {
					partial -= numbers.get(k+1);
				}
				else if(operations.get(k).equals("*")) {
					partial *= numbers.get(k+1);
				}
				else if(operations.get(k).equals("/")){
					try {
						partial /= numbers.get(k+1);
					}
					catch (ArithmeticException e) {
						return false;
					}
				}
			}
			
			if(solution != partial) {
				return false;
			}
			else {
				return true;
			}
		}
		
	}
	
	@Override
	public boolean isSolution() {
		return heuristicValue == 0;
	}
    
	/**
	 * To get the children of the current node. They 
     * point to their parent through the parentID
	 */
	@Override
	public ArrayList<Node> expand() {
		ArrayList<Node> result = new ArrayList<Node>();
		String[][] newBoard;
		GameBoard temp;
		
		int[] coordinate = findEmptyCell(board);
		row = coordinate[0];
		column = coordinate[1];
		
		for (int k = 0; k <= 9; k++) {
			newBoard = copyBoard(row, column, k);
			temp = new GameBoard(newBoard,depth+1,this.getID(),row,column);//FALLA AQUI!!!!!
			result.add(temp);
		}

		return result;
	}
	
	static int[] findEmptyCell(String[][] board) {
		for(int i = 0; i< board.length-1; i+=2) {
			for(int j = 0; j < board[0].length; j++) {
				if(board[i][j].equals("?")) {
					return new int[] {i,j};
				}
			}
		}
		return null;
	}
	
	private String[][] copyBoard(int row, int column, int k) {
		String[][] newBoard = new String[board.length][board.length];
		for (int i=0; i<board.length; i++) 
			for (int j=0; j<board.length; j++)
				newBoard[i][j] = board[i][j];				      
		
		newBoard[row][column] = String.valueOf(k);	
		return newBoard;
	}
	
	/**
	 * Constructor for Numerical square objects (children of the root node)
	 * @param board
	 * @param depth
	 * @param parentID
	 */
    public GameBoard(String[][] board, int depth, UUID parentID, int row, int column) {
    	this.board = board;
    	this.depth = depth;
    	this.parentID = parentID;
    	this.row = row;
    	this.column = column;
    	
    	calculateHeuristicValue(); 
    	//we know if the node should be inserted in the priority queue and in which position.
    }

} 


