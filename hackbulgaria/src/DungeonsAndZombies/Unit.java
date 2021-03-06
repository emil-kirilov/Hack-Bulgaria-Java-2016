package DungeonsAndZombies;

public abstract class Unit {
	Weapon weapon;
	Spell spell;
	int health;
	int maxHP;
	int mana;
	int maxMP;
	int range;
	
	public Unit(int hp, int mp) {
		health = hp;
		maxHP = hp;
		mana = mp;
		maxMP = mp;
		range = 1;
	}
	
	public boolean isAlive() {
		return health > 0;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMaxHealth() {
		return maxHP;
	}

	public int getMana() {
		return mana;
	}
	
	public int getMaxMana() {
		return maxMP;
	}

	public int getWeaponDmg() {
		int damage = 0;
		if (weapon != null) {
			damage = weapon.getDamage();	
		}
		return damage;
	}
	
	public int getSpellDmg() {
		int damage = 0;
		if (spell != null) {
			damage = spell.getDamage();	
		}
		return damage;
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
		range = Math.max(weapon.getRange(), range);
	}

	public void learn(Spell spell) {
		this.spell = spell;
		range = Math.max(spell.getRange(), range);
	}
	
	public int getRange() {
		return range;
	}
	
	public abstract	int attack(String type);
	public abstract void death();
}
