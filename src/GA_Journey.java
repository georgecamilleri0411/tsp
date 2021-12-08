import java.util.ArrayList;
import java.util.Collections;

public class GA_Journey {

	// ArrayList of GA_City to store the cities available
	private ArrayList<GA_City> journey = new ArrayList<>();

	// GA-specific values
	private double fitness = 0;
	private double distance = 0;

	public GA_Journey() {
		// Populate the journey ArrayList with nulls
		// (for the number of cities available + 1 to ensure a complete circuit)
		for (int c = 0; c <= GA_JourneyMaster.numberOfCities(); c++) {
			journey.add(null);
		}
		generateRandomJourney();
	}

	public GA_Journey(ArrayList<GA_City> _cityList) {
		this.journey = _cityList;
		// Add the first locality as the ending point.
		this.journey.add (_cityList.get(0));
	}

	/*
	Populate the journey ArrayList of City and shuffle it to create a random order.
	 */
	public void generateRandomJourney() {
		for (int c = 0; c < GA_JourneyMaster.numberOfCities(); c++) {
			placeCity(c, GA_JourneyMaster.getCity((c)));
		}
		Collections.shuffle(journey);
		// Add the city at the first element, at the end of the journey, to complete the circuit.
		// First, check where there is a null element
		int n = journey.indexOf(null);
		if (n != -1) {
			placeCity(n, journey.get((journey.size() - 1)));
			placeCity((journey.size() - 1), journey.get(0));
		}
	}

	/*
	Retrieves a city from the journey at the order number specified
	 */
	public GA_City getJourneyCity(int journeyOrder) {
		return journey.get(journeyOrder);
	}

	/*
	Places a city at the order number specified
	 */
	public void placeCity (int journeyOrder, GA_City city) {
		journey.set(journeyOrder, city);
		// Reset fitness and distance
		fitness = 0;
		distance = 0;
	}

	/*
	Calculates a fitness level based on the distance of the journey.
	The inverse (1/distance) will be used, to ensure that the shorter
	the journey, the larger the fitness level
	 */
	public double getJourneyFitness() {
		if (fitness == 0) {
			fitness = 1/getJourneyDistance();
		}
		return fitness;
	}

	/*
	Calculates and returns the distance for this journey
	 */
	public double getJourneyDistance() {
		double journeyDistance = 0;
		if (distance == 0) {
			for (int c = 0; c < journeyCities(); c++) {
				GA_City fromCity = getJourneyCity(c);
				GA_City toCity;
				if ((c + 1) < journeyCities()) {
					toCity = getJourneyCity(c + 1);
				} else {
					toCity = getJourneyCity(0);
				}
				journeyDistance += fromCity.distanceTo(toCity);
			}
		}
		return journeyDistance;
	}

	/*
	Returns the number of cities for this journey
	 */
	public int journeyCities() {
		return journey.size();
	}

	/*
	Returns True is the city specified already exists in this journey
	 */
	public boolean cityExists (GA_City city) {
		return journey.contains(city);
	}

}
