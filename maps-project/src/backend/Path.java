package backend;

import java.util.List;

public class Path {

	private float distance;
	private float duration;
	private List<PathSection> steps;
	
	public Path(float distance, float duration, List<PathSection> steps) {
		this.distance = distance;
		this.duration = duration;
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
		String result = "Total Distance: " + distance + " mi\nTotal Duration: " + duration + " mins\n ---------- \n";
		for (PathSection step : steps) {
			result = result + "Mode: " + step.getMode() + "\nStart Location: " + step.getStartLoc() 
			+ "\nEnd Location: " + step.getEndLoc() + "\nDistance: " + step.getDistance() 
			+ " mi\nDuration: " + step.getDuration() + " mins\n ---------- \n";
		}
		
		return result;
	}
	
	public float getDistance() {
		return distance;
	}
	public float getDuration() {
		return duration;
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
	public void setSteps(List<PathSection> steps) {
		this.steps = steps;
	}
	
}
