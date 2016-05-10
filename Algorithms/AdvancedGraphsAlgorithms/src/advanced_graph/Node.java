package advanced_graph;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private final List<Edge> connections;
	private String name;
    
	public Node(String name) {
    	
        this.name = name;
        this.connections = new ArrayList<Edge>();
    }

    public List<Edge> getConnections() {
		
    	return this.connections;
	}

	public String getName() {
		
		return this.name;
	}

	public void addConnection(Node targetNode, double distance, boolean twoWay) {
		
        if (targetNode == null) {
        	
            throw new NullPointerException("targetNode");
        }

        if (targetNode == this) {
        	
            throw new IllegalArgumentException("Node may not connect to itself.");
        }

        if (distance <= 0) {
        	
            throw new IllegalArgumentException("Distance must be positive.");
        }

        this.connections.add(new Edge(targetNode, distance));
        
        if (twoWay) {
        	
            targetNode.addConnection(this, distance, false);
        }
    }

	@Override
	public String toString() {
		
		return this.getName();
	}
}
