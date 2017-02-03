package oopExample;

import java.util.Random;

public enum Color {
	//RED for VehicleClass.CAR
	//BLUE for VehicleClass.TRUCK
	//GREEN for VehicleClass.BUS
	RED, BLUE, GREEN;

	public static Color generateRandomColor() {
		Color result = null;
		Random rand = new Random(); 
		
		int color = rand.nextInt(3); 
		switch (color) {
		case 0:
			result = RED;
			break;
		case 1:
			result = BLUE;
			break;
		case 2:
			result = GREEN;
			break;
		}
		return result;
	}
	
	public boolean represents(VehicleClass vehicleClass) {
		boolean result = false;
		if (this == RED && vehicleClass == VehicleClass.CAR) {
			result = true;
		} else if (this == BLUE && vehicleClass == VehicleClass.TRUCK){
			result = true;
		} else if (this == GREEN && vehicleClass == VehicleClass.BUS){
			result = true;
		}
		return result;
	}
}
