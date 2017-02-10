package closestPointCalculations;

import java.util.ArrayList;
import java.util.List;

public class PointsHelper implements Runnable {
	private int targetIndex;
	private Point target;
	private List<Point> points;
	private Point closestPoint = null;
	private double closestDistance = Double.MAX_VALUE;
	
	public static List<Point> generatePoints(int count) {
		List<Point> result = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			result.add(Point.generatePoint());
		}
		return result;
	}
	
	public PointsHelper(List<Point> points, int index) {
		this.points = points;
		this.targetIndex = index;
		target = points.get(index);
	}	

	public Point getClosestPoint() {
		return closestPoint;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < points.size(); i++) {
			if( i != targetIndex ) {
				Point current = points.get(i);
				double distanceToCurrent = target.distanceTo(current);
				if (closestDistance > distanceToCurrent) {
					closestPoint = current;
					closestDistance = distanceToCurrent;
				}
			}
		}
	}

}
