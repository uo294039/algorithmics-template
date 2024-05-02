package algstudent.exam;

public class NumericSquareExam extends NumericSquareBaseExam {
	public NumericSquareExam(String[][] board) {
		super(board);
	}
	@Override
	public boolean backtracking() throws SolutionException{ 
		int[] emptyCell = findEmptyCell(board);
        if (emptyCell == null) {
            throw new SolutionException();
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
