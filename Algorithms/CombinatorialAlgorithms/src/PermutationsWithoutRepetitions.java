
public class PermutationsWithoutRepetitions {
	
public static void main(String[] args) {
        
		String[] arr = { "apple", "banana", "orange" };
        generatePermutations(arr, 0);
    }

    private static <T> void generatePermutations(T[] arr, int k) {
        
    	if (k >= arr.length) {
            
    		print(arr);
        } else {
            generatePermutations(arr, k + 1);
            for (int i = k + 1; i < arr.length; i++)
            {
                swap(arr, k, i);
                generatePermutations(arr, k + 1);
                swap(arr, k, i);
            }
        }
    }

    private static <T> void print(T[] arr) {
        
    	for (T element : arr) {
			
        	System.out.print(element + " ");
		}
        
        System.out.println();
    }

    private static <T> void swap(T[] arr, int firstIndex, int secondIndex) {
        
    	T oldFirst = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = oldFirst;
    }
}
