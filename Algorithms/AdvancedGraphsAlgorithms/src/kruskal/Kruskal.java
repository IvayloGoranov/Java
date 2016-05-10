package kruskal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.javatuples.Triplet;

public class Kruskal {

	private static List<List<Integer>> adjacencyList = new ArrayList<List<Integer>>()
	{{ 
		add(Arrays.asList(3, 5, 8));
		add(Arrays.asList(4, 7));
		add(Arrays.asList(6));
		add(Arrays.asList(0, 5, 6, 8));
		add(Arrays.asList(1, 7));
		add(Arrays.asList(0, 3));
		add(Arrays.asList(2, 3, 8));
		add(Arrays.asList(1, 4));
		add(Arrays.asList(0, 3, 6));
	}};

	    private static List<List<Integer>> edges = new ArrayList<List<Integer>>()
	    {{ 
	    	add(Arrays.asList(0, 3, 9));
	    	add(Arrays.asList(0, 5, 4));
	    	add(Arrays.asList(0, 8, 5));
	    	add(Arrays.asList(3, 5, 2));
	    	add(Arrays.asList(3, 8, 20));
	    	add(Arrays.asList(3, 6, 8));
	    	add(Arrays.asList(6, 8, 7));
	    	add(Arrays.asList(2, 6, 12));
	    	add(Arrays.asList(1, 4, 8));
	    	add(Arrays.asList(1, 7, 7));
	    	add(Arrays.asList(4, 7, 1));
	    }};

	    public static void main(String[] args) {
			
	    	edges = edges.stream().sorted((list1, list2) -> Integer.compare(list1.get(2), list2.get(2))).
	    			collect(Collectors.toList());
	        int[] parentList = new int[adjacencyList.size()];
	        for (int i = 0; i < parentList.length; i++) {
	        	
	            parentList[i] = i;
	        }

	        ArrayList<Triplet<Integer, Integer, Integer>> spanningTree = new ArrayList<Triplet<Integer, Integer, Integer>>();
	        
	        for (List<Integer> edge : edges) {
	        	
	            int rootNode1 = findRootParent(edge.get(0), parentList);
	            int rootNode2 = findRootParent(edge.get(1), parentList);
	            if (rootNode1 != rootNode2) {
	            	
	                spanningTree.add(new Triplet<Integer, Integer, Integer>(edge.get(0), edge.get(1), edge.get(2)));
	                parentList[rootNode2] = rootNode1;
	            }
	        }
	        
	        for (Triplet<Integer, Integer, Integer> edge : spanningTree) {
	        	
	            System.out.println(edge);
	        }
		}
	    
	    private static int findRootParent(int node, int[] parentList)
	    {
	        while (parentList[node] != node) {
	        	
	            node = parentList[node];
	        }

	        return node;
	    }
}
