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

	public long toMillis() {
		long result = 0;
		
		switch (this) {
		case DAYLY:
			//1000 milliseconds * 60 seconds * 60 minutes * 24 hours = 1 day
			result = 1000 * 60 * 60 * 24;
			break;
		case MONTHLY:
			result = 1000 * 60 * 60 * 24 * 30;
			break;
		case YEARLY:
			result = 1000 * 60 * 60 * 24 * 30 * 12;
			break;
		}
		return result;
	}
}
