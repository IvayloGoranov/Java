package dijkstra_shortest_path;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.lang3.StringUtils;

public class DijkstraShortestPath {

	private static int[][] weights = new int[][] { 
	        // 0   1   2   3   4   5   6   7   8   9  10  11
	         { 0,  0,  0,  0,  0,  0, 10,  0, 12,  0,  0,  0 }, // 0
	         { 0,  0,  0,  0, 20,  0,  0, 26,  0,  5,  0,  6 }, // 1
	         { 0,  0,  0,  0,  0,  0,  0, 15, 14,  0,  0,  9 }, // 2
	         { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  7,  0 }, // 3
	         { 0, 20,  0,  0,  0,  5, 17,  0,  0,  0,  0, 11 }, // 4
	         { 0,  0,  0,  0,  5,  0,  6,  0,  3,  0,  0, 33 }, // 5
	         {10,  0,  0,  0, 17,  6,  0,  0,  0,  0,  0,  0 }, // 6
	         { 0, 26, 15,  0,  0,  0,  0,  0,  0,  3,  0, 20 }, // 7
	         {12,  0, 14,  0,  0,  3,  0,  0,  0,  0,  0,  0 }, // 8
	         { 0,  5,  0,  0,  0,  0,  0,  3,  0,  0,  0,  0 }, // 9
	         { 0,  0,  0,  7,  0,  0,  0,  0,  0,  0,  0,  0 }, // 10
	         { 0,  6,  9,  0, 11, 33,  0, 20,  0,  0,  0,  0 }, // 11
	    };
	    
	    private static ArrayList<Integer> shortestPath = new ArrayList<Integer>();
	    
	    public static void main(String[] args) {
			
	    	System.out.println("Shortest Distance: " + findShortestPathDijkstra(0, 9));
	    	System.out.println("Shortest Path = " + StringUtils.join(shortestPath, ", "));
		}
	    
	    private static int findShortestPathDijkstra(int startNode, int endNode) {
	    	
	        int[] distanceToStart = new int[weights[0].length];
	        for (int i = 0; i < distanceToStart.length; i++) {
	        	
	            distanceToStart[i] = Integer.MAX_VALUE;
	        }
	        
	        distanceToStart[startNode] = 0;

	        boolean[] visited = new boolean[weights[0].length];
	        
	        int[] predecessor = new int[weights[0].length];
	        for (int i = 0; i < predecessor.length; i++){
	        	
	            predecessor[i] = -1;
	        }
	        
	        
	        while (true) {
	        	
	            int minDistance = Integer.MAX_VALUE;
	            int minNode = 0;
	            for (int node = 0; node < weights[0].length; node++) {
	            	
	                if ((visited[node] == false) && (distanceToStart[node] < minDistance)) {
	                    
	                	minDistance = distanceToStart[node];
	                    minNode = node;
	                }
	            }

	            if (minDistance == Integer.MAX_VALUE) {
	                
	            	break;
	            }

	            visited[minNode] = true;
	            for (int node = 0; node < weights[0].length; node++) {
	            	
	                if (weights[minNode][node] > 0) {
	                	
	                    minDistance = distanceToStart[minNode] + weights[minNode][node];
	                    if (minDistance < distanceToStart[node]) {
	                    	
	                        distanceToStart[node] = minDistance;
	                        predecessor[node] = minNode;
	                    }
	                }
	            }
	        }

	        if (distanceToStart[endNode] == Integer.MAX_VALUE) {
	        	
	            return -1; //No such path exists.
	        }

	        buildPath(predecessor, startNode, endNode);
	        
	        return distanceToStart[endNode];
	    }
	    
	    private static void buildPath(int[] predecessor, int startNode, int endNode) {
	    	
	        int currentNode = endNode;
	        while (currentNode != startNode) {
	        	
	            shortestPath.add(currentNode);
	            currentNode = predecessor[currentNode];
	        }

	        shortestPath.add(startNode);
	        Collections.reverse(shortestPath);
	    }
}
