package advanced_graph;

public class Edge {

	private Node target;
	private double distance;
	
	public Edge(Node target, double distance) {

		this.target = target;
		this.distance = distance;
    }

	public Node getTarget() {
		return this.target;
	}

	public double getDistance() {
		return this.distance;
	}
}
