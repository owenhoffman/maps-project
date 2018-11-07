package backend;

import java.awt.geom.Point2D;

public class OurAPI {

	DirectionCalculator calc;
	
	public OurAPI() {
		calc = new DirectionCalculator();
	}
	
	public void pathToJSON(Path path) {
		
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
	
	public void getDirection(Point2D.Float origin, Point2D.Float destination) {
		
	}
}
