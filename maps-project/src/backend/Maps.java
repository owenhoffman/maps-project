package backend;

public class Maps {
	public static void main(String[] args) {
		
		String apiKey = "AIzaSyBrjEuT3xE8G7yalJsZ4Flqc1mADZSsA8g";
		
		GoogleAPI google = new GoogleAPI(apiKey);
		
		google.directionAPI("Nebraska_Innovation_Campus", "University_of_Nebraska_Lincoln", "driving");

	}
}
