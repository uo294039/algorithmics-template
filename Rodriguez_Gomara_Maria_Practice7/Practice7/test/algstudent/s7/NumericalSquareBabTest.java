package algstudent.s7;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;

/**
 * JUnit Test for Numerical Square
 */
public class NumericalSquareBabTest {
	@Test
	public void test00() {
		boolean result = executeFromFile("src/algstudent/s7/test00.txt");
		assertEquals(true, result);
	}
	
	@Test
	public void test01() {
		boolean result = executeFromFile("src/algstudent/s7/test01.txt");
		assertEquals(true, result);
	}
	
	@Test
	public void test02() {
		boolean result = executeFromFile("src/algstudent/s7/test02.txt");
		assertEquals(true, result);
	}
	
	@Test
	public void test03() {
		boolean result = executeFromFile("src/algstudent/s7/test03.txt");
		assertEquals(true, result);
	}
	
	@Test
	public void test04() {
		boolean result = executeFromFile("src/algstudent/s7/test04.txt");
		assertEquals(true, result);
	}
	
	@Test
	public void test05() {
		boolean result = executeFromFile("src/algstudent/s7/test05.txt");
		assertEquals(true, result);
	}
	
	@Test
	public void test06() {
		boolean result = executeFromFile("src/algstudent/s7/test06.txt");
		assertEquals(true, result);
	}
	
	@Test
	public void test07() {
		boolean result = executeFromFile("src/algstudent/s7/test07.txt");
		assertEquals(true, result);
	}
	
	private boolean executeFromFile(String file) {
		boolean result = false;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			
			String size = br.readLine();
			int n = Integer.parseInt(size)*2 + 1; //size of the whole board
			
			GameBoard board = new GameBoard(n);
			
			
			for (int i=0; i<n; i++) {
				String[] values = br.readLine().split(" ");				
				board.insertValues(values, i);
			}
						
			NumericalSquareBab puzzle = new NumericalSquareBab(board);	
			puzzle.branchAndBound(puzzle.getRootNode()); 		
			puzzle.printSolutionTrace();
			System.out.println("Number of nodes %d".formatted(puzzle.getNumberOfNodes()));
			
			result = puzzle.getBestNode() != null ? true : false; 
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return result;
	}
	
}
