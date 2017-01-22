package DroneDeliverySystem;

public class DroneManager {

	public boolean judgeDist(Coordinates target, Coordinates warehouse) {
		int targetX = target.getX();
		int targetY = target.getY();
		int warehouseX = warehouse.getX();
		int warehouseY = warehouse.getY();
		
		if (distance(targetX, targetY, warehouseX, warehouseY) < 50) {
			return true;
		}
		return false;
	}
	
	private double distance(int targetX, int targetY, int warehouseX, int warehouseY) {
		return Math.sqrt( Math.pow(targetX - warehouseX, 2) + Math.pow(targetY - warehouseY, 2) );
	}

}
