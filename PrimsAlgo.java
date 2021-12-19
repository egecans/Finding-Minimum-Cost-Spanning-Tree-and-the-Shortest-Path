import java.util.*;

/** this class is for finding shortest spanning tree.
 *  its name is Prims algo because the logic behind that algorithms owner was the Prim.
 * @author egecans
 *
 */
public class PrimsAlgo {
	
	private int nCities, LeylasHT;    //number of cities for creating arrays with suitable size, and LeylasHT for some operations.
	private int[] distance;			  // array for storing the distances (actually lengths)
	private boolean[] processed;	  // array for storing whether it is processed or not.
	RoadMap graph;
	private PriorityQueue<City> cities = new PriorityQueue<City>();  // PQ for sorting the cities wrt its distances.
	private int MSTLength = 1;										 
	private Set<Integer> computing = new HashSet<Integer>();		 // this is for computing whether the all cities has processed or not.
	private Set<Integer> empty = new HashSet<Integer>();			 // this is the empty set whenever a city is processed it is added there.

	/** this is a constructor with 3 parameters
	 * it's also creates some arrays with respect to its parameters and initialize some of them.
	 * @param nCities  	number of Cities that will be traveled for honeymoon.
	 * @param graph	   	roadMap for honeymoon	
	 * @param LeylasHT 	Leyla's hometown
	 */
	public PrimsAlgo (int nCities, RoadMap graph, int LeylasHT) {
		
		this.nCities = nCities;
		this.graph = graph;
		this.LeylasHT = LeylasHT;
		distance = new int[nCities];
		processed = new boolean[nCities];

		for (int i=0; i<nCities ; i++) {
			distance[i] = Integer.MAX_VALUE;
			processed[i] = false;
		}
		
		
		for (int i = 0; i<nCities ; i++) {
			computing.add(i);
		}
		
	}
	
	/** this method find us the minimum spanning tree
	 * 
	 */
	public void MST() {
		
		cities.add(new City (0, 0));
		
		// this loop continue until the cities is empty or the computing equals the empty which means Leyla and Mecnun traveled all the other side's countries.
		while ( !computing.equals(empty) && !cities.isEmpty() ) {
			

			City currCity = cities.poll();
			int index = currCity.getID();
			empty.add(index);
			
			
			if (!processed[index]) {

				
				List<int[]> adjacentCities = graph.getAdjCitieswDistance(index);  //Adjacent cities list of the processing country
				
				
				for (int i = 0; i < adjacentCities.size() ; i++ ) {
					int currID = adjacentCities.get(i)[0];
					int currLength = adjacentCities.get(i)[1];
					

					if (currLength < distance[currID]) {  // if current Length is better (smaller) than before then update it and add it to the PQ.
						
						
						distance[currID] = currLength;
						cities.add(new City (currID, currLength));
						
						
					}
					
				}
				
				processed[index] = true;
				MSTLength += distance[index];
				
				
			}
			
		}
		
	}
	
	public boolean isMSTFinish() {
			return computing.equals(empty);
		}
	
	public int getTax() {
		return MSTLength*2;
	}
	

	
	
	

}
