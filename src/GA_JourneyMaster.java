import java.util.ArrayList;

public class GA_JourneyMaster {

	// ArrayList of City to store the cities available
	private static ArrayList<GA_City> journeyCities = new ArrayList<>();

	// Retrieve the City at the element index specified
	public static GA_City getCity (int cityIndex) {
		return journeyCities.get(cityIndex);
	}

	// Add a new city to the journeyCities ArrayList
	public static void addNewCity (GA_City newCity) {
		journeyCities.add(newCity);
	}

	// Retrieve the number of cities in journeyCities
	public static int numberOfCities() {
		return journeyCities.size();
	}

}
