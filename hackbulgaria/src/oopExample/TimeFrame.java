package oopExample;

import java.util.Random;

public enum TimeFrame {
	DAYLY, MONTHLY, YEARLY;

	public static TimeFrame generateRandomTimeFrame() {
		TimeFrame result = null;
		Random rand = new Random(); 
		
		int interval = rand.nextInt(3); 
		switch (interval) {
		case 0:
			result = DAYLY;
			break;
		case 1:
			result = MONTHLY;
			break;
		case 2:
			result = YEARLY;
			break;
		}
		return result;
	}
}
