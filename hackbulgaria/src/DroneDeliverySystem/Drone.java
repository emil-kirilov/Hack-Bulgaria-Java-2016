package DroneDeliverySystem;

public class Drone {
	private int id;
	private int battery;
	private int capacity;
	private int chargingRate;
	private boolean free;
	
	public Drone(int id, int battery, int capacity, int chargingRate) {
		this.id = id;
		this.battery = battery;
		this.capacity = capacity;
		this.chargingRate = chargingRate;
		free  = true;
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

	public boolean isFree() {
		return free;
	}
	
	public void setState(boolean state) {
		free = state;
	}
}
