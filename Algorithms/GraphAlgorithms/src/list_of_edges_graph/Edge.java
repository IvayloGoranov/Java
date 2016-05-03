package list_of_edges_graph;

public class Edge {
	
	private int parent;
	private int child;
	
	public Edge(int parent, int child) {

		this.setParent(parent);
		this.setChild(child);
	}

	public int getParent() {
		
		return this.parent;
	}

	public void setParent(int parent) {
		
		this.parent = parent;
	}

	public int getChild() {
		
		return this.child;
	}

	public void setChild(int child) {
		
		this.child = child;
	}
}
