package oopExample;

import java.util.Random;

public enum Name {
	JOSH, MIKE, GEORGE, KIM, LESLIE, DAN;
	
	public static Name generateRandomName() {
		Name result = null;
		Random rand = new Random(); 
		
		int name = rand.nextInt(3); 
		switch (name) {
		case 0:
			result = JOSH;
			break;
		case 1:
			result = MIKE;
			break;
		case 2:
			result = GEORGE;
			break;
		case 3:
			result = KIM;
			break;
		case 4:
			result = LESLIE;
			break;
		case 5:
			result = DAN;
			break;
		}
		
		return result;
	}
}
