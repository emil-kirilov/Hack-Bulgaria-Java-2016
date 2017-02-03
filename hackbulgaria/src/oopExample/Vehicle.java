package oopExample;

import java.util.Date;
import java.util.Random;

//I made this class abstract for the sake of exercising OOP.
//Different vehicle types could easily be differentiated via a new variable.
public abstract class Vehicle {
	protected String model;
	protected final VehicleClass vehicleClass;
	protected Vignette vignette;
	protected int year;
	
	public void setVignette(Vignette newVignette) {
		vignette = newVignette;
	}
	
	//Ensured that the vehicleClass variable will be set when creating a Vehicle object
	//I am setting the vignette to be null on a brand new vehicle. Carefull with that, always call super first.
	protected Vehicle(VehicleClass vehicleClass) {
		this.vehicleClass = vehicleClass;
		this.vignette = null;
	}
	
	public static Vehicle generateRandomVehicle() {
		Vehicle result = null;
		
		int year = 1980 + (int)(Math.random() * 37);
		Random rand = new Random(); 
		int vehicleType = rand.nextInt(3); 
		switch (vehicleType) {
		case 0: 
			result =  Car.generateRandomCar(year);
			break;
		case 1: 
			result = Truck.generateRandomTruck(year);
			break;
		case 2: 
			result =  Bus.generateRandomBus(year);
			break;	
		}
		return result;
	}
	
	public boolean vignetteExpired() {
		if (vignette == null) {
			return true;
		}
		
		boolean result = false;
		if ( vignette.getGetExpiryDate().compareTo(new Date()) < 0 ) {
			result = true;
		}
		return result;
	}
	
	public VehicleClass getVehicleClass() {
		return vehicleClass;
	}

	public String toString() {
		return "Class: " + vehicleClass + "  Year: " + year + "  Model: " + model;
	}

	public boolean isATruck() {
		return this.vehicleClass == VehicleClass.TRUCK;
	}
}
