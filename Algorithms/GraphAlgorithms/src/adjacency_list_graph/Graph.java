package adjacency_list_graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {

	private ArrayList<List<Integer>> childNodes;
    private String[] nodeNames;

    public Graph(ArrayList<List<Integer>> childNodes, String[] nodeNames) {
    	
        this.setChildNodes(childNodes);
        this.setNodeNames(nodeNames);
    }

	public ArrayList<List<Integer>> getChildNodes() {
		
		return this.childNodes;
	}

	public void setChildNodes(ArrayList<List<Integer>> childNodes) {
		
		this.childNodes = childNodes;
	}

	public String[] getNodeNames() {
		
		return this.nodeNames;
	}

	public void setNodeNames(String[] nodeNames) {
		
		this.nodeNames = nodeNames;
	}
    
    
}
