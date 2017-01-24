package DroneDeliverySystem;

import java.util.ArrayList;
import java.util.HashMap;

public class DroneManager {
	private HashMap<Integer, ArrayList<Drone>> whIDtoDrones = new HashMap<Integer, ArrayList<Drone>>();
	
	public boolean judgeDist(Coordinates target, Coordinates warehouse) {
		int targetX = target.getX();
		int targetY = target.getY();
		int warehouseX = warehouse.getX();
		int warehouseY = warehouse.getY();
		
		if (calcDistance(targetX, targetY, warehouseX, warehouseY) < 50) {
			return true;
		}
		return false;
	}
	
	private double calcDistance(int targetX, int targetY, int warehouseX, int warehouseY) {
		return Math.sqrt( Math.pow(targetX - warehouseX, 2) + Math.pow(targetY - warehouseY, 2) );
	}

	public boolean canLift(int id, double weight) {
		return weight < getFreeDronesCapacity(whIDtoDrones.get(id));
	}

	private double getFreeDronesCapacity(ArrayList<Drone> drones) {
		// check for battery life		
		int capacity = 0;
		for (Drone dr : drones) {
			if (dr.isFree()) {
				capacity += dr.getCapacity();
			}
		}
		
		return capacity;
	}

	public void addDrone(int id, Drone drone) {
		whIDtoDrones.get(id).add(drone);
	}

	public void sendDrones(int id, Request request) {
		
	}
}
