package DroneDeliverySystem;
public class RequestManager {
	DroneManager dm;
	WarehouseManager whm;
	
	public RequestManager(DroneManager dm, WarehouseManager whm){
		this.dm = dm;
		this.whm = whm;
	}
	public boolean acceptRequest(Request request) {
		return dm.judgeDist(request.getCoordX(), request.getCoordY()) && whm.haveGoods(request.getGoods());
	}

}
