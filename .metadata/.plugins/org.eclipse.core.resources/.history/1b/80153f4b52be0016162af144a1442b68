package DungeonsAndDragons;

public abstract class Unit {
	Weapon weapon;
	Spell spell;
	int health;
	int maxHP;
	int mana;
	int maxMP;
	
	public Unit(int hp, int mp) {
		health = hp;
		maxHP = hp;
		mana = mp;
		maxMP = mp;
	}
	
	public boolean isAlive() {
		return health > 0;
	}
	
	public int getHealth() {
		return health;
	}

	public int getMana() {
		return mana;
	}

	public void takeHealing(int healHP) {
		if (healHP + getHealth() > maxHP) {
			health = maxHP;
		} else {
			health += healHP;
		}
	}

	public void takeMana(int healMP) {
		if (healMP + getMana() > maxMP) {
			mana = maxMP;
		} else {
			mana += healMP;
		}
	}
	
	public void takeDamage(int damage) {
		health -= damage;
		if (health < 0) {
			death();
		}
	}

	public void setSpell(Spell name) {
		spell = name;
	}
	
	public void setWeapon(Weapon name) {
		weapon = name;
	}
	
	public boolean canCast(Spell name) {
		return spell.equals(name);
	}
	
	public void equip(Weapon weapon) {
		this.weapon = weapon;
	}

	public void learn(Spell spell) {
		this.spell = spell;
	}
	
	public abstract	int attack(String type);
	public abstract void death();
}
