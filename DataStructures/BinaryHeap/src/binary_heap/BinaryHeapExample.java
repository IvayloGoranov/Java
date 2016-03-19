package binary_heap;

import java.util.Arrays;
import java.util.List;

public class BinaryHeapExample {

	public static void main(String[] args) {

		System.out.println("Created an empty heap.");
		BinaryHeap<Integer> heap = new BinaryHeap<Integer>();
        heap.insert(5);
        heap.insert(8);
        heap.insert(1);
        heap.insert(3);
        heap.insert(12);
        heap.insert(-4);

        System.out.println();
        System.out.println("Heap elements (max to min):");
        while (heap.size() > 0)
        {
            int max = heap.extractMax();
            System.out.println(max);
        }

        Integer[] arr = new Integer[] { 3, 4, -1, 15, 2, 77, -3, 4, 12 };
        List<Integer> collection = Arrays.asList(arr);
        BinaryHeap<Integer> heapFromArr = new BinaryHeap<Integer>(collection);
        System.out.println("Created a heap from array.");

        System.out.println();
        System.out.println("Heap elements (max to min):");
        while (heapFromArr.size() > 0)
        {
            int max = heapFromArr.extractMax();
            System.out.println(max);
        }
	}

}
