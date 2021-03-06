package DungeonsAndZombies;

public class Weapon implements Treasureable{
	String name;
	int damage;
	int range = 1;
	
	public Weapon(String name, int damage, int range) {
		this.name = name;
		this.damage = damage;
		this.range = range;
	}
	
	public String toString() {
		return "the weapon " + name + ". It does " + damage + " damage and has a range of " + range + "!";
	}
	
	public void activate(Hero h) {
		h.equip(this);
		System.out.println(h.knownAs() + " equiped " + name + "!");
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getRange() {
		return range;
	}
}
