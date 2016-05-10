package kruskal_oop;

public class Edge implements Comparable<Edge> {

	private int startNode;
	private int endNode;
	private int weight;
	
	public Edge(int startNode, int endNode, int weight) {
		
        this.startNode = startNode;
        this.endNode = endNode;
        this.weight = weight;
    }

    public int getStartNode() {
		
    	return this.startNode;
	}

	public void setStartNode(int startNode) {
		
		this.startNode = startNode;
	}

	public int getEndNode() {
		
		return this.endNode;
	}

	public void setEndNode(int endNode) {
		
		this.endNode = endNode;
	}

	public int getWeight() {
		
		return this.weight;
	}

	public void setWeight(int weight) {
		
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge other) {
		
        int weightCompared = Integer.compare(this.getWeight(), other.getWeight());
        return weightCompared;
    }

    @Override
	public String toString() {

    	return String.format("(%d %d) -> %d", this.getStartNode(), this.getEndNode(), this.getWeight());
	}

}
