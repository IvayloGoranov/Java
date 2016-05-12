package dijkstra_priorityqueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.apache.commons.lang3.StringUtils;

public class Dijkstra {

	public static void dijkstraAlgorithm(HashMap<Node, List<Connection>> graph, Node source) {
        PriorityQueue<Node> queue = new PriorityQueue<Node>();

        for (Map.Entry<Node, List<Connection>> node : graph.entrySet()) {
        	
            node.getKey().setDijkstraDistance(Double.POSITIVE_INFINITY);
        }

        source.setDijkstraDistance(0.0d);
        queue.offer(source);

        while (queue.size() != 0) {
        	
            Node currentNode = queue.poll();

            if (Double.POSITIVE_INFINITY == currentNode.getDijkstraDistance()) {
                
            	break;
            }

            for (Connection neighbor : graph.get(currentNode))
            {
                double minDistance = currentNode.getDijkstraDistance() + neighbor.getDistance();
                if (minDistance < neighbor.getNode().getDijkstraDistance())
                {
                    neighbor.getNode().setDijkstraDistance(minDistance);
                    neighbor.getNode().setPrevious(currentNode);
                    queue.offer(neighbor.getNode());
                }
            }
        }
    }

	public static void main(String[] args) {
		
		Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);

        Node[] nodes = new Node[] { node1, node2, node3, node4, node5, node6, node7, node8, node9, node10 };

        HashMap<Node, List<Connection>> graph = new HashMap<Node, List<Connection>>()
        {{
            put(node1, Arrays.asList(new Connection(node2, 23), new Connection(node8, 8)));
            put(node2, Arrays.asList(new Connection(node1, 23), new Connection(node4, 3), new Connection(node7, 34)));
            put(node3, Arrays.asList(new Connection(node4, 6), new Connection(node8, 25), new Connection(node10, 7)));
            put(node4, Arrays.asList(new Connection(node2, 3), new Connection(node3, 6)));
            put(node5, Arrays.asList(new Connection(node6, 10)));
            put(node6, Arrays.asList(new Connection(node5, 10)));
            put(node7, Arrays.asList(new Connection(node2, 34)));
            put(node8, Arrays.asList(new Connection(node1, 8), new Connection(node3, 25), new Connection(node10, 30)));
            put(node9, Arrays.asList());
            put(node10, Arrays.asList(new Connection(node3, 7), new Connection(node8, 30)));
        }};

        Node source = node1;

        dijkstraAlgorithm(graph, source);

        for (int i = 0; i < nodes.length; i++) {
        	
            System.out.printf("Distance from %d to %d: %f; ", source.getId(), nodes[i].getId(), nodes[i].getDijkstraDistance());
            System.out.printf("Shortest Path: %s\n", buildPath(graph, source, nodes[i]));
        }
	}
	
    private static String buildPath(HashMap<Node, List<Connection>> graph, Node source, Node destination) {
    	
        ArrayList<Integer> shortestPath = new ArrayList<Integer>();
        Node currentNode = destination;
        if (currentNode.getDijkstraDistance() == Double.POSITIVE_INFINITY || currentNode.getId() == source.getId()) {
        	
            return "No such path exists.";
        }

        while (currentNode != null) {
        	
            shortestPath.add(currentNode.getId());
            currentNode = currentNode.getPrevious();
        }

        Collections.reverse(shortestPath);

        return StringUtils.join(shortestPath, ", ");
    }
}
