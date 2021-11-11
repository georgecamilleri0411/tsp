import java.util.ArrayList;

public class Localities {

    protected ArrayList<Integer> localityID = new ArrayList();
    protected ArrayList<Integer> localityX = new ArrayList();
    protected ArrayList<Integer> localityY = new ArrayList();
    protected ArrayList<Integer> localityFrom = new ArrayList();
    protected ArrayList<Integer> localityTo = new ArrayList();
    protected ArrayList<Double> distance = new ArrayList();


    public ArrayList<Integer> getLocalityID() {
        return localityID;
    }

    public void setLocalityID(ArrayList<Integer> localityID) {
        this.localityID = localityID;
    }

    public ArrayList<Integer> getLocalityX() {
        return localityX;
    }

    public void setLocalityX(ArrayList<Integer> localityX) {
        this.localityX = localityX;
    }

    public ArrayList<Integer> getLocalityY() {
        return localityY;
    }

    public void setLocalityY(ArrayList<Integer> localityY) {
        this.localityY = localityY;
    }

    public ArrayList<Integer> getLocalityFrom() {
        return localityFrom;
    }

    public void setLocalityFrom(ArrayList<Integer> localityFrom) {
        this.localityFrom = localityFrom;
    }

    public ArrayList<Integer> getLocalityTo() {
        return localityTo;
    }

    public void setLocalityTo(ArrayList<Integer> localityTo) {
        this.localityTo = localityTo;
    }

    public ArrayList<Double> getDistance() {
        return distance;
    }

    public void setDistance(ArrayList<Double> distance) {
        this.distance = distance;
    }

}
