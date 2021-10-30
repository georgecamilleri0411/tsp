public class Distance {

	private int fromLocality;
	private int toLocality;
	private double distance;

	public Distance () {}

	public Distance (int fromLocality, int toLocality, double distance) {
		setFromLocality(fromLocality);
		setToLocality(toLocality);
		setDistance(distance);
	}

	public int getFromLocality() {
		return this.fromLocality;
	}

	public void setFromLocality(int fromLocality) {
		this.fromLocality = fromLocality;
	}

	public int getToLocality() {
		return this.toLocality;
	}

	public void setToLocality(int toLocality) {
		this.toLocality = toLocality;
	}

	public double getDistance() {
		return this.distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

}
