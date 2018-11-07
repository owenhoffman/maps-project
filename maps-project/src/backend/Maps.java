package backend;

public class Maps {
	public static void main(String[] args) {
		
		String apiKey = "AIzaSyBrjEuT3xE8G7yalJsZ4Flqc1mADZSsA8g";
		
		GoogleAPI google = new GoogleAPI(apiKey);
		
		Path test = google.directionAPI(google.placeAPI("Greenbelt_Metro_Station"), 
				google.placeAPI("Dupont_Circle_station"), "transit");

		System.out.print(test);
	}
}
