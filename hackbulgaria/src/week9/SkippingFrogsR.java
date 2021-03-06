package week9;

public class SkippingFrogsR {
	public static void solve() {
		char[]start = {'>', '>', '>', '_', '<', '<', '<'};
		int rock = 3;
		String path = ">>>_<<<\n";
		
		findSolution(start, path, rock);
		System.out.println(path);
	}
	
	private static void findSolution(char[] start, String path, int rock) {
		if (start.equals("<<<_>>>")) {
			return;
		}
		
		if (rock >= 1 && start[rock - 1] == '>') {
			start[rock - 1] = '_';
			start[rock] = '>';
			rock = rock - 1;
			
			path += new String(start) + "\n";
			findSolution(start.clone(), path, rock);
		}
		
		if (rock >= 2 && start[rock - 2] == '>'){
			start[rock - 2] = '_';
			start[rock] = '>';
			rock = rock - 2;
			
			path += new String(start) + "\n";
			findSolution(start.clone(), path, rock);
		}
	
		if (rock <= 5 && start[rock + 1] == '<') {
			start[rock + 1] = '_';
			start[rock] = '<';
			rock = rock + 1;
			
			path += new String(start) + "\n";
			findSolution(start.clone(), path, rock);
		}
		
		if (rock <= 4 && start[rock + 2] == '<') {
			start[rock + 2] = '_';
			start[rock] = '<';
			rock = rock + 2;
			
			path += new String(start) + "\n";
			findSolution(start.clone(), path, rock);
		}
	}
	
	public static void main(String[] args) {
		solve();
	}
}
