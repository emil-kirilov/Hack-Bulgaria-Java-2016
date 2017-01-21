package DroneDeliverySystem;

public class DroneManager {

	public boolean judgeDist(int x, int y) {
		if (distanceFromWarehouse(x, y) < 50) {
			return true;
		}
		return false;
	}
	
	private double distanceFromWarehouse(int x, int y) {
		return Math.sqrt( Math.pow(42 - x, 2) + Math.pow(42 - y, 2) );
	}

}
