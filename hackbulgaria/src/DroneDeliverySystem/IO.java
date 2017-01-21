package DroneDeliverySystem;

public class IO {
	RequestManager rm;
	
	public IO (RequestManager rm) {
		this.rm = rm;
	}

	public boolean acceptDelivery(String delivery) {
		Request request = new Request(delivery);
		return rm.acceptRequest(request);
	}

}
