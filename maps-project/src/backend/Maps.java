package backend;

public class Maps {
	public static void main(String[] args) {
		
		GoogleAPI google = new GoogleAPI("AIzaSyBrjEuT3xE8G7yalJsZ4Flqc1mADZSsA8g");
		OurAPI api = new OurAPI();
		
		try {
			Path path = api.getDirection(google.placeAPI("university_of_maryland"),
					google.placeAPI("baltimore_md"));
			System.out.println(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
