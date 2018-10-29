package backend;

import java.awt.geom.Point2D;

public class Place {

	private long longitude;
	private long latitude;
	private String name;

	public Place(int longitude, int latitude) {
		this.longitude = longitude;
		this.latitude = latitude;		
	}
	
	public Place(String name, int longitude, int latitude) {
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public Point2D getCoordinates() {
		return new Point2D(longitude, latitude);
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
