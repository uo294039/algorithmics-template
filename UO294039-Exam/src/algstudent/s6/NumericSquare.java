package algstudent.s6;

public class NumericSquare extends NumericSquareBase {
	public NumericSquare(String[][] board) {
		super(board);
	}
	@Override
	public boolean backtracking(){ 
		int[] emptyCell = findEmptyCell(board);
        if (emptyCell == null) {
            return true;
        }

        int row = emptyCell[0];
        int col = emptyCell[1];
        
        for (int num = MIN_VALUE; num <= MAX_VALUE; num++) {
        	if (isValidMove(board, row, col, num)) {
                board[row][col] = String.valueOf(num);

                backtracking();

                board[row][col] = "?"; 
        	}
        	board[row][col] = "?";
        }
        return false;
		
	}

	@Override
	public int getNumberOfSolutions() {
		return 1;
	}

}
