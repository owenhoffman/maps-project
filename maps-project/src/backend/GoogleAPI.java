package backend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.json.*;
import backend.Path;
import backend.PathSection;

public class GoogleAPI {
	
	private String apiKey;
	
	public GoogleAPI(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public Path directionAPI(Coordinates origin, Coordinates destination, String mode) {
		try {
			// check mode of transportation
			if (mode == null) {
				mode = "driving";
			} else if (!mode.equals("driving") && !mode.equals("walking") && 
					!mode.equals("bicycling") && !mode.equals("transit")) {
				throw new Exception("Invalid mode of transportation.");
			}
			
			String targetURL = "https://maps.googleapis.com/maps/api/directions/json?"
					+ "origin=" + origin.getLatitude() + "," + origin.getLongitude() 
					+ "&destination=" + destination.getLatitude() + "," + destination.getLongitude() 
					+ "&mode=" + mode + "&key=" + apiKey;
			
			// I have it for debugging purpose.
			System.out.println(targetURL);

			JSONObject jsonObject = new JSONObject(readURL(targetURL));
			JSONObject legs = jsonObject.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0);
			float totalDistance = convertUnit(legs.getJSONObject("distance").getString("text"));
			float totalDuration = convertUnit(legs.getJSONObject("duration").getString("text"));
			JSONArray steps = legs.getJSONArray("steps");
			
			ArrayList<PathSection> pathList = new ArrayList<PathSection>();
			for (Object step : steps) {
				JSONObject stepOb = (JSONObject) step;
				float distance = convertUnit(stepOb.getJSONObject("distance").getString("text"));
				float duration = convertUnit(stepOb.getJSONObject("duration").getString("text"));
				String pathMode = stepOb.getString("travel_mode");
				String instruction = stepOb.getString("html_instructions");
				JSONObject startLoc = stepOb.getJSONObject("start_location");
				JSONObject endLoc = stepOb.getJSONObject("end_location");
				
				PathSection section = new PathSection(distance, duration, pathMode, 
						new Coordinates(startLoc.getFloat("lat"), startLoc.getFloat("lng")),
						new Coordinates(endLoc.getFloat("lat"), endLoc.getFloat("lng")),
						instruction);
				pathList.add(section);
			}
			
			return new Path(totalDistance, totalDuration, pathList);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void distanceMatrixAPI(String origin, String destination) {
		try {
			String targetURL = "https://maps.googleapis.com/maps/api/distancematrix/json?"
					+ "origins=" + origin + "&destinations=" + destination 
					+ "&key=" + apiKey;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Coordinates placeAPI(String place) {
		try {
			String targetURL = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?"
					+ "input=" + place
					+ "&inputtype=textquery&fields=geometry&key=" + apiKey;
	        
	        JSONObject jsonObject = new JSONObject(readURL(targetURL));
	        JSONObject locationObject = jsonObject.getJSONArray("candidates").getJSONObject(0)
	        		.getJSONObject("geometry").getJSONObject("location");
	        float latitude = locationObject.getFloat("lat");
	        float longitude = locationObject.getFloat("lng");
	        
	        return new Coordinates(latitude, longitude);
	        
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// URL reader method to get JSON String.
	private static String readURL(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}
	
	// convert distance and duration to right units.
	private static float convertUnit(String text) {
		String[] numAndUnit = text.split("\\s+");
		float num = Float.parseFloat(numAndUnit[0].replaceAll(",", ""));
		String unit = numAndUnit[1];
		
		// Distance conversion
		if (unit.equals("ft")) {
			num = num / 5280;
		} // Duration conversion
		else if (unit.equals("hours") || unit.equals("hour")) {
			num = num * 60;
			num = num + Float.parseFloat(numAndUnit[2]);
		}
		
		return num;
	}
	
}