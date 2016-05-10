package bellman_ford;

public class Edge {

	private int start;
	private int end;
	private double distance;
	
	public Edge(int start, int end, double distance) {
        
		this.start = start;
        this.end = end;
        this.distance = distance;
    }

	public int getStart() {
		
		return this.start;
	}

	public int getEnd() {
		
		return this.end;
	}

	public double getDistance() {
		
		return this.distance;
	}

    
}
