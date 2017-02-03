package oopExample;

import java.util.ArrayList;
import java.util.List;

public class DriversCreator {
	private List<Driver> drivers;
	
	public DriversCreator(int required) {
		drivers = new ArrayList<Driver>();
		
		for(int i = 0; i < required; i++) {
			drivers.add(Driver.generateRandomDriver());
		}
	}
	
	public List<Driver> getDrivers() {
		return drivers; 
	}
}
