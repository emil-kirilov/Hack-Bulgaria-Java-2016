package DroneDeliverySystem;
import java.util.HashMap;

public class Request {
	//product_id -> quantity
	private HashMap<Integer, Integer> goods = new HashMap<Integer, Integer>();
	private int coordX;
	private int coordY;
	
	public int getCoordX() {
		return coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public HashMap<Integer, Integer> getGoods() {
		return goods;
	}

	public Request(String request) {
		String[] parts = request.split(" ");
		String[] coords = parts[4].split(","); 
		coordX = Integer.parseInt(coords[0]);
		coordY = Integer.parseInt(coords[1]);
		
		for (int i = 5; i < parts.length; i+=2) {
			goods.put(Integer.parseInt(parts[i]), Integer.parseInt(parts[i+1]));
		}
	}
	
}
