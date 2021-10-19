import java.util.ArrayList;

public class City {

	private int index;
	private int x;
	private int y;
	private static ArrayList<Integer> cityTo = new ArrayList();
	private static ArrayList<Double> eDistance = new ArrayList();

	public City (int index, int x, int y) {
		this.index = index;
		this.x = x;
		this.y = y;
	}

	public static void setCityTo(ArrayList<Integer> cityTo) {
		City.cityTo = cityTo;
	}

	public static void seteDistance(ArrayList<Double> eDistance) {
		City.eDistance = eDistance;
	}

	public static ArrayList<Integer> getCityTo() {
		return cityTo;
	}

	public static ArrayList<Double> geteDistance() {
		return eDistance;
	}

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	/*
Calculates the distances between all the cities in the ArrayList
 */
	public void setDistances() {
		try{
			int c1 = this.getIndex();
			int x1 = this.getX();
			int y1 = this.getY();
			for (City cTo : Utilities.cities) {
				if (c1 != cTo.getIndex()) {
					this.getCityTo().add (cTo.getIndex());
					this.geteDistance().add (Utilities.getEuclideanDistance(x1, y1, cTo.getX(), cTo.getY()));
				}
			}
		} catch (Exception e) {
			System.out.println("An error has occurred - " + e.getMessage());
			e.printStackTrace();
		}
	}

}
