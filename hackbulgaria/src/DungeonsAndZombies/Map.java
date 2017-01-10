package DungeonsAndZombies;

import java.util.Scanner;

public class Map {
	//TODO TEST!!!!!!!!!!!!!!
	private class Position {
		int x;
		int y;
		
		public Position() {
			
		}
		
		private Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
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
	
	char[][] map = {
			{'S', '.', '#', '#', '.', '.', '.', '.', '.', 'T'},
			{'#', 'T', '#', '#', 'E', '.', '#', '#', '#', '.'},
			{'#', '.', '#', '#', '#', 'E', '#', '#', '#', 'E'},
			{'#', '.', 'E', '.', '.', '.', '#', '#', '#', '.'},
			{'#', '#', '#', 'T', '#', '#', '#', '#', '#', 'G'}
	};
	
	boolean mapCleared = false;
	Position coords = new Position();
	Hero hero;
	
	public void print() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("-------------------------------");
	}
	
	public boolean spawn(Hero hero) {
		this.hero = hero;
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
		int heroX = coords.getX();
		int heroY = coords.getY();
		
		boolean validMove = false;
		int[] movement = {0, 0};
		
		switch(direction) {
		case "up":
			if (heroX > 0 && map[heroX - 1][heroY] != '#') {
				movement[0] -= 1; 
				validMove = true;
			}
			break;
		case "down": 
			if (heroX < map.length && map[heroX + 1][heroY] != '#') {
				movement[0] += 1; 
				validMove = true;
			}
			break;
		case "left": 
			if (heroY > 0 && map[heroX][heroY - 1] != '#') {
				movement[1] -= 1; 
				validMove = true;
			}
			break;
		case "right": 
			if (heroY < map[0].length && map[heroX][heroY + 1] != '#') {
				movement[1] += 1; 
				validMove = true;
			}
			break;
		}
		
		if (validMove) {
			map[heroX][heroY] = '.';
			coords.setX(heroX + movement[0]);
			coords.setY(heroY + movement[1]);
			trigger(heroX + movement[0], heroY + movement[1]);			
		} else {
			System.out.println("Invalid move. Turn was wasted.");
		}
	}
	
	
	private void trigger(int x, int y){
		char tyle = map[x][y];
		map[x][y] = 'H';
		
		switch(tyle){
		case 'E':
			System.out.println("You encountered an enemy!");
			Helper.BattleManager.startBattle(hero, new Enemy(100, 100, 1));
			break;
		case 'T':
			System.out.println("You picked up a treasure!");
			Helper.Treasure.startTreasure(hero);
			break;
		case 'G':
			System.out.println("You cleared the map!");
			mapCleared = true;
			break;
		}
	}
	
	public void startGame() {
		print();
		Scanner s = new Scanner(System.in);
		while(hero.getHealth() > 0 && !mapCleared) {
			if (hero.getMana() < 100) {
				hero.takeMana(hero.getManaRegen());
				System.out.print(hero.knownAs() + " restored " + hero.getManaRegen() + " mp. ");
			}
			System.out.println("HP = " + hero.getHealth() + ", MP = " + hero.getMana() + ".");
			System.out.println("Currently equiped:");
			System.out.println("\t" + hero.weapon);
			System.out.println("\t" + hero.spell);
			
			int enemies = visibleEnemyInRange();
			if (enemies > 0) {
				suggestBattle(enemies);
			}
			System.out.print("Choose direction: ");
			String direction = s.nextLine();
			moveHero(direction);
			print();
		}
		
		if(mapCleared) {
			System.out.println(hero.knownAs() + " cleared the map!" );
		} else {
			hero.death();
		}
		s.close();
	}
	
	private int visibleEnemyInRange() {
		int enemy = 0;
		int range = hero.getRange();
		
		//makes an extra iteration when i = 0
		for (int i = -range; i <= range; i++) {
			if ((coords.y + i >= 0 && coords.y + i < map[0].length && map[coords.x][coords.y + i] == 'E') ||
					(coords.x + i >= 0 && coords.x + i < map.length && map[coords.x + i][coords.y] == 'E')) {
				enemy++;
			}
		}
		
		return enemy;
	}
	
	private void suggestBattle(int enemies) {
		//works through walls
		if (enemies == 1) {
			System.out.println("There is 1 enemy in sight. You could attack it once. Do you want to attack it?");
		} else {
			System.out.println("There are " + enemies + " in sight. Do you want to attack one of them?");
		}
		System.out.println("*Note that if you attack enemies from a distance they become aware of you and will chase you.");
		
		Scanner s = new Scanner(System.in);
		
		String answer = s.nextLine();
		while(!answer.equals("yes") && !answer.equals("no")) {
			System.out.println("Valid answers are \"yes\" and \"no\". Please enter a valid answer:");
			answer = s.nextLine();
		}
		
		if (answer.equals("yes")) {
			System.out.println("Please enter the direction of the enemy you would like to attack.");
			String direction = s.nextLine();
			if(isInvalidDirection(direction)) {
				System.out.println("Invalid asnwer. Valid directions are \"up\", \"down\", \"right\" and \"left\"." 
						+ " Please enter a valid direction.");
				while (isInvalidDirection(direction)) {
					direction = s.nextLine();
				}
			}
			Position enemy = findEnemy(direction);
			if (enemy == null) {
				System.out.println(hero.knownAs() + " turned " + direction + 
						". There was no enemy there and the hero wasted a turn.");
			} else {
				//TODO
				// i need enemy manager for attacking enemies without killing them
				// finally i wont pass them as arguments!!!
				Helper.BattleManager.startRangedBattle(hero, new Enemy(100, 100, 1));
				//what if hero dies?
				//what if enemy does not die
				map[enemy.x][enemy.y] = '.';
				print();
			}
		}
	}
	
	private boolean isInvalidDirection(String direction) {
		boolean result = true;
		if(direction.equals("up") || direction.equals("down") || direction.equals("right") || direction.equals("left")) {
			result = false;
		}
		
		return result;
	}
	
	private Position findEnemy(String direction) {
		//map[0].length is row length
		Position enemy = null;
		switch (direction) {
			case "right": 
				for (int i = 1; i <= hero.getRange(); i++) {
					if(coords.y + i < map[0].length && map[coords.x][coords.y + i] == 'E') {
						enemy = new Position(coords.x, coords.y + i);
						break;
					}
				}
				break;
			case "left": 
				for (int i = -hero.getRange(); i < 0; i++) {
					if(coords.y + i >= 0 && map[coords.x][coords.y + i] == 'E') {
						enemy = new Position(coords.x, coords.y + i);
						break;
					}
				}
				break;
			case "down": 
				for (int i = 1; i <= hero.getRange(); i++) {
					if(coords.x + i < map.length && map[coords.x + i][coords.y] == 'E') {
						enemy = new Position(coords.x + i, coords.y);
						break;
					}
				}
				break;
			case "up": 
				for (int i = -hero.getRange(); i < 0; i++) {
					if(coords.x + i >= 0 && map[coords.x + i][coords.y] == 'E') {
						enemy = new Position(coords.x + i, coords.y);
						break;
					}
				}
				break;
		}
		
		return enemy;
	}
}
