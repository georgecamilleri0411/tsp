public class Distance_old {

	private int fromCity;
	private int toCity;
	private double distance;

	public Distance_old() {}

	public Distance_old(int fromCity, int toCity, double distance) {
		setFromCity(fromCity);
		setToCity(toCity);
		setDistance(distance);
	}

	public int getFromCity() {
		return this.fromCity;
	}

	public void setFromCity(int fromCity) {
		this.fromCity = fromCity;
	}

	public int getToCity() {
		return this.toCity;
	}

	public void setToCity(int toCity) {
		this.toCity = toCity;
	}

	public double getDistance() {
		return this.distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

}
