package backend;

public class PathSection {
	private float distance;
	private float duration;
	private String mode;
	private Coordinates startLoc;
	private Coordinates endLoc;
	private String instruction;
	
	public PathSection(float distance, float duration, String mode, 
			Coordinates startLoc, Coordinates endLoc, String instruction) {
		this.distance = distance;
		this.duration = duration;
		this.mode = mode;
		this.startLoc = startLoc;
		this.endLoc = endLoc;
		this.instruction = instruction;
	}
	
	public PathSection(float distance, float duration, String mode, 
			Coordinates startLoc, Coordinates endLoc) {
		this.distance = distance;
		this.duration = duration;
		this.mode = mode;
		this.startLoc = startLoc;
		this.endLoc = endLoc;
	}
	
	public float getDistance() {
		return distance;
	}
	public float getDuration() {
		return duration;
	}
	public String getMode() {
		return mode;
	}
	public Coordinates getStartLoc() {
		return startLoc;
	}
	public Coordinates getEndLoc() {
		return endLoc;
	}
	public String getInstruction() {
		return instruction;
	}
}