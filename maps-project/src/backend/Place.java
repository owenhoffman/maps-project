package backend;

public class Place {

	private String name;
	private Coordinates coords;

	public Place(float latitude, float longitude) {
		this.name = null;
		this.coords = new Coordinates(latitude, longitude);
	}
	
	public Place(String name, float latitude, float longitude) {
		this.name = name;
		this.coords = new Coordinates(latitude, longitude);
	}
	
	public String toString() {
		return " ---------- \n" + name + "\nCoordinates: " + coords;
	}
	public Coordinates getCoordinates() {
		return coords;
	}
	
	public String getName() {
		return name;
	}
	
	public float getLatitude() {
		return coords.getLatitude();
	}
	
	public float getLongitude() {
		return coords.getLongitude();
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public void setLatitude(float l) {
		coords.setLatitude(l);
	}
	
	public void setLongitude(float l) {
		coords.setLongitude(l);
	}

}
