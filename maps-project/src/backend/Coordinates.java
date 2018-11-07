package backend;

public class Coordinates {

	private float longitude;
	private float latitude;

	public Coordinates(float latitude, float longitude) {
		this.longitude = longitude;
		this.latitude = latitude;		
	}
	
	public String toString() {
		return latitude + ", " + longitude;
	}
	
	public float getLatitude() {
		return latitude;
	}
	
	public float getLongitude() {
		return longitude;
	}
	
	public void setLatitude(float l) {
		latitude = l;
	}
	
	public void setLongitude(float l) {
		longitude = l;
	}
}
