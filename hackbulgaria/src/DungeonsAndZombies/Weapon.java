package DungeonsAndZombies;

public class Weapon implements Treasureable{
	String name;
	int damage;
	
	public Weapon(String name, int damage) {
		this.name = name;
		this.damage = damage;
	}
	
	public String toString() {
		return "the weapon " + name + ". It does " + damage + " damage!";
	}
	
	public void activate(Hero h) {
		h.equip(this);
		System.out.println(h.knownAs() + "equiped" + name + "!");
	}
}
