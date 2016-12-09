package DungeonsAndZombies;

public class Enemy extends Unit{
	int damage;
	
	public Enemy(int hp, int mp, int dmg) {
		super(hp, mp);
		damage = dmg;
	}


	@Override
	public int attack(String type) {
		int result = damage;
		switch (type) {
		case "weapon": 
			if (weapon != null) {
				result = weapon.damage;
			}
			break;
		case "magic": 
			if (spell != null) {
				result = spell.damage;
			}
			break;
		}
		
		return result;
	}


	@Override
	public void death() {
		System.out.println("An enemy unit was killed!");
	}
	
	

}
