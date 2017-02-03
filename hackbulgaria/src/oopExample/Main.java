package oopExample;

import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args){
		//1.
		GasStation gasStation = new GasStation();
		PriorityQueue<Vignette> v = gasStation.getVignettes(); 
		int length = v.size();
		for (int i = 0; i < length; i ++) {
			System.out.println(v.poll());
		}
		
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
		for (int i = 0; i < drivers.size(); i++) {
			if( i % 3 == 0) {
				drivers.get(i).buyRandomVignettes(5);
			} else {
				drivers.get(i).buyRandomVignettesForAll();
			}
		}
		
//		//5.
//		for (Driver driver : drivers) {
//			System.out.println(driver);
//		}
//		
//		//6.
//		for (Vignette vignette : gasStation.getVignettes()) {
//			System.out.println(vignette);
//		}
//		
//		//7.
//		for (Truck truck : vehiclesCreator.getTheTrucks()) {
//			if (truck.vignetteExpired(new Date())) {
//				System.out.println(truck);
//			}
//		}
	}
}
