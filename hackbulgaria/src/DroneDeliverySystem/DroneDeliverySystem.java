package DroneDeliverySystem;

public class DroneDeliverySystem {
	IO io;
	
	public DroneDeliverySystem() {
		DroneManager dm = new DroneManager();
		Drone d1 = new Drone(1, 2000, 5, 500);
		Drone d2 = new Drone(2, 2000, 5, 500);
		Drone d3 = new Drone(3, 2000, 5, 500);
		Drone d4 = new Drone(4, 2000, 5, 500);
		Drone d5 = new Drone(5, 2000, 5, 500);
		dm.addDrone(d1);
		dm.addDrone(d2);
		dm.addDrone(d3);
		dm.addDrone(d4);
		dm.addDrone(d5);
		
		Warehouse wh = new Warehouse(42,42);
		Product meat = new Product("meat", 8, 1);
		Product eggs = new Product("eggs", 2, 0.5);
		Product milk = new Product("milk", 2, 1);
		wh.addProduct(meat);
		wh.addProduct(eggs);
		wh.addProduct(eggs);
		wh.addProduct(milk);
		
		WarehouseManager whm = new WarehouseManager();
		whm.addWarehouse(wh);
	
		RequestManager rm = new RequestManager(dm, whm);

		this.io = new IO(rm);
	}

	public boolean acceptDelivery(String delivery) {
		return io.acceptDelivery(delivery);
	}
}
