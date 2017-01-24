package DroneDeliverySystem;

public class Drone {
	private int id;
	private int battery;
	private int capacity;
	private int chargingRate;
	private long availableAt;
	
	public Drone(int id, int battery, int capacity, int chargingRate) {
		this.id = id;
		this.battery = battery;
		this.capacity = capacity;
		this.chargingRate = chargingRate;
		availableAt = System.currentTimeMillis();	
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

	public long firstAvailable() {
		return availableAt;
	}
}
