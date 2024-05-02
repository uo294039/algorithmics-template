package algstudent.exam;

public class NumericSquareAllExam extends NumericSquareBaseExam {
	private int numberOfSolutions;
	
	public NumericSquareAllExam(String[][] board) {
		super(board);
		this.numberOfSolutions = 0;
	}
	@Override
	public boolean backtracking() throws SolutionException{ 
		int[] emptyCell = findEmptyCell(board);
        if (emptyCell == null) {
            numberOfSolutions++;
            System.out.println(numberOfSolutions);
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
        	
        }
        board[row][col] = "?";
        return false;
		
	}

	@Override
	public int getNumberOfSolutions() {
		return numberOfSolutions;
	}
	
	

}
