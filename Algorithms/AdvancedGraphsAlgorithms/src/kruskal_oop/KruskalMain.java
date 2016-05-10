package kruskal_oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalMain {

	public static void main(String[] args) {
		
		int numberOfVertices = 9;
        List<Edge> graphEdges = new ArrayList<Edge>();
        graphEdges.add(new Edge(0, 3, 9));
        graphEdges.add(new Edge(0, 5, 4));
        graphEdges.add(new Edge(0, 8, 5));
        graphEdges.add(new Edge(1, 4, 8));
        graphEdges.add(new Edge(1, 7, 7));
        graphEdges.add(new Edge(2, 6, 12));
        graphEdges.add(new Edge(3, 5, 2));
        graphEdges.add(new Edge(3, 6, 8));
        graphEdges.add(new Edge(3, 8, 20));
        graphEdges.add(new Edge(4, 7, 10));
        graphEdges.add(new Edge(6, 8, 7));

        Collections.sort(graphEdges);
        List<Edge> minimumSpanningForest = KruskalAlgorithm.Kruskal(numberOfVertices, graphEdges);

        System.out.println("Minimum spanning forest weight: " +
                        minimumSpanningForest.stream().mapToInt(Edge::getWeight).sum());

        for (Edge edge : minimumSpanningForest) {
        	
            System.out.println(edge);
        }
	}
}
