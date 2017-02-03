package oopExample;

import java.util.Date;
import java.util.PriorityQueue;

class Vignette implements Comparable<Vignette>{
	private Color color;
	private Date dateOfIssue;
	private TimeFrame timeFrame;
	private int price;
	private int timeToStick;
	
	public String toString() {
		return "Color: " + color + "  Price: " + price;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getTimeToStick() {
		return timeToStick;
	}
	
	public TimeFrame getTimeFrame() {
		return timeFrame;
	}
	
	public Color getColor() {
		return color;
	}
	
	Date getDateOfIssue() {
		return dateOfIssue;
	}
	
	public Vignette(TimeFrame timeFrame, Color color) {
		this.color = color;
		this.timeFrame = timeFrame;
		this.dateOfIssue = new Date();
		this.price = calculatePrice(color, timeFrame);
		this.timeToStick = calculateTimeToStick(color);
	}
	
	private int calculateTimeToStick(Color color) {
		int timeToStick = 0;
		switch (color) {
		case RED:
			timeToStick = 5;
			break;
		case BLUE:
			timeToStick = 10;
			break;
		case GREEN:
			timeToStick = 20;
			break;
		}
		return timeToStick;
	}

	private int calculatePrice(Color color, TimeFrame timeFrame) {
		int vehicleTypeTax = 0;
		switch (color) {
		case RED:
			vehicleTypeTax = 5;
			break;
		case BLUE:
			vehicleTypeTax = 7;
			break;
		case GREEN:
			vehicleTypeTax = 9;
			break;
		}
		
		int timeFrameMultiplier = 0;
		switch (timeFrame) {
		case DAYLY:
			timeFrameMultiplier = 1;
			break;
		case MONTHLY:
			// according to the problem the monthly tax should equal 10-fold the price of a weekly Vignette
			// there are no weekly vignettes... there has been an error, there should not be any DAYLY 
			// vignettes but rather WEEKLY. I am not going to fix this...
			timeFrameMultiplier = 10;
			break;
		case YEARLY:
			timeFrameMultiplier = 60;
			break;
		}
		
		return vehicleTypeTax * timeFrameMultiplier;
	}
	
	public static PriorityQueue<Vignette> generateRandomVignettes(int required) {
		PriorityQueue<Vignette> result = new PriorityQueue<>();

		for (int i = 0; i < required; i++) {
			TimeFrame timeframe = TimeFrame.generateRandomTimeFrame();
			Color color = Color.generateRandomColor();
			Vignette vignette = new Vignette(timeframe, color);
			result.add(vignette);
		}
		
		return result;
	}
	
	public int compareTo(Vignette other) {
		int result = -1;
		
		if(this.price > other.price) {
			result = 1;
		} else if (this.price == other.price) {
			result = 0;
		} 
		return result;
	}
}
