package DungeonsAndZombies;

public class Dungeon {
	Map map = new Map();
	
	public void spawn(Hero hero) {
		map.spawn(hero);
	}
	
	public void startGame() {
		map.startGame();
	}
	
	public static void main(String[] args) {
		Dungeon level1 = new Dungeon();
		Hero emo = new Hero("Emo", "Hacker", 100, 100, 2);
		
		Weapon sword = new Weapon("Sword of Seals", 50, 1);
		emo.equip(sword);
		Spell dark = new Spell("Luna", 50, 40, 2);
		emo.learn(dark);
		
		level1.spawn(emo);
		level1.startGame();
	}
}
