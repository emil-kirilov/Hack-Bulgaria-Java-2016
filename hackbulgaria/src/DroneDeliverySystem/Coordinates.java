package DroneDeliverySystem;

public class Coordinates {
	int x;
	int y;
	
	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Coordinates(String coords) {
		String[] xy = coords.split(",");
		this.x = Integer.parseInt(xy[0]);
		this.y = Integer.parseInt(xy[1]);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
