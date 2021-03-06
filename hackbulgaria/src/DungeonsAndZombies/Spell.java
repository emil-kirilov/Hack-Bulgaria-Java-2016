package DungeonsAndZombies;

public class Spell implements Treasureable{
	String name;
	int damage;
	int manaCost;
	int range;
	
	public Spell(String name, int damage, int manaCost, int range) {
		this.name = name;
		this.damage = damage;
		this.manaCost = manaCost;
		this.range = range;
	}
	
	public String toString() {
		return "the spell " + name + ". It does " + damage + " damage, costs " + manaCost + " mana and has a range of " + range + "!";
	}
	
	public void activate(Hero h) {
		h.learn(this);
		System.out.println(h.knownAs() + " learned " + name + "!");
	}
	
	public int getManaCost() {
		return manaCost;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getRange() {
		return range;
	}
}
