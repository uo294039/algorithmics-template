package algstudent.s5;

public class PatternMatching {
	
	private String text;
	private boolean[][] table;
	private String pattern;

	public PatternMatching(String text) {
		this.text = text;
	}

	public boolean checkPattern(String pattern) {
		this.pattern = pattern;
		table = new boolean[text.length()+1][pattern.length()+1];
		table[0][0] = true;
		for(int i = 1; i < table.length; i++) {
			for(int j = 1; j < table[0].length; j++) {
				if(pattern.charAt(j-1) == text.charAt(i -1 )) {
					table[i][j] = table[i-1][j-1];
				}
				else {
					if(pattern.charAt(j-1) == '?') {
						if(table[i-1][j-1]) {
							table[i][j] = true;
						}
						else if(table[i][j-1]) {
							table[i][j] = true;
						}
					}
					else if(pattern.charAt(j-1) == '*') {
						if(table[i-1][j-1]) {
							table[i][j] = true;
						}
						else if(table[i][j-1]) {
							table[i][j] = true;
						}
						else if(table[i-1][j]) {
							table[i][j] = true;
						}
					}
				}
			}
		}
		return table[text.length()][pattern.length()];
		
	}

	public void printsTable() {
		System.out.print("\t");
		for(int i=0; i < pattern.length(); i++) {
			System.out.print("\t" + pattern.charAt(i));
		}
		System.out.println();
		for(int i=0; i < table.length; i++) {
			if(i!=0) {
				System.out.print(text.charAt(i-1));
			}
			
			for(int j=0; j < table[0].length; j++) {
				System.out.print("\t" + table[i][j]);
			}
			System.out.println();
		}
	}

}
