package list_of_edges_graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class ListOfEdgesGraph {

	private static ArrayList<Edge> graph = new ArrayList<Edge>() 
	{{
        add(new Edge(0, 6));
        add(new Edge(1, 2));
        add(new Edge(1, 3));
        add(new Edge(1, 4));
        add(new Edge(1, 5));
        add(new Edge(1, 6));
        add(new Edge(2, 1));
        add(new Edge(2, 4));
        add(new Edge(2, 5));
        add(new Edge(3, 0));
        add(new Edge(3, 1));
        add(new Edge(3, 5));
        add(new Edge(4, 1));
        add(new Edge(4, 2));
        add(new Edge(4, 6));
        add(new Edge(5, 1));
        add(new Edge(5, 2));
        add(new Edge(5, 3));
        add(new Edge(6, 0));
        add(new Edge(6, 1));
        add(new Edge(6, 4));
    }};
    
    private static String[] nodeNames = new String[] { "Ruse", "Sofia", "Pleven", "Varna", "Bourgas", "Stara Zagora", "Plovdiv" };
    
    public static void main(String[] args) {
		
    	printNodesWithChildren(graph);

        System.out.println();

        printNodeNamesWithChildrenNames(graph, nodeNames);
	}
    
    private static void printNodesWithChildren(ArrayList<Edge> graph) {
    	
        int maxEdgeValue = graph.stream().max((value1, value2) -> Integer.compare(value1.getParent(), value2.getParent())).
        		get().getParent();
    	for (int i = 0; i < maxEdgeValue; i++) {
        	
        	int node = i;
    		List<Integer> childNodeNames = graph.stream().filter(e -> e.getParent() == node)
                .map(edge -> edge.getChild()).collect(Collectors.toList());
            System.out.printf("%d --> %s\n", node, StringUtils.join(childNodeNames, ", "));
        }
    }

    static void printNodeNamesWithChildrenNames(List<Edge> graph, String[] nodeNames)
    {
        for (int i = 0; i < nodeNames.length; i++)
        {
            int node = i;
        	List<String> childNodeNames = graph.stream().filter(e -> e.getParent() == node)
                .map(edge -> nodeNames[edge.getChild()]).collect(Collectors.toList());
        	System.out.printf("%s --> %s\n", nodeNames[node], StringUtils.join(childNodeNames, ", "));
        }
    }
}
