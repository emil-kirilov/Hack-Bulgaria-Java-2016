package DungeonsAndZombies;

public class Hero extends Unit{
	String name;
	String title;
	int manaRegen;

	public Hero(String name, String title, int hp, int mp, int manaRegen) {
		super(hp, mp);
		this.name = name;
		this.title = title;
		this.manaRegen = manaRegen;
	}

	public String knownAs() {
		return name + " the " + title;
	}
	
	@Override
	public int attack(String type) {
		int result = 2;
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
		System.out.println("The mighty hero " + name + " the " + title + " was killed!");
	}

}
