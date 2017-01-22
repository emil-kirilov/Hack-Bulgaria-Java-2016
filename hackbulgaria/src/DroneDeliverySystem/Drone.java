package DroneDeliverySystem;

public class Drone {
	private int id;
	private int battery;
	private int capacity;
	private int chargingRate;
	
	public Drone(int id, int battery, int capacity, int chargingRate) {
		this.id = id;
		this.battery = battery;
		this.capacity = capacity;
		this.chargingRate = chargingRate;
	}

	public int getId() {
		return id;
	}

	public int getBattery() {
		return battery;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getChargingRate() {
		return chargingRate;
	}
}
