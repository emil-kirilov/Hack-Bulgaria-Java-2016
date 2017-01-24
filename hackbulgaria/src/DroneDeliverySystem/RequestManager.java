package DroneDeliverySystem;
public class RequestManager {
	DroneManager dm;
	WarehouseManager whm;
	
	public RequestManager(DroneManager dm, WarehouseManager whm){
		this.dm = dm;
		this.whm = whm;
	}
	public boolean acceptRequest(Request request) {
		//TODO whm must provide the coords of the right warehouse
		if (dm.judgeDist(request.getCoords(), whm.getCoords(0)) && whm.haveGoods(0, request.getGoods())) {
			double weight = whm.calculateWeight(0, request.getGoods());
			if (dm.canLift(weight)) {
				executeRequest(request);
				return true;
			}
		}
		
		return false;
	}

	public void executeRequest(Request request) {
		dm.sendDrones(0, request);
		whm.removeGoods(0, request);
	}
	
}
