package hashmap_graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class HashMapGraph {
	
	private static LinkedHashMap<String, List<String>> graph = new LinkedHashMap<String, List<String>>()
	{{
        put("Sofia", Arrays.asList("Plovdiv", "Varna", "Bourgas", "Pleven", "Stara Zagora"));
        put("Plovdiv", Arrays.asList("Bourgas", "Ruse"));
        put("Varna", Arrays.asList("Ruse", "Stara Zagora"));
        put("Bourgas", Arrays.asList("Plovdiv", "Pleven"));
        put("Ruse", Arrays.asList("Varna", "Plovdiv"));
        put("Pleven", Arrays.asList("Bourgas", "Stara Zagora"));
        put("Stara Zagora", Arrays.asList("Varna", "Pleven"));
	}};
	
	public static void main(String[] args) {
		
		//graph = readGraph();

        printGraph(graph);
	}
	
	static LinkedHashMap<String, List<String>> readGraph() {
		
		LinkedHashMap<String, List<String>> graph = new LinkedHashMap<String, List<String>>();

        // Read a sequence of edges {parent, child} until an empty line is entered
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
        while (!line.equals(""))
        {
            String[] edge = line.split("\\s");

            String parent = edge[0];
            String child = edge[1];

            if (!graph.containsKey(parent)) {
            	
                graph.put(parent, new ArrayList<String>());
            }

            graph.get(parent).add(child);

            if (parent != child) {
            	
                if (!graph.containsKey(child)) {
                	
                    graph.put(child, new ArrayList<String>());
                }

                graph.get(child).add(parent);
            }

            line = scanner.nextLine();
        }

        return graph;
    }

    static void printGraph(LinkedHashMap<String, List<String>> graph) {
    	
        for (Map.Entry<String, List<String>> node : graph.entrySet()) {
            
        	System.out.printf("%s -> %s\n", node.getKey(),
                    StringUtils.join(node.getValue(), ", "));
        }
    }
}
