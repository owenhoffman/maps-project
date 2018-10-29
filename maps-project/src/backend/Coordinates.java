package backend;

import java.awt.geom.Point2D;

public class Coordinates {

	private float longitude;
	private float latitude;

	public Coordinates(float latitude, float longitude) {
		this.longitude = longitude;
		this.latitude = latitude;		
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
}
