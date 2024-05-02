package algstudent.s6;

public class NumericSquareAll extends NumericSquareBase {
	private int numberOfSolutions;
	
	public NumericSquareAll(String[][] board) {
		super(board);
		this.numberOfSolutions = 0;
	}
	@Override
	public  void backtracking() throws SolutionException{ 
		int[] emptyCell = findEmptyCell(board);
        if (emptyCell == null) {
            numberOfSolutions++;
            System.out.println(numberOfSolutions);
            //throw new SolutionException();
            return;
        }

        int row = emptyCell[0];
        int col = emptyCell[1];
        
        for (int num = MIN_VALUE; num <= MAX_VALUE; num++) {
        	numberOfNodes++;
        	if (isValidMove(board, row, col, num)) {
                board[row][col] = String.valueOf(num);

                backtracking();

                //board[row][col] = "?"; 
        	}
        	
        }
        board[row][col] = "?";
		
	}

	@Override
	public int getNumberOfSolutions() {
		return numberOfSolutions;
	}
	
	

}
