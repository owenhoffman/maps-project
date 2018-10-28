package backend;

import java.awt.geom.Point2D;

public class OurAPI {

	DirectionCalculator calc;
	
	public OurAPI() {
		calc = new DirectionCalculator();
	}
	
	// Note: this is not returning void
	public void getDirection(String origin, String destination) throws Exception {
		if (origin == null || destination == null) {
			throw new Exception("Missing argument exception.");
		}
		
	}
	
	public void getDirection(Point2D.Float origin, Point2D.Float destination) {
		
	}
}
