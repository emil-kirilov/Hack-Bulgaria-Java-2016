package DungeonsAndZombies;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Helper {
	public static class BattleManager {
		public static void startBattle(Hero hero, Enemy enemy) {
			String attackOptions = "";
			if (hero.weapon != null) {
				attackOptions = "weapon";
			} 
			if (hero.spell != null) {
				attackOptions = (attackOptions.equals("")) ?  "spell" :"weapon and spell" ;
			}
			System.out.println("You can attack by " + attackOptions + ".");
			System.out.println("What is your choice?");
			List<String> validChoice =  Arrays.asList(attackOptions.split(" and "));
			
			Scanner s = new Scanner(System.in);
			String choice = s.nextLine();
			while (!validChoice.contains(choice)) {
				System.out.print("Please enter a valid choice: ");
				choice = s.nextLine();
				System.out.println();
			}
			
			while(hero.getHealth() > 0 && enemy.getHealth() > 0) {
				System.out.println(hero.knownAs() + " attacked the enemy for " + hero.getDamage(choice) + " damage.");
				enemy.takeDamage(hero.attack(choice));
				
				if(enemy.getHealth() > 0) {
					System.out.println("Enemy was left at " + enemy.getHealth() + "hp.");
					System.out.println("The enemy attacked " + hero.knownAs() + " for " + enemy.attack("weapon") + " damage.");
					hero.takeDamage(enemy.attack("weapon"));
					System.out.println(hero.knownAs() + " was left at " + hero.getHealth() + "hp.");
				}
			}
			
			if(enemy.getHealth() <= 0) {
				System.out.println(hero.knownAs() + " defeated the enemy!" );
			} else {
				System.out.println(hero.knownAs() + " was defeated!" );	
			}
		}
		
		public static void startRangedBattle(Hero hero, Enemy enemy) {
			String attackOptions = "";
			if (hero.weapon != null && hero.weapon.getRange() > 1) {
				attackOptions = "weapon";
			} 
			if (hero.spell != null && hero.spell.getRange() > 1) {
				attackOptions = (attackOptions.equals("")) ?  "spell" :"weapon and spell" ;
			}
			System.out.println("You can attack by " + attackOptions + ".");
			System.out.println("What is your choice?");
			List<String> validChoice =  Arrays.asList(attackOptions.split(" and "));
			
			Scanner s = new Scanner(System.in);
			String choice = s.nextLine();
			while (!validChoice.contains(choice)) {
				System.out.print("Please enter a valid choice: ");
				choice = s.nextLine();
				System.out.println();
			}
			
			int attacks = 0;
			while(hero.getHealth() > 0 && enemy.getHealth() > 0 && attacks < 1) {
				System.out.println(hero.knownAs() + " attacked the enemy for " + hero.getDamage(choice) + " damage.");
				enemy.takeDamage(hero.attack(choice));
				
				attacks++;
				if(enemy.getHealth() > 0) {
					System.out.println("Enemy was left at " + enemy.getHealth() + "hp.");
					//System.out.println("The enemy attacked " + hero.knownAs() + " for " + enemy.attack("weapon") + " damage.");
					//hero.takeDamage(enemy.attack("weapon"));
					//System.out.println(hero.knownAs() + " was left at " + hero.getHealth() + "hp.");
				}
			}
			
			if(enemy.getHealth() <= 0) {
				System.out.println(hero.knownAs() + " defeated the enemy!" );
			} else {
				System.out.println(hero.knownAs() + " was defeated!" );	
			}
		}
	}

	public static class Treasure {
		static final Weapon[] armory = {new Weapon("Silver lance", 40, 1), new Weapon("Silver bow", 30, 2), new Weapon("Silver musket", 15, 3)};
		static final Spell[] library = {new Spell("Fire", 45, 45, 2), new Spell("Nosferatu", 30, 45, 3), new Spell("Shine", 60, 60, 1)};
		static final Potion[] potions = {new Potion(25), new Potion(50), new Potion(100)};
			
		String contains;
		Treasureable treasure;
			
		public Treasure() {
			Random rand = new Random(); 
			int type = rand.nextInt(3); 
			int index = rand.nextInt(3); 
			switch (type) {
			case 0: 
				treasure = armory[index];
				contains = armory[index].toString();
				break;
			case 1: 
				treasure = library[index];
				contains = library[index].toString();
				break;
			case 2: 
				treasure = potions[index];
				contains = potions[index].toString(); 
				break;
			}
		}
			
		public String toString() {
			return "The treasure contains " + contains;
		}
		
		public Treasureable getTreasure() {
			return treasure;
		}
		
		public static void startTreasure(Hero hero) {
			Treasure luck = new Treasure();
			System.out.println(luck);
			
			Scanner s = new Scanner(System.in);
			boolean invalidAnswer = true;
			while(invalidAnswer) {
				System.out.println("Would you like to use this item?");
				System.out.print("Your answer (yes/no): ");
				String response = s.nextLine();
				if (response.equals("yes")) {
					luck.getTreasure().activate(hero);
					invalidAnswer = false;
				} else if (response.equals("no")) {
					invalidAnswer = false;
				}
			}
		}
	} 	
}
