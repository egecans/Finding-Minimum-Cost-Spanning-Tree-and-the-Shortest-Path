import java.util.*;

/** this class for graphing easily.
 *  because the project is relevant with the cities a changes its name.
 * 
 * author egecans
 *
 */
public class RoadMap {
	
	private int nOfCities; 
	private int adjMatrix[][];   // this is for mapping
	
	/** this is a constructor with 1 parameter.
	 * it also creates a Matrix with a size with respect to that parameter
	 * @param nOfCities
	 */
	public RoadMap (int nOfCities) {
		this.nOfCities = nOfCities;
		adjMatrix = new int[nOfCities][nOfCities];  
				
			for(int i=0; i<nOfCities; i++)
				for(int j=0; j<nOfCities; j++)
					adjMatrix[i][j]=Integer.MAX_VALUE;
	
	}
	
	/** this is for adding the ways (edges) with no direction.
	 *  because of that it adds the distance with 2 side of the matrix.
	 * @param id1  first city's id
	 * @param id2  second city's id
	 * @param distance  distance between the those 2 cities
	 */
	public void addUndirectedWay(String id1, String id2, int distance) {
		if (id1.startsWith("c")) {  // this is for avoid the conflict when the c# and d# 's #(number)s' are the same
			int newID = 0;
			int newid2 = Integer.parseInt(id2.substring(1));
			if (distance<adjMatrix[newID][newid2]) {
				adjMatrix[newID][newid2] = distance;
				adjMatrix[newid2][newID] = distance;
				}
		}
		else if (id2.startsWith("c")) {
			int newid1 = Integer.parseInt(id1.substring(1));
			int newID = 0;
			if (distance<adjMatrix[newid1][newID]) {
				adjMatrix[newid1][newID] = distance;
				adjMatrix[newID][newid1] = distance;
			}
		}
		else if (id1.startsWith("d") || id2.startsWith("d")) {
			int newid1 = Integer.parseInt(id1.substring(1));
			int newid2 = Integer.parseInt(id2.substring(1));
			if (distance<adjMatrix[newid1][newid2]) {
				adjMatrix[newid1][newid2] = distance;
				adjMatrix[newid2][newid1] = distance;
				}
		}
	}
	
	/** this is for adding the directed edges.
	 * because all directed edges with c#, there is no need to distinguish them. 
	 * @param id1	first city's id
	 * @param id2	second city's id
	 * @param distance	distance between those 2 cities.
	 */
	public void addDirectedWay(int id1, int id2, int distance) {
		if (distance<adjMatrix[id1][id2])
		adjMatrix[id1][id2] = distance;
	}
	
	
	/** this method is find the adjacent's with respect to city whose id is given by parameter and insert its adjacents a list that 
	 * contains IDs and distance between the given ID city.
	 * @param ID  ID of a city that wants to find its adjacents.
	 * @return	  a list that contains arrays with ID and the distance
	 */
	public List<int[]> getAdjCitieswDistance(int ID){

		List<int[]> adjCities = new ArrayList<int[]>();
		
		for (int i = 0; i < nOfCities ; i++) {
			if (i != ID) {
				if (adjMatrix [ID][i] != Integer.MAX_VALUE) {
					int[] citywDistance = { i ,(int) adjMatrix[ID][i]};
//					System.out.println( ID + " to " + i + " at distance: " + adjMatrix[ID][i]);
					adjCities.add(citywDistance);
				}
			}
			
		}
		return adjCities;
	}

	


	
	
	
	
	
	
}

