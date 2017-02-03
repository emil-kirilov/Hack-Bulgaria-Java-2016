package oopExample;

public class Car extends Vehicle{

	protected Car(int year, String model) {
		super(VehicleClass.CAR);
		this.year = year;
		this.model = model;
	}
	
	static Car generateRandomCar(int year) {
		return new Car(year, "Mazda");
	}
}
