package week8;

public class Sword extends ToSmash{

	public Sword() {
		super(50, 10);
	}
	
	public String toString() {
		return "Sword " + damage + " " + durability;
	}
}
