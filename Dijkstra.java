import java.util.*;

/** this class is for easing the implementation of finding shortest path.
 *  the guy who has discovered logic behind that algorithm was Dijkstra, that's why I use his name.
 * @author egecans
 *
 */
public class Dijkstra {
	
	private int nCities, LeylasHT;
	private int[] distance;  						// this is for storing distances
	private boolean[] processed;					// this is for checking whether the city has processed or not.
	private int[] parent;							// this is for storing parents
	RoadMap graph;
	private Stack<Integer> path = new Stack<Integer>();		// this is for writing creating path, it is used for recursive
	private PriorityQueue<City> cities = new PriorityQueue<City>();  // this is for instead minDist, i use that PQ to sort the cities with respect to their distances automatically. 
	private boolean reached = false;						// this is for understanding whether the algorithm has end in Main file.
	
	/** this is a constructor with 3 parameters.
	 * it's also creates some arrays with respect to its parameters and initialize some of them.
	 * @param nCities    number of Cities in the Mecnun's side of the country
	 * @param graph		 the roadMap that Mecnun can be travelled to reach Leyla
	 * @param LeylasH	 the Leyla's hometown
	 */
	public Dijkstra (int nCities, RoadMap graph, int LeylasH) {
		this.nCities=nCities;
		this.graph=graph;
		this.LeylasHT=LeylasH;
		distance = new int[nCities];
		processed = new boolean [nCities];
		parent = new int[nCities];
		distance [0] = 0;
		processed [0] = false;
		parent[0] = -1;
		for (int i=1; i<nCities; i++) {
			distance[i] = Integer.MAX_VALUE;
			processed[i] = false;
			parent[i] = -1;
		}
	}
		
		
		
		/** That method finds us the short path.
		 * 
		 */
		public void ShortPath() {
			// firstly, Mecnun's town added to the PQ to initialize first because it's the source.
			cities.add(new City(1,0));
			
			// the code has continue while the city class is not empty, unless it breakes by reaching the Leyla's hometown.
			while (!cities.isEmpty()) {
				
				City currCity = cities.poll();  	// en düþük mesafeliyi seçti
				
				int index = currCity.getID()-1;		// ID - 1 is because the city's starts with c1 instead c0.
					

				if (currCity.getID() == LeylasHT) {
					reached = true;
					break;
				}
				

				List<int[]> adjacentCities = graph.getAdjCitieswDistance(currCity.getID());  // this is the adjacent cities list of the current city.

				for (int i = 0; i < adjacentCities.size(); i++) {	//when the city is processed, their adjacent's is updated.

					int currID = adjacentCities.get(i)[0]; 							
					int currDistance = distance[index] + adjacentCities.get(i)[1]; 	

					if ( currDistance < distance[currID-1] && !processed[currID-1] ) {   //if current Distance is smaller than the past, update its items and add it to the PQ for processing next time.
						parent[currID-1] = currCity.getID();	
						distance[currID-1] = currDistance;
						cities.add(new City(currID,currDistance));
						

					}	
				}	
				processed[index]=true;
			}	
		}
		
		
		
		/** this is a recursive code for creating a path.
		 * 
		 * @param IDminus1  city's ID - 1 because the c1.
		 */
		public void createPath(int IDminus1) {
				path.push(IDminus1+1);
			if (IDminus1 == 0)
				return;
			int currInd = parent[IDminus1]-1;
			createPath(currInd);
		}
		
		public int getTotalDistance() {
			return distance[LeylasHT-1];
		}
		
		public int[] getDistance() {
			return distance;
		}

		public int[] getParent() {
			return parent;
		}
		
		/** this is for printing path on the output file easily.
		 * 
		 * @return the printing document
		 */
		public String printPath() {
			String x = "";
			while (!path.isEmpty()) {
				x+=("c"+path.pop()+" ");
			}
			return x.trim();
		}

		public Stack<Integer> getPath() {
			return path;
		}


		public boolean isReached() {
			return reached;
		}
		

		
	
	
		
	
	
	
	

}
