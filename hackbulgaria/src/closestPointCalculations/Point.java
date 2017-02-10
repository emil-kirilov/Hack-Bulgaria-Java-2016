package closestPointCalculations;

import java.util.Random;

public class Point {
	private int x;
	private int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public double distanceTo(Point other) {
		return Math.sqrt( Math.pow(x - other.getX(), 2) + Math.pow(y - other.getY(), 2));
	}
	
	public String toString() {
		return "Point's coordinates are (" + x +", " + y + ").";
	}
	
	public static Point generatePoint() {
		Point result = null;
		Random random = new Random();
		result = new Point( random.nextInt(10_000), random.nextInt(10_000) );
		return result;
	}
}
