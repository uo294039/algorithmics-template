package algstudent.s6;

public class NumericSquare extends NumericSquareBase {
	public NumericSquare(String[][] board) {
		super(board);
	}
	@Override
	public void backtracking() throws SolutionException{ 
		int[] emptyCell = findEmptyCell(board);
        if (emptyCell == null) {
            throw new SolutionException();
        }

        int row = emptyCell[0];
        int col = emptyCell[1];
        
        for (int num = MIN_VALUE; num <= MAX_VALUE; num++) {
        	numberOfNodes++;
        	if (isValidMove(board, row, col, num)) {
                board[row][col] = String.valueOf(num);

                backtracking();

                board[row][col] = "?"; 
        	}
        	board[row][col] = "?";
        }
		
	}

	@Override
	public int getNumberOfSolutions() {
		return 1;
	}

}
