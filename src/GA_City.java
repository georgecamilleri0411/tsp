public class GA_City {

	int x;
	int y;

	public GA_City (int _x, int _y) {
		this.x = _x;
		this.y = _y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public double distanceTo (GA_City city) {
		return Utilities.getEuclideanDistance(this.x, this.y, city.getX(), city.getY());
	}

	@Override
	public String toString() {
		return getX() + ", " + getY();
	}

}
