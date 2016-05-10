package kruskal_oop;

import java.util.ArrayList;
import java.util.List;

public class KruskalAlgorithm {

	public static List<Edge> Kruskal(int numberOfVertices, List<Edge> edges) {
		
        //Initialize parents.
        int[] parent = new int[numberOfVertices];
        for (int i = 0; i < parent.length; i++) {
        	
            parent[i] = i;
        }

        List<Edge> spannigTree = new ArrayList<Edge>();
        for (Edge edge : edges) {
        	
            int rootStartNode = findRoot(edge.getStartNode(), parent);
            int rootEndNode = findRoot(edge.getEndNode(), parent);
            if (rootStartNode != rootEndNode) {
            	
                spannigTree.add(edge);
                parent[rootStartNode] = rootEndNode;
            }
        }

        return spannigTree;
    }

    public static int findRoot(int node, int[] parent) {
    	
        //Find the root parent for the node.
        int root = node;
        while (parent[root] != root) {
        	
            root = parent[root];
        }

        return root;
    }
}
