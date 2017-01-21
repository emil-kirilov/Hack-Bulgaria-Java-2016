package DroneDeliverySystem;
import java.util.HashMap;
import java.util.Map.Entry;

public class Warehouse {
	//product -> product_id
	private HashMap<Product, Integer> productID = new HashMap<Product, Integer>();
	//product_id -> quantity
	private HashMap<Integer, Integer> idQuantity = new HashMap<Integer, Integer>();
	private int id_counter = 1;
	
	public void addProduct(Product pr) {
		
		//if product is already registered
		if (productID.containsKey(pr)) {
			//increase quantity by 1
			idQuantity.put(productID.get(pr), idQuantity.get(id_counter) + 1);
		
		//if product is not registered
		} else {
			//register the product
			productID.put(pr, id_counter);
			id_counter++;
			//set product quantity to 1
			idQuantity.put(productID.get(pr), 1);
		}
	}

	public boolean haveGoods(HashMap<Integer, Integer> goods) {
		//go over the requested goods
		//each entry represents    product_id (requested)-> quantity(needed)
		for(Entry<Integer, Integer> e : goods.entrySet()) {
			//return false if the warehouse doesn't have a product with the requested id 
			if(!idQuantity.containsKey( e.getKey() )) {
				return false;
			}
			
			//return false if the warehoue doesn't have the requested quanitty
			if(idQuantity.get( e.getKey() ) < e.getValue()) {
				return false;
			}
		}
		
		//TODO remove the selected quantity from the warehouse!
		return true;
	}
}
