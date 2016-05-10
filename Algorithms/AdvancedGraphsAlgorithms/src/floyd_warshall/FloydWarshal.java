package floyd_warshall;

public class FloydWarshal {

	private static final double Inf = Double.POSITIVE_INFINITY;

    public static void main(String[] args) {
	
    	double[][] graph = new double[][]
    	        {
    	            //0    1    2    3
    	            { 0,   4,  -2,   Inf },
    	            { Inf, 0,   3,   -1 },
    	            { Inf, Inf, 0,   2 },
    	            { Inf, Inf, Inf, 0 }
    	        };

    	        double[][] dist = new double[][]
    	    	        {
    	    	            //0    1    2    3
    	    	            { 0,   4,  -2,   Inf },
    	    	            { Inf, 0,   3,   -1 },
    	    	            { Inf, Inf, 0,   2 },
    	    	            { Inf, Inf, Inf, 0 }
    	    	        };        
    	        
    	    	int v = graph[0].length;
    	        
    	    	for (int k = 0; k < v; k++) {
    	    		
    	            for (int i = 0; i < v; i++) {
    	            	
    	                for (int j = 0; j < v; j++) {
    	                	
    	                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
    	                    	
    	                        dist[i][j] = dist[i][k] + dist[k][j];
    	                    }
    	                }
    	            }
    	        }

    	        System.out.printf("Shortest path between (0..3): %f\n", dist[0][3]);
	}
	
}
