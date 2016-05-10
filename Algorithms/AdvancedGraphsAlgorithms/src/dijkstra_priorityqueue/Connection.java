package dijkstra_priorityqueue;

public class Connection {

	private Node node;
	private double distance;
	
	public Connection(Node node, double distance) {
		
        this.node = node;
        this.distance = distance;
    }

	public Node getNode() {
		
		return this.node;
	}

	public void setNode(Node node) {
		
		this.node = node;
	}

	public double getDistance() {
		
		return this.distance;
	}

	public void setDistance(double distance) {
		
		this.distance = distance;
	}

    
}
