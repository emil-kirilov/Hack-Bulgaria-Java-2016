package DroneDeliverySystem;
import java.util.HashMap;

public class Request {
	//product_id -> quantity
	private HashMap<Integer, Integer> goods = new HashMap<Integer, Integer>();
	private Coordinates xy;
	
	public int getCoordX() {
		return xy.getX();
	}

	public int getCoordY() {
		return xy.getY();
	}
	
	public Coordinates getCoords() {
		return xy;
	}

	public HashMap<Integer, Integer> getGoods() {
		return goods;
	}

	public Request(String request) {
		String[] parts = request.split(" ");
		xy = new Coordinates(parts[4]);
		
		for (int i = 5; i < parts.length; i+=2) {
			goods.put(Integer.parseInt(parts[i]), Integer.parseInt(parts[i+1]));
		}
	}
	
}
