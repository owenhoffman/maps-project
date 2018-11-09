package backend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
			//System.out.println(targetURL);

			JSONObject jsonObject = new JSONObject(readURL(targetURL));
			JSONObject legs = jsonObject.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0);
			float totalDistance = convertUnit(legs.getJSONObject("distance").getString("text"));
			float totalDuration = convertUnit(legs.getJSONObject("duration").getString("text"));
			
			ArrayList<PathSection> pathList = new ArrayList<PathSection>();
			if (mode.equals("driving")) {
				PathSection section = new PathSection(totalDistance, totalDuration, mode, origin, destination, "Drive to " + destination);
				pathList.add(section);
				return new Path(totalDistance, totalDuration, origin, destination, pathList);
			}
			
			JSONArray steps = legs.getJSONArray("steps");
			
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
			
			return new Path(totalDistance, totalDuration, origin, destination, pathList);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public float distanceMatrixAPI(Coordinates origin, Coordinates destination) {
		try {
			String targetURL = "https://maps.googleapis.com/maps/api/distancematrix/json?"
					+ "origins=" + origin.getLatitude() + "," + origin.getLongitude() 
					+ "&destinations=" + destination.getLatitude() + "," + destination.getLongitude() 
					+ "&key=" + apiKey;
			String distanceText = new JSONObject(readURL(targetURL)).getJSONArray("rows")
					.getJSONObject(0).getJSONArray("elements").getJSONObject(0)
					.getJSONObject("distance").getString("text");
			return convertUnit(distanceText);
			
		} catch (Exception e) {
			e.printStackTrace();
			return Float.MAX_VALUE;
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
	
	// return nearby train stations
	public List<Place> NearbySearchAPI(Coordinates currentLoc, float radius) {
		try {
			String targetURL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
					+ "location=" + currentLoc.getLatitude() + "," + currentLoc.getLongitude() 
					+ "&radius=" + radius*1609.34 + "&type=train_station"
					+ "&key=" + apiKey;
			
			ArrayList<Place> placeList = new ArrayList<Place>();
			JSONObject jsonObject = new JSONObject(readURL(targetURL));
			JSONArray resultArray = jsonObject.getJSONArray("results");
			for (Object ob : resultArray) {
				JSONObject placeOb = (JSONObject) ob;
				JSONObject locationObject = placeOb.getJSONObject("geometry").getJSONObject("location");
		        float latitude = locationObject.getFloat("lat");
		        float longitude = locationObject.getFloat("lng");
		        String name = placeOb.getString("name");
		        Place place = new Place(name, latitude, longitude);
		        placeList.add(place);
			}
	        if (placeList.isEmpty()) {
	        	return null;
	        }
	        return placeList;
	        
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
		} else if (unit.equals("km")) {
			num = num / (float) 1.60934;
		} else if (unit.equals("m")) {
			num = num / (float) 1609.34;
		} // Duration conversion
		else if (unit.equals("hours") || unit.equals("hour")) {
			num = num * 60;
			num = num + Float.parseFloat(numAndUnit[2]);
		}
		
		return num;
	}
	
}