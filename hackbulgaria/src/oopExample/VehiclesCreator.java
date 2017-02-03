package oopExample;

import java.util.LinkedList;
import java.util.List;

public class VehiclesCreator {
	LinkedList<Vehicle> vehicles;
	
	public VehiclesCreator(int required) {
		vehicles = new LinkedList<Vehicle>();
		
		for (int i = 0; i < required; i++) {
			vehicles.add(Vehicle.generateRandomVehicle());
		}
	}

	public List<Vehicle> giveVehicles(int required) {
		List<Vehicle> result = new LinkedList<Vehicle>();
		for(int i = 0; i < required; i++) {
			//poll will return null if vehicles is empty
			result.add(vehicles.poll());
		}
		return result;
	}

	public List<Truck> getTheTrucks() {
		// TODO Auto-generated method stub
		return null;
	}

	public void showVehicles() {
		int limit = vehicles.size();
		for(int i = 0; i < limit; i++) {
			System.out.println(vehicles.poll());
		}
	}

}
