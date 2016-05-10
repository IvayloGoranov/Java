package dijkstra_priorityqueue;

public class Node implements Comparable {

	private int id;
	private double dijkstraDistance;
	private Node previous;
	
	public Node(int id) {
		
        this.id = id;
    }

	public int getId() {
		
		return this.id;
	}

	public void setId(int id) {
		
		this.id = id;
	}

	public double getDijkstraDistance() {
		
		return this.dijkstraDistance;
	}

	public void setDijkstraDistance(double dijkstraDistance) {
		
		this.dijkstraDistance = dijkstraDistance;
	}

	public Node getPrevious() {
		
		return this.previous;
	}

	public void setPrevious(Node previous) {
		
		this.previous = previous;
	}

	@Override
    public int compareTo(Object obj)
    {
        if (!(obj instanceof Node))
        {
            return -1;
        }

        return Double.compare(this.dijkstraDistance, ((Node)obj).getDijkstraDistance());
    }
}
