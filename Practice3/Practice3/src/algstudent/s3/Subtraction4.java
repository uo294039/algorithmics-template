package algstudent.s3;

public class Subtraction4 {
	
	static long cont;
	public static long rec4(int n) {
		if (n <= 0)
			cont++;
		else {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					cont++; // O(n2)
				}
			}
				
			rec4(n - 1);
			for (int i = 0; i < n; i++)
				cont++; // O(n)
		}
		return cont;
	}

	public static void main(String arg[]) {
		long t1, t2 = 0;
		int nTimes = Integer.parseInt (arg [0]);
		
		for (int n = 100; n <= Integer.MAX_VALUE; n *= 2) {
			t1 = System.currentTimeMillis();

			for (int reps=1; reps<=nTimes;reps++)
			{ 
				cont = rec4(n);
			} 			

			t2 = System.currentTimeMillis();

			System.out.println("n=" + n + "**TIME=" + (t2 - t1) + "**cont=" + cont);
		} // for
	} // main

}
