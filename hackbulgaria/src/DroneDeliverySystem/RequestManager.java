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
		Coordinates whCoords = whm.getCoords(0);
		if (dm.judgeDist(request.getCoords(), whCoords) && whm.haveGoods(0, request.getGoods())) {
			double weight = whm.calculateWeight(0, request.getGoods());
			int dronesNeeded = dm.dronesToLift(0, weight);
			if (dronesNeeded) {
				executeRequest(request, whCoords);
				return true;
			}
		}
		
		return false;
	}

	public void executeRequest(Request request, Coordinates whCoords) {
		dm.sendDrones(0, request, whCoords);
		whm.removeGoods(0, request.getGoods());
	}
	
}
