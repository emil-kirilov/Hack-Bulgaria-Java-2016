package DroneDeliverySystem;

public class DroneDeliverySystem {
	IO io;
	
	public DroneDeliverySystem() {
		DroneManager dm = new DroneManager();
	
		Warehouse wh = new Warehouse();
		Product meat = new Product(1, "meat", 8);
		Product eggs = new Product(2, "eggs", 2);
		Product milk = new Product(3, "milk", 2);
		wh.addProduct(meat);
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
