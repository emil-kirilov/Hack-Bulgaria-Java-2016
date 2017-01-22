package DroneDeliverySystem;
import java.util.HashMap;
import java.util.Map.Entry;

public class Warehouse {
	//TODO PRODUCTS HAVE ID FIELD NOW!!
	//product -> product_id
	private HashMap<Integer, Product> idToProduct = new HashMap<Integer, Product>();
	//product_id -> quantity
	private HashMap<Product, Integer> productToQuantity = new HashMap<Product, Integer>();
	private int id_counter = 1;
	private Coordinates xy;
	
	public Warehouse(int x, int y) {
		xy = new Coordinates(x, y);
	}

	public Coordinates getCoords() {
		return xy;
	}
	
	public void addProduct(Product pr) {
		
		//if product is already registered
		if (productToQuantity .containsKey(pr)) {
			//increase quantity by 1
			productToQuantity .put(pr, productToQuantity .get(pr) + 1);
		
		//if product is not registered
		} else {
			//register the product
			idToProduct.put(id_counter, pr);
			id_counter++;
			//set product quantity to 1
			productToQuantity.put(pr, 1);
		}
	}

	public boolean haveGoods(HashMap<Integer, Integer> goods) {
		//go over the requested goods
		//each entry represents    product_id (requested)-> quantity(needed)
		for(Entry<Integer, Integer> e : goods.entrySet()) {
			//return false if the warehouse doesn't have a product with the requested id 
			if(!productToQuantity.containsKey( e.getKey() )) {
				return false;
			}
			
			//return false if the warehouse doesn't have the requested quantity
			if(productToQuantity.get( e.getKey() ) < e.getValue()) {
				return false;
			}
		}
		
		//TODO remove the selected quantity from the warehouse!
		return true;
	}

	public double calculateWeight(HashMap<Integer, Integer> goods) {
		double weight = 0;
		
		//each entry represents    product_id (requested)-> quantity(needed)
		for(Entry<Integer, Integer> e : goods.entrySet()) {
			Product requestedProduct = idToProduct.get(e.getKey());
			weight += requestedProduct.getWeight() * e.getValue();
		}
		
		return weight;
	}
}
