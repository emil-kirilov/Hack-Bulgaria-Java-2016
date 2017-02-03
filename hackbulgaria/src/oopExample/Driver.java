package oopExample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Driver {
	private Name name;
	private int cash;
	private GasStation gasStation;
	List<Vehicle> vehicles;
	
	public Driver(Name name, int cash, GasStation gasStation) {
		this.name = name;
		this.cash = cash;
		this.gasStation = gasStation;
		vehicles = new ArrayList<>();
	}

	public String toString() {
		return "Vehicles: " + vehicles.size() + "  Vehicles with out-of-date-vignette: X" + "  Cash:" + cash;
	}
	
	
	public void stickVignette() {
		
	}
	
	List<Vehicle> getVehicleWithExpiredVignettes(Date date) {
		return null;
	}

	public static Driver generateRandomDriver() {
		//sets the minimum cash to 500 and maximum to 2500 (I believe 2500 cannot be achieved though)
		int cash = 500 + (int)(Math.random() * 2000);
		return new Driver(Name.generateRandomName(), cash, new GasStation());
	}

	public void setVehicles(List<Vehicle> newVehicles) {
		vehicles.addAll(newVehicles);
	}

	public void buyRandomVignettes(int vehiclesCount) {
		List<Vehicle> randomVehicles = pickRandomVehicles(vehiclesCount);
		for (Vehicle vehicle : randomVehicles) {
			//driver decides the time period for the new vignette
			TimeFrame randomTimeFrame = TimeFrame.generateRandomTimeFrame();
			VehicleClass randomVehicleClass = vehicle.getVehicleClass();
			
			if ( canBuyVignette(randomVehicleClass, randomTimeFrame) ) {
				Vignette boughtVignette = buyVignette(randomVehicleClass, randomTimeFrame);
				stickVignette(boughtVignette, vehicle);
			}
				
		}
	}

	private void stickVignette(Vignette boughtVignette, Vehicle vehicle) {
		vehicle.setVignette(boughtVignette);
	}

	private Vignette buyVignette(VehicleClass randomVehicleClass, TimeFrame randomTimeFrame) {
		//Here I should make a transaction, but I won't
		Vignette vignette = gasStation.sellVignette(randomVehicleClass, randomTimeFrame);
		cash -= vignette.getPrice();
		return vignette;
	}

	private boolean canBuyVignette(VehicleClass vehicleClass, TimeFrame randomTimeFrame) {
		int price = gasStation.checkPriceForVignette(vehicleClass, randomTimeFrame);
		if (price < 0) {
			return false;
		}
		return cash >= gasStation.checkPriceForVignette(vehicleClass, randomTimeFrame);
	}

	private List<Vehicle> pickRandomVehicles(int vehiclesCount) {
		List<Vehicle> randomVehicles = new ArrayList<>();
		int limit = vehicles.size();
		
		if (vehiclesCount < limit) {
			while(vehiclesCount > 0) {
				int index = (int)(Math.random() * limit);
				Vehicle picked = vehicles.get(index);
				
				if (!randomVehicles.contains(picked)) {
					randomVehicles.add(picked);
					vehiclesCount--;
				}
			}
		} 
		return vehicles;
	}

	public void buyRandomVignettesForAll() {
		for (Vehicle vehicle : vehicles ) {
			
		}
	}
}
