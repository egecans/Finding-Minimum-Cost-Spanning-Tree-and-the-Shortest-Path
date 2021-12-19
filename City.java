/** this class is storing cities
 * 
 * @author egecans
 *
 */
public class City implements Comparable<City> {
	
	private int ID, distance;
	
	/** constructor with 2 parameters.
	 * 
	 * @param ID  ID of city except Leyla's in honeymoon, i make its ID 0 to prevent collision.
	 * @param distance	distance that must be got over to come this city.
	 */
	public City (int ID, int distance) {
		this.distance=distance;
		this.ID = ID;
	}

	public int getID() {
		return ID;
	}

	public int getDistance() {
		return distance;
	}
	
	/** comparable with its distance.
	 * 
	 */
	public int compareTo(City other) {
		return (distance-other.getDistance());
	}

}
