public class Distance {

	private String cityFromTo = "";
	private double distance;

	public Distance() {}

	public Distance(int fromCity, int toCity, double distance) {
		setCityFromTo(String.valueOf(fromCity) + "|" + String.valueOf(toCity));
		setDistance(distance);
	}

	public double getDistance() {
		return this.distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getCityFromTo() {
		return cityFromTo;
	}

	private void setCityFromTo(String cityFromTo) {
		this.cityFromTo = cityFromTo;
	}
}
