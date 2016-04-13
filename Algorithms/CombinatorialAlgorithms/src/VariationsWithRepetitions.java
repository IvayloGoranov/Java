
public class VariationsWithRepetitions {

	private static final int k = 3;
    private static final int n = 10;
    
    private static String[] objects = 
    {
		"banana", "apple", "orange", "strawberry", "raspberry",
		"apricot", "cherry", "lemon", "grapes", "melon"
	};

    private static int[] arr = new int[k];

    public static void main(String[] args) {
        generateVariationsWithRepetitions(0);
    }

    private static void generateVariationsWithRepetitions(int index) {
        
    	if (index >= k) {
            PrintVariations();
        } else {
            for (int i = 0; i < n; i++) {
                arr[index] = i;
                generateVariationsWithRepetitions(index + 1);
            }
        }
    }

    private static void PrintVariations() {

    	for (int index : arr) {
			
    		System.out.print(objects[index] + " ");
		}
		        
		System.out.println();
    }
}
