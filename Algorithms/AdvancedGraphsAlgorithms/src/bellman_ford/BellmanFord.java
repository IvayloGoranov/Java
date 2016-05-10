package bellman_ford;

import java.util.ArrayList;

public class BellmanFord {

	public static void main(String[] args) {
		
		int verticeCount = 4;

        ArrayList<Edge> edges = new ArrayList<Edge>(); 
        edges.add(new Edge(3, 1, 15));
        edges.add(new Edge(3, 2, 2));
        edges.add(new Edge(2, 1, 12));
        edges.add(new Edge(1, 0, -5));

        int startNode = 3;
        int destination = 0;

        double distance = findShortestPathBellmanFord(startNode, destination, verticeCount, edges);
        System.out.println(distance);
	}
	
	private static int findShortestPathBellmanFord(int startNode, int destination, int verticeCount, ArrayList<Edge> edges) {
		
        double[] distance = new double[verticeCount];
        int[] previous = new int[verticeCount];
        for (int i = 0; i < distance.length; i++) {
        	
            distance[i] = Double.POSITIVE_INFINITY;
            previous[i] = -1;
        }

        distance[startNode] = 0;

        for (int i = 0; i < verticeCount - 1; i++) {
        	
            for (Edge edge : edges) {
            	
                if (distance[edge.getStart()] + edge.getDistance() < distance[edge.getEnd()]) {
                	
                    distance[edge.getEnd()] = distance[edge.getStart()] + edge.getDistance();
                    previous[edge.getEnd()] = edge.getStart();
                }
            }
        }

        for (Edge edge : edges) {
        	
            if (distance[edge.getStart()] + edge.getDistance() < distance[edge.getEnd()]) {
            	
                throw new IllegalArgumentException(
                    String.format("A negative weight cycle exists at edge (%d, %d)\n",
                        edge.getStart(), edge.getEnd()
                        ));
            }
        }

        return (int)distance[destination];
    }
}
