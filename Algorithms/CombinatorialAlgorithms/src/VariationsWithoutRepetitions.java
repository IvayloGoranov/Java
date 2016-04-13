
public class VariationsWithoutRepetitions {
	
	private static final int k = 3;
    private static final int n = 4;

    private static int[] arr = new int[k];
    private static int[] free = { 1, 2, 3, 4 };

    public static void main(String[] args) {
        
    	generateVariationsNoRepetitions(0);
    }

    public static void generateVariationsNoRepetitions(int index) {
        
    	if (index >= k) {
            
    		printVariations();
        } else {
            for (int i = index; i < n; i++) {
                arr[index] = free[i];
                swap(free, i, index);
                generateVariationsNoRepetitions(index + 1);
                swap(free, i, index);
            }
        }
    }

    private static void swap(int[] free, int v1, int v2) {
        
    	int old = free[v1];
        free[v1] = free[v2];
        free[v2] = old;
    }

    private static void printVariations() {
    	
    	for (int element : arr) {
			
        	System.out.print(element + " ");
		}
        
        System.out.println();
    }
}
