package DroneDeliverySystem;
public class RequestManager {
	DroneManager dm;
	WarehouseManager whm;
	
	public RequestManager(DroneManager dm, WarehouseManager whm){
		this.dm = dm;
		this.whm = whm;
	}
	public boolean acceptRequest(Request request) {
		//TODO when to dispatch the shipment??
		if (dm.judgeDist(request.getCoords(), whm.getCoords(0)) && whm.haveGoods(0, request.getGoods())) {
			double weight = whm.calculateWeight(0, request.getGoods());
			return dm.canLift(weight);
		}
		
		return false;
	}

}
