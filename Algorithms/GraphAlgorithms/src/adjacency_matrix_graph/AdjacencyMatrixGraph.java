package adjacency_matrix_graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class AdjacencyMatrixGraph {
	
	private static int[][] graph =
        { // 0  1  2  3  4  5  6
           { 0, 0, 0, 1, 0, 0, 1 }, // node 0
           { 0, 0, 1, 1, 1, 1, 1 }, // node 1
           { 0, 1, 0, 0, 1, 1, 0 }, // node 2
           { 1, 1, 0, 0, 0, 1, 0 }, // node 3
           { 0, 1, 1, 0, 0, 0, 1 }, // node 4
           { 0, 1, 1, 1, 0, 0, 0 }, // node 5
           { 1, 1, 0, 0, 1, 0, 0 }, // node 6
        };
	
    private static String[] nodeNames = new String[] { "Ruse", "Sofia", "Pleven", "Varna", "Bourgas", "Stara Zagora", "Plovdiv" };
	
	public static void main(String[] args) {
		
		//int[][] graph = ReadGraphMatrix();

        printGraphMatrix(graph);

        System.out.println();

        printNodesWithChildren(graph, nodeNames);
	}
	
	private static int[][] readGraphMatrix() {
		
        Scanner scanner = new Scanner(System.in);
		int nodesCount = Integer.parseInt(scanner.nextLine());
		int[][] graph = new int[nodesCount][nodesCount];

        // Read children for each node
        for (int parentNode = 0; parentNode < nodesCount; parentNode++) {
        	
            Integer[] childNodes = Arrays.stream(scanner.nextLine().split("\\s")).
            		map(i -> Integer.parseInt(i)).toArray(Integer[]::new);
            for (Integer childNode : childNodes) {
				
            	graph[parentNode][childNode] = 1;
			}
        }

        return graph;
    }

    private static void printGraphMatrix(int[][] graph) {
    	
        for (int row = 0; row < graph.length; row++) {
        	
            for (int col = 0; col < graph[row].length; col++) {
            	
                System.out.print(graph[row][col] + " ");
            }

           System.out.println();
        }
    }

    private static void printNodesWithChildren(int[][] graph, String[] nodeNames)
    {
        for (int row = 0; row < graph.length; row++) {
        	
            ArrayList<String> childNodes = new ArrayList<String>();
            for (int col = 0; col < graph[row].length; col++) {
            	
                if (graph[row][col] != 0) {
                	
                    childNodes.add(nodeNames[col]);
                }
            }
            
            System.out.printf("%s --> %s\n", nodeNames[row],
                    StringUtils.join(childNodes,", "));
        }
    }
}
