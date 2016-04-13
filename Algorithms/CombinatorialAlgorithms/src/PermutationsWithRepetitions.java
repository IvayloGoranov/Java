import java.util.Arrays;

public class PermutationsWithRepetitions {
	
	public static void main(String[] args) {
        
		Integer[] arr = new Integer[] { 3, 5, 1, 5, 5 };
        Arrays.sort(arr);
        permuteRep(arr, 0, arr.length - 1);
    }

    private static void permuteRep(Integer[] arr, int start, int end) {
        
    	print(arr);

        for (int left = end - 1; left >= start; left--) {
            
        	for (int right = left + 1; right <= end; right++) {
                
        		if (arr[left] != arr[right]) {
                    swap(arr, left, right);
                    permuteRep(arr, left + 1, end);
                }
            }

            // Undo all modifications done by the
            // previous recursive calls and swaps
            Integer firstElement = arr[left];
            for (int i = left; i <= end - 1; i++) {
                arr[i] = arr[i + 1];
            }
            
            arr[end] = firstElement;
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
