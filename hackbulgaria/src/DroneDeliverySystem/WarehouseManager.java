package DroneDeliverySystem;
import java.util.ArrayList;
import java.util.HashMap;

public class WarehouseManager {
	ArrayList<Warehouse> whs = new ArrayList<Warehouse>();
	
		public void addWarehouse(Warehouse wh){
		whs.add(wh);
	}

		public boolean haveGoods(HashMap<Integer, Integer> goods) {
			return whs.get(0).haveGoods(goods);
		}
}
