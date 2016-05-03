package topological_sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class TopologicalSortingDFS {

	public static void main(String[] args) {
		
		ArrayList<List<Integer>> graph = new ArrayList<List<Integer>>()
	    {{
			add(Arrays.asList(3, 5));
			add(Arrays.asList(2));
			add(Arrays.asList());
			add(Arrays.asList(5));
	        add(Arrays.asList(0, 1));
	        add(Arrays.asList(1, 2));
	    }};
	    
	 // Calculate the predecessorsCount
        int[] predecessorsCount = new int[graph.size()];
        for (int node = 0; node < graph.size(); node++) {
        	
            for (Integer childNode : graph.get(node)) {
            	
                predecessorsCount[childNode]++;
            }
        }

        // Topological sorting: source removal algorithm
        boolean[] isRemoved = new boolean[graph.size()];
        List<Integer> removedNodes = new ArrayList<Integer>();
        boolean nodeRemoved = true;
        while (nodeRemoved) {
        	
            nodeRemoved = false;
            for (int node = 0; node < graph.size(); node++) {
                
            	if (predecessorsCount[node] == 0 && !isRemoved[node]) {
                	
                    // Found a node with 0 predecessors -> remove it from the graph
                    for (Integer childNode : graph.get(node)) {
                    	
                        predecessorsCount[childNode]--;
                    }

                    isRemoved[node] = true;
                    removedNodes.add(node);
                    nodeRemoved = true;
                    break;
                }
            }
        }

        if (removedNodes.size() == graph.size()) {
        	
            System.out.println("Topological sorting: " +
                StringUtils.join(removedNodes, " "));
        } else {
        	
           System.out.println("A cycle detected in the graph.");
        }
    }
}
