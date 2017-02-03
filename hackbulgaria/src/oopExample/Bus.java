package oopExample;

public class Bus extends Vehicle {

	protected Bus(int year, String model) {
		super(VehicleClass.BUS);
		this.year = year;
		this.model = model;
	}
	
	//hardcoded the model :s
	public static Vehicle generateRandomBus(int year) {
		return new Bus(year, "Mercedes");
	}

}
