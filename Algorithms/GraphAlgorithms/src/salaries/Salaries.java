package salaries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Salaries {

	private static ArrayList<List<Integer>> graph;
    static boolean[] visited; //To keep track of visited nodes.
    static int[] salaries; //To hold the salary of each employee.
    
    public static void main(String[] args) {
		
    	readGraph();
        
        for (int node = 0; node < graph.size(); node++) {
        	
            if (graph.get(node).size() == 0) {
            	
                salaries[node] = 1;
            }
        }

        int[] predecessorsCount = new int[graph.size()]; //To track predecessors.
        for (int node = 0; node < graph.size(); node++) {
        	
            for (Integer childNode : graph.get(node)) {
            	
                predecessorsCount[childNode]++;
            }
        }

        for (int node = 0; node < graph.size(); node++) {
        	
            if (predecessorsCount[node] > 0) {
            	
                continue;
            }

            calcSalariesDFS(node); //The graph traversal will start only from nodes without predecessors.
        }

        System.out.println(Arrays.stream(salaries).sum()); //Printing the total amount of salaries, the sum of all individual salaries.
	}
    
    private static void readGraph() {
    	
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
    	int graphLength = Integer.parseInt(scanner.nextLine());
        graph = new ArrayList<List<Integer>>();
        visited = new boolean[graphLength];
        salaries = new int[graphLength];
        
        for (int node = 0; node < graph.size(); node++) {
        	
            String childNodesArgs = scanner.nextLine();
            graph.set(node, new ArrayList<Integer>());
            for (int nodeValue = 0; nodeValue < childNodesArgs.length(); nodeValue++) {
            	
                if (childNodesArgs.charAt(nodeValue) == 'Y') {
                	
                    graph.get(node).add(nodeValue);
                }
            }
        }
    }

    static void calcSalariesDFS(int employee) {
        
    	if (visited[employee] == false) {
    		
            visited[employee] = true;
            for (Integer childNode : graph.get(employee)) {
            	
                calcSalariesDFS(childNode);
            }

            for (int childNode : graph.get(employee)) {
            	
                salaries[employee] = salaries[employee] + salaries[childNode];
            }
        }
    }
}
