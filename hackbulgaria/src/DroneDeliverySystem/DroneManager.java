package DroneDeliverySystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

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

	public int dronesToLift(int id, double weight) {
		int res = 0;
		//remove drone's capacity from weight until un reaches negative value
		if (weight < getFreeDronesCapacity(whIDtoDrones.get(id))) {
			res = Math.ceil(weight / );
		}
		return res;
	}

	private double getFreeDronesCapacity(ArrayList<Drone> drones) {
		// check for battery life		
		int capacity = 0;
		for (Drone dr : drones) {
			if (System.currentTimeMillis() > dr.firstAvailable()) {
				capacity += dr.getCapacity();
			}
		}
		
		return capacity;
	}

	public void addDrone(int id, Drone drone) {
		whIDtoDrones.get(id).add(drone);
	}

	public void sendDrones(int id, Request request, Coordinates coords) {
		long timeToReturn = 2 * numberOfItems(request.getGoods().values()) 
				+ 2 * calcDistance(request.getCoordX(), req, warehouseX, warehouseY);
		
	}
	
	private int numberOfItems(Collection<Integer> items) {
		int res = 0;
		for (Integer integer : items) {
			res += integer;
		}
		return res;
	}
}
