package backend;

public class OurAPI {

	DirectionCalculator calc;
	
	public OurAPI() {;
		calc = new DirectionCalculator();
	}
	
	public Path getDirection(Coordinates origin, Coordinates destination) throws Exception {
		// check validity of arguments
		if (origin == null || destination == null) {
			throw new Exception("Missing argument exception.");
		}
		
		return calc.getDirection(origin, destination);
	}
}
