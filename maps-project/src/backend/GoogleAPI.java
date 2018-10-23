package backend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class GoogleAPI {
	
	private String apiKey;
	
	public GoogleAPI(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public void directionAPI(String origin, String destination, String mode) {
		try {
			// check mode of transportation
			if (mode == null) {
				mode = "driving";
			} else if (!mode.equals("driving") && !mode.equals("walking") && 
					!mode.equals("bicycling") && !mode.equals("transit")) {
				throw new Exception("Invalid mode of transportation.");
			}
			
			String targetURL = "https://maps.googleapis.com/maps/api/directions/json?"
					+ "origin=" + origin + "&destination=" + destination 
					 + "&mode=" + mode + "&key=" + apiKey;
			URL url = new URL(targetURL);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String temp = "";

			while ((temp = br.readLine()) != null) {
				System.out.println(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void distanceMatrixAPI(String origin, String destination) {
		try {
			String targetURL = "https://maps.googleapis.com/maps/api/distancematrix/json?"
					+ "origins=" + origin + "&destinations=" + destination 
					+ "&key=" + apiKey;
			URL url = new URL(targetURL);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String temp = "";

			while ((temp = br.readLine()) != null) {
				System.out.println(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}