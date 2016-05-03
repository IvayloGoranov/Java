package recursive_dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import adjacency_list_graph.Graph;

public class RecursiveDFS {

	private static ArrayList<List<Integer>> graph = new ArrayList<List<Integer>>()
    {{
		add(Arrays.asList(3, 6)); // children of node 0 (Ruse)
		add(Arrays.asList(2, 3, 4, 5, 6)); // children of node 1 (Sofia)
		add(Arrays.asList(1, 4, 5)); // children of node 2 (Pleven)
		add(Arrays.asList(0, 1, 5)); // children of node 3 (Varna)
        add(Arrays.asList(1, 2, 6)); // children of node 4 (Bourgas)
        add(Arrays.asList(1, 2, 3)); // children of node 5 (Stara Zagora)
        add(Arrays.asList(0, 1, 4));  // children of node 6 (Plovdiv)
    }};
    
    private static String[] nodeNames = new String[] { "Ruse", "Sofia", "Pleven", "Varna", "Bourgas", "Stara Zagora", "Plovdiv" };
    
    private static HashSet<Integer> visited;
    
    private static void recursiveDFS(int node) {
    	
        if (!visited.contains(node)) {
        	
            visited.add(node);
            System.out.printf("%d (%s)\n", node, nodeNames[node]);

            for (int i = 0; i < graph.get(node).size(); i++) {
            	
                recursiveDFS(graph.get(node).get(i));
            }
        }
    }
    
    public static void main(String[] args) {
		
    	visited = new HashSet<Integer>();

        // Start DFS from node 4 (Bourgas)
        recursiveDFS(4);
	}
}
