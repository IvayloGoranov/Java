package adjacency_list_graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class AdjacencyListGraph {
	
	public static void main(String[] args) {
		
		Graph graph = new Graph(new ArrayList<List<Integer>>()
	            {{
					add(Arrays.asList(3, 6)); // children of node 0 (Ruse)
					add(Arrays.asList(2, 3, 4, 5, 6)); // children of node 1 (Sofia)
					add(Arrays.asList(1, 4, 5)); // children of node 2 (Pleven)
					add(Arrays.asList(0, 1, 5)); // children of node 3 (Varna)
	                add(Arrays.asList(1, 2, 6)); // children of node 4 (Bourgas)
	                add(Arrays.asList(1, 2, 3)); // children of node 5 (Stara Zagora)
	                add(Arrays.asList(0, 1, 4));  // children of node 6 (Plovdiv)
	            }},
	            new String[] { "Ruse", "Sofia", "Pleven", "Varna", "Bourgas", "Stara Zagora", "Plovdiv" }
	        );

		
		
		// Print the nodes and their children
	        for (int nodeIndex = 0; nodeIndex < graph.getChildNodes().size(); nodeIndex++){
	            
	        	System.out.printf("%d -> %s\n", nodeIndex, 
	        			StringUtils.join(graph.getChildNodes().get(nodeIndex)," "));
	        }

	        System.out.println();

	        // Print the node names and their children names
	        for (int nodeIndex = 0; nodeIndex < graph.getChildNodes().size(); nodeIndex++) {
	        	
	        	System.out.printf("%s -> %s\n", graph.getNodeNames()[nodeIndex], 
	        			StringUtils.join(graph.getChildNodes().get(nodeIndex).
	        					stream().map(node -> graph.getNodeNames()[node]).toArray(String[]::new),", "));
	        }
	}
}
