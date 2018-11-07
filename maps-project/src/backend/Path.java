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
