package backend;

import java.util.List;

public class Path implements Comparable<Path> {

	private float distance;
	private float duration;
	private Coordinates origin;
	private Coordinates destination;
	private List<PathSection> steps;
	
	public Path(float distance, float duration, Coordinates origin, Coordinates destination, List<PathSection> steps) {
		this.distance = distance;
		this.duration = duration;
		this.origin = origin;
		this.destination = destination;
		this.steps = steps;
	}
	
	public Path(List<PathSection> steps) {
		this.steps = steps;
		
		// calculate total distance and duration here.
		float totalDistance = 0;
		float totalDuration = 0;
		for (PathSection step : steps) {
			
		}
		
		distance = totalDistance;
		duration = totalDuration;
	}
	
	public String toString() {
		String result = "----- Path -----\nTotal Distance: " + distance + " mi\n"
				+ "Total Duration: " + duration + " mins\n"
				+ "Origin: " + origin + "\nDestination: " + destination + "\n ---------- \n";
		for (PathSection step : steps) {
			result = result + step.getInstruction() + "\nMode: " + step.getMode() 
			+ "\nStart Location: " + step.getStartLoc() + "\nEnd Location: " + step.getEndLoc() 
			+ "\nDistance: " + step.getDistance() + " mi\nDuration: " 
			+ step.getDuration() + " mins\n ---------- \n";
		}
		
		return result;
	}

	public int compareTo(Path p) {
		int durationComp = Float.compare(duration, p.duration);
		if (durationComp != 0)
			return durationComp;
		return Float.compare(distance, p.distance);
	}

	public float getDistance() {
		return distance;
	}
	public float getDuration() {
		return duration;
	}
	public Coordinates getOrigin() {
		return origin;
	}
	public Coordinates getDestination() {
		return destination;
	}
	public List<PathSection> getSteps() {
		return steps;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public void setDuration(float duration) {
		this.duration = duration;
	}
	public void setOrigin(Coordinates origin) {
		this.origin = origin;
	}
	public void setDestination(Coordinates destination) {
		this.destination = destination;
	}
	public void setSteps(List<PathSection> steps) {
		this.steps = steps;
	}
	
}
