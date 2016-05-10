package advanced_graph;

public class GraphTest {

	public static void main(String[] args) {
		
		Graph graph = new Graph();

        // Nodes
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");
        graph.addNode("G");
        graph.addNode("H");
        graph.addNode("I");
        graph.addNode("J");
        graph.addNode("Z");

        // Connections
        graph.addConnection("A", "B", 14, true);
        graph.addConnection("A", "C", 10, true);
        graph.addConnection("A", "D", 14, true);
        graph.addConnection("A", "E", 21, true);
        graph.addConnection("B", "C", 9, true);
        graph.addConnection("B", "E", 10, true);
        graph.addConnection("B", "F", 14, true);
        graph.addConnection("C", "D", 9, false);
        graph.addConnection("D", "G", 10, false);
        graph.addConnection("E", "H", 11, true);
        graph.addConnection("F", "C", 10, false);
        graph.addConnection("F", "H", 10, true);
        graph.addConnection("F", "I", 9, true);
        graph.addConnection("G", "F", 8, false);
        graph.addConnection("G", "I", 9, true);
        graph.addConnection("H", "J", 9, true);
        graph.addConnection("I", "J", 10, true);

        // Print
        System.out.println(graph.toString());
	}
}
