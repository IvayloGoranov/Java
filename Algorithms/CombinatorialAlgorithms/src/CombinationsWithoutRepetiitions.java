
public class CombinationsWithoutRepetiitions {
	
	private static final int k = 3;
    private static final int n = 5;
    
    private static String[] objects = 
	{
		"banana", "apple", "orange", "strawberry", "raspberry"
	};
    
    private static int[] arr = new int[k];

    public static void main(String[] args) {
       
    	generateCombinationsNoRepetitions(0, 0);
    }

    private static void generateCombinationsNoRepetitions(int index, int start) {
        
    	if (index >= k) {
            
    		printCombinations();
        } else {
            
        	for (int i = start; i < n; i++) {
                
        		arr[index] = i;
                generateCombinationsNoRepetitions(index + 1, i + 1);
            }
        }
    }

    private static void printCombinations() {
        
    	for (int index : arr) {
			
    		System.out.print(objects[index] + " ");
		}
		        
		System.out.println();
    }
}
