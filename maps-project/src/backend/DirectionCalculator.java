package backend;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectionCalculator {

	GoogleAPI google;
	
	public DirectionCalculator() {
		String apiKey = "AIzaSyBrjEuT3xE8G7yalJsZ4Flqc1mADZSsA8g";
		google = new GoogleAPI(apiKey);
	}
	
	public Path getDirection(Coordinates origin, Coordinates destination) {
		// user assumptions
		boolean bike = false;
		float carRadius = 3; // mi
		
		ArrayList<PathSection> newPath = new ArrayList<PathSection>();
		
		// if destination within radius, take car
		float carDistance = google.distanceMatrixAPI(origin, destination);

		if (carDistance <= carRadius) {
			PathSection pathSec = new PathSection("driving", origin, destination);
			newPath.add(pathSec);
			return new Path(newPath);
		}
		
		Path originalPath = google.directionAPI(origin, destination, "transit");
		List<Place> nearbyStations = google.NearbySearchAPI(origin, carRadius);
		
		if (nearbyStations == null) {
			System.out.println("No nearby station shortcut.");
			return originalPath;
		}
		
		// Goal is to minimize time here
		float totalDuration = originalPath.getDuration();
		PriorityQueue<Path> pQueue = new PriorityQueue<Path>(); 
		for (Place station : nearbyStations) {
			System.out.println("Checking: " + station.getName() + "..........");
			Path transitSec = google.directionAPI(station.getCoordinates(), destination, "transit");
			// combine the sections
			pQueue.add(transitSec);
		}
		Path fastestTransit = pQueue.peek();
		Path carSec = google.directionAPI(origin, fastestTransit.getOrigin(), "driving");
		Path p = new Path(carSec.getDistance() + fastestTransit.getDistance(),
				carSec.getDuration() + fastestTransit.getDuration(), origin, destination,
				Stream.concat(carSec.getSteps().stream(), fastestTransit.getSteps().stream())
                .collect(Collectors.toList()));
		if (p.getDuration() < totalDuration) {
			return p;
		}
		return originalPath;
	}
}
