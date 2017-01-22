package DroneDeliverySystem;
import java.util.ArrayList;
import java.util.HashMap;

public class WarehouseManager {
	ArrayList<Warehouse> whs = new ArrayList<Warehouse>();

	public void addWarehouse(Warehouse wh){
		whs.add(wh);
	}

	//asks the warehouse with the given id whether it has the requested goods
	public boolean haveGoods(int id, HashMap<Integer, Integer> goods) {
		return whs.get(id).haveGoods(goods);
	}

	//gets the coordinates of the warehouse with the given id
	public Coordinates getCoords(int id) {
		return whs.get(id).getCoords();
	}

	//ask the warehouse with the given id to calculate the weight of the requested goods
	public double calculateWeight(int id, HashMap<Integer, Integer> goods) {
		return whs.get(id).calculateWeight(goods);
	}
}
