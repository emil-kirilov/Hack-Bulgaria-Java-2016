package oopExample;

public class Truck extends Vehicle{

	protected Truck(int year, String model) {
		super(VehicleClass.TRUCK);
		this.year = year;
		this.model = model;
	}
	
	//hardcoded the model :s
	static Truck generateRandomTruck(int year) {
		return new Truck(year, "CAT");
	}
	
}
