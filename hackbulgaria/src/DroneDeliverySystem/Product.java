package DroneDeliverySystem;

public class Product {
	private int id;
	private String name;
	private double price;
	private double weight;
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public double getWeight() {
		return weight;
	}

	public Product(int id, String name, double price, double weight) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.weight = weight;
	}
}
