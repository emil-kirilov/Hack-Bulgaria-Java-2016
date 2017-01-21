package DroneDeliverySystem;

public class Main {

	public static void main(String[] args) {
		DroneDeliverySystem dds = new DroneDeliverySystem();
		String delivery = "delivery 4 2016-10-25 12:31 42,36 1 1 2 2";
		System.out.println(dds.acceptDelivery(delivery));
	}

}

