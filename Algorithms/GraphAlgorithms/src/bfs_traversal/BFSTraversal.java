package bfs_traversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSTraversal {
	
	private static String[] nodeNames = new String[] { "Ruse", "Sofia", "Pleven", "Varna", "Bourgas", "Stara Zagora", "Plovdiv" };

	private static ArrayList<List<Integer>> childNodes = new ArrayList<List<Integer>>()
    {{
		add(Arrays.asList(3, 6)); // children of node 0 (Ruse)
		add(Arrays.asList(2, 3, 4, 5, 6)); // children of node 1 (Sofia)
		add(Arrays.asList(1, 4, 5)); // children of node 2 (Pleven)
		add(Arrays.asList(0, 1, 5)); // children of node 3 (Varna)
        add(Arrays.asList(1, 2, 6)); // children of node 4 (Bourgas)
        add(Arrays.asList(1, 2, 3)); // children of node 5 (Stara Zagora)
        add(Arrays.asList(0, 1, 4));  // children of node 6 (Plovdiv)
    }};

    public static void traverseBFS(int node) {
    	
        Queue<Integer> nodes = new LinkedList<Integer>();
        boolean[] visited = new boolean[childNodes.size()];

        // Enqueue the start node to the queue
        visited[node] = true;
        nodes.offer(node);

        // Breadth-First Search (BFS)
        while (nodes.size() != 0) {
        	
            int currentNode = nodes.poll();
            System.out.printf("%d (%s)\n", currentNode, nodeNames[currentNode]);
            
            for (Integer childNode : childNodes.get(currentNode)) {
            	
                if (!visited[childNode]) {
                	
                    nodes.offer(childNode);
                    visited[childNode] = true;
                }
            }
        }
    }
	
	public static void main(String[] args) {
		
		// Start DFS from node 4 (Bourgas)
        traverseBFS(4);
	}
}
