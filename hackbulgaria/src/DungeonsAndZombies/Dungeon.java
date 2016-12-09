package DungeonsAndZombies;

public class Dungeon {
	private class Position {
		int x;
		int y;
		
		public void setX(int x) {
			this.x = x;
		}
		
		public void setY(int y) {
			this.y = y;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
	}
	
	Position coords = new Position();
	char[][] map = {
			{'S', '.', '#', '#', '.', '.', '.', '.', '.', 'T'},
			{'#', 'T', '#', '#', '.', '.', '#', '#', '#', '.'},
			{'#', '.', '#', '#', '#', 'E', '#', '#', '#', 'E'},
			{'#', '.', 'E', '.', '.', '.', '#', '#', '#', '.'},
			{'#', '#', '#', 'T', '#', '#', '#', '#', '#', 'G'}
	};
	
	
	
	public void printMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public boolean spawn(Hero hero) {
		boolean success = false;
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if ( map[i][j] == 'S' || map[i][j] == '.' ) {
					map[i][j] = 'H';
					coords.setX(i);
					coords.setY(j);
					success = true;
					break;
				}
				if (success) {
					break;
				}
			}
		}
		
		return success;
	}
	
	public void moveHero(String direction) {
		switch(direction) {
		case "up": 
			if (coords.getX() > 0 && map[coords.getX() - 1][coords.getY()] != '#') {
				map[coords.getX()][coords.getY()] = '.';
				coords.setX(coords.getX() - 1);
				trigger(coords.getX() - 1, coords.getY());
			}
			break;
		case "down": 
			if (coords.getX() < map[0].length && map[coords.getX() + 1][coords.getY()] != '#') {
				map[coords.getX()][coords.getY()] = '.';
				coords.setX(coords.getX() + 1);
				trigger(coords.getX() + 1, coords.getY());
			}
			break;
		case "left": 
			if (coords.getY() > 0 && map[coords.getX()][coords.getY() - 1] != '#') {
				map[coords.getX()][coords.getY()] = '.';
				coords.setY(coords.getY() - 1);
				trigger(coords.getX(), coords.getY() - 1);
			}
			break;
		case "right": 
			if (coords.getY() < map.length && map[coords.getX() - 1][coords.getY() + 1] != '#') {
				map[coords.getX()][coords.getY()] = '.';
				coords.setY(coords.getY() + 1);
				trigger(coords.getX(), coords.getY() + 1);
			}
			break;
		}
	}
	
	private void trigger(int x, int y){
		char tyle = map[x][y];
		map[x][y] = 'H';
		
		switch(tyle){
		case 'E':
			break;
		case 'T':
			break;
		case 'G':
			break;
		}
	}

	
	public static void main(String[] args) {
		Dungeon level1 = new Dungeon();
		Hero emo = new Hero("Emo", "Hacker", 100, 100, 2);
		level1.spawn(emo);
		level1.printMap();
		
		while(emo.getHealth() > 0) {
			
		}
	}
}
