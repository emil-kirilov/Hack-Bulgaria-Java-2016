package oopExample;

import java.util.List;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args){
		//1.
		GasStation gasStation = new GasStation();
		
		//2.
		DriversCreator driversCreator = new DriversCreator(20);
		for (Driver driver : driversCreator.getDrivers()) {
			System.out.println(driver);
		}

		//3.
		VehiclesCreator vehiclesCreator = new VehiclesCreator(200);
		//random vehicles are being created
		//vehiclesCreator.showVehicles();
		
		List<Driver> drivers = driversCreator.getDrivers();		
		for (Driver driver : drivers) {
			driver.setVehicles(vehiclesCreator.giveVehicles(10));
		}
		//drivers are being given random vehicles
		//for (Driver driver : driversCreator.getDrivers()) {
		//	System.out.println(driver);
		//}
		
		//4.
		//TODO check whether buyRandom___ works correctly
		for (int i = 0; i < drivers.size(); i++) {
			if( i % 3 == 0) {
				drivers.get(i).buyRandomVignettes(5);
			} else {
				drivers.get(i).buyRandomVignettesForAll();
			}
		}
		
		//you can see how driver's money are spent
		//for (Driver driver : driversCreator.getDrivers()) {
		//	System.out.println(driver);
		//	driver.showVehicles();
		//}
		
		
		//5.
		for (Driver driver : drivers) {
			System.out.println(driver);
		}

		//6.
		PriorityQueue<Vignette> orderedVignetes = gasStation.getVignettes(); 
		int length = orderedVignetes.size();
		for (int i = 0; i < length; i ++) {
			System.out.println(orderedVignetes.poll());
		}
		
		//7.
		for (Driver driver : drivers) {
			System.out.println(driver.trucksWithExpiredVignettes());
		}
	}
}
