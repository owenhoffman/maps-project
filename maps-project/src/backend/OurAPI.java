package backend;

import java.awt.geom.Point2D;

public class OurAPI {

	DirectionCalculator calc;
	GoogleAPI google;
	
	public OurAPI() {
		String apiKey = "AIzaSyBrjEuT3xE8G7yalJsZ4Flqc1mADZSsA8g";
		calc = new DirectionCalculator();
		google = new GoogleAPI(apiKey);
	}
	
	// Note: this is not returning void
	public Path getDirection(String origin, String destination) throws Exception {
		
		// check validity of arguments
		if (origin == null || destination == null) {
			throw new Exception("Missing argument exception.");
		}
		
		// pass arguments to DirectionCalculator for result
		
		return null;
	}
	
	public Path getDirection(Coordinates origin, Coordinates destination) throws Exception {
		// check validity of arguments
		if (origin == null || destination == null) {
			throw new Exception("Missing argument exception.");
		}
		
		Path originalPath = google.directionAPI(origin, destination, "transit");
		return calc.getDirection(originalPath);
	}
}
