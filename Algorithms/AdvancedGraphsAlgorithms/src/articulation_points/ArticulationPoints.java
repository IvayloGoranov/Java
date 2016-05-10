package articulation_points;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ArticulationPoints {

	private static ArrayList<List<Integer>> graph = new ArrayList<List<Integer>>()
		    {{
		    	add(Arrays.asList(1, 2, 6, 7, 9));      // children of node 0
		    	add(Arrays.asList(0, 6));               // children of node 1
		    	add(Arrays.asList(0, 7));               // children of node 2
		    	add(Arrays.asList(4));                  // children of node 3
		    	add(Arrays.asList(3, 6, 10));           // children of node 4
		    	add(Arrays.asList(7));                  // children of node 5
		    	add(Arrays.asList(0, 1, 4, 8, 10, 11)); // children of node 6
		    	add(Arrays.asList(0, 2, 5, 9));         // children of node 7
		    	add(Arrays.asList(6, 11));              // children of node 8
		    	add(Arrays.asList(0, 7));               // children of node 9
		    	add(Arrays.asList(4, 6));               // children of node 10
		    	add(Arrays.asList(6, 8));               // children of node 11
		    }};
		    
		    private static boolean[] visited = new boolean[graph.size()];
		    private static Integer[] parent = new Integer[graph.size()];
		    private static int[] depth = new int[graph.size()];
		    private static int[] lowpoint = new int[graph.size()];
		    private static ArrayList<Integer> articulationPoints = new ArrayList<Integer>();

		    public static void main(String[] args) {
				
		    	findArticulationPoints(5, 1);
		        System.out.println("Articulation points: " + StringUtils.join(articulationPoints, ", "));
			}


		    private static void findArticulationPoints(int node, int d) {
		    	
		        visited[node] = true;
		        depth[node] = d;
		        lowpoint[node] = d;
		        int childCount = 0;
		        boolean isArticulation = false;
		        for (Integer childNode : graph.get(node)) {
		        	
		            if (!visited[childNode]) {
		            	
		                parent[childNode] = node;
		                findArticulationPoints(childNode, d + 1);
		                childCount = childCount + 1;
		                if (lowpoint[childNode] >= depth[node]) {
		                	
		                    isArticulation = true;
		                }

		                lowpoint[node] = Math.min(lowpoint[node], lowpoint[childNode]);
		            } else if (childNode != parent[node]) {
		            	
		                lowpoint[node] = Math.min(lowpoint[node], depth[childNode]);
		            }
		        }

		        if ((parent[node] != null && isArticulation) ||
		            (parent[node] == null && childCount > 1)) {
		        	
		            articulationPoints.add(node);
		        }
		    }
}
