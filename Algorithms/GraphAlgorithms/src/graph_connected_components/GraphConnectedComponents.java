package graph_connected_components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GraphConnectedComponents {

	private static ArrayList<List<Integer>> graph;
    private static boolean[] visited;
    
    public static void main(String[] args) {
		
    	graph = readGraph();
        findGraphConnectedComponents();
	}
    
    private static ArrayList<List<Integer>> readGraph() {
        
    	@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
    	int graphSize = Integer.parseInt(scanner.nextLine());
    	ArrayList<List<Integer>> graph = new ArrayList<List<Integer>>();
        for (int i = 0; i < graphSize; i++) {
        	
            String lineArgs = scanner.nextLine();
        	if (lineArgs.length() > 0) {
        		
        		graph.add(Arrays.stream(lineArgs.split("\\s")).map(num -> Integer.parseInt(num)).collect(Collectors.toList())); 
			} else {
				
				graph.add(new ArrayList<Integer>()); 
			}
        }

        return graph;
    }

    private static void findGraphConnectedComponents() {
    	
        visited = new boolean[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
        	
            if (!visited[i]){
            	
                System.out.print("Connected component:");
                DFS(i);
                System.out.println();
            }
        }
    }

    private static void DFS(int node) {
    	
        if (!visited[node]) {
        	
            visited[node] = true;
            for (Integer childNode : graph.get(node)) {
                DFS(childNode);
            }

            System.out.print(" " + node);
        }
    }
}
