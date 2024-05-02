package algstudent.s6;

import java.util.ArrayList;

public abstract class NumericSquareBase {
	
	String[][] board;
	static final int MIN_VALUE = 0;
	static final int MAX_VALUE = 9;
	
	public NumericSquareBase(String[][] board) {
		this.board = board;
	}

	public String[][] getSol() {
		return board;
	}

	public abstract boolean backtracking() throws SolutionException;
	
	boolean isValidMove(String[][] board, int row, int col, int num) {
		String[][] auxBoard = board.clone();
		auxBoard[row][col] = String.valueOf(num);
		String[] column = new String[board.length];
		for(int i = 0; i < board.length; i++) {
			column[i] = auxBoard[i][col];
		}
		if(checkIndividual(auxBoard[row]) && checkIndividual(column)) {
			return true;
		}
		return false;
	}

	public abstract int getNumberOfSolutions();
	
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

	

}
