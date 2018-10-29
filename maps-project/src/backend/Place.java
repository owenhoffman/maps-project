package backend;

import java.awt.geom.Point2D;

public class Place {

	private Coordinates coords;
	private String name;

	public Place(int latitude, int longitude) {
		this.coords = new Coordinates(latitude, longitude);		
	}
	
	public Place(String name, int latitude, int longitude) {
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public Point2D getCoordinates() {
		return coords;
	}
	
	public long getLatitude() {
		return latitude;
	}
	
	public long getLongitude() {
		return longitude;
	}
	
	public void setLatitude(long l) {
		latitude = l;
	}
	
	public void setLongitude(long l) {
		longitude = l;
	}
	
	public void setName(String n) {
		name = n;
	}

}
