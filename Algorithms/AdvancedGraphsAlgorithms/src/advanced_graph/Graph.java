package advanced_graph;

import java.util.HashMap;
import java.util.Map;

public class Graph {

	private Map<String, Node> nodes;
	
	public Graph() {
		
        this.nodes = new HashMap<String, Node>();
    }

    public Map<String, Node> getNodes() {
		
    	return this.nodes;
	}

	public void addNode(String name) {
		
        Node newNode = new Node(name);
        this.nodes.put(name, newNode);
    }

    public void addConnection(String fromNode, String toNode, int distance, boolean twoWay) {
    	
        this.nodes.get(fromNode).addConnection(this.nodes.get(toNode), distance, twoWay);
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, Node> node : this.nodes.entrySet())
        {
            result.append(node.getKey() + " -> ");

            for (Edge connection : node.getValue().getConnections()) {
            	
                result.append(connection.getTarget() + "-" + connection.getDistance() + " ");
            }

            result.append("\n");
        }

        return result.toString();
    }
}
