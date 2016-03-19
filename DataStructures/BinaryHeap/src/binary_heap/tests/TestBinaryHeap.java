package binary_heap.tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import binary_heap.BinaryHeap;

public class TestBinaryHeap {

	@Test
	public void buildHeap_ExtractAllElements_ShouldReturnElementsSorted() {

		// Arrange
        Integer[] arr = new Integer[] { 3, 4, -1, 15, 2, 77, -3, 4, 12 };
        List<Integer> collection = Arrays.asList(arr);

        // Act
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(collection);
        ArrayList<Integer> elements = new ArrayList<Integer>();
        while (heap.size() > 0)
        {
            int maxElement = heap.extractMax();
            elements.add(maxElement);
        }

        // Assert
        Integer[] expected = new Integer[] { 77, 15, 12, 4, 4, 3, 2, -1, -3 };
        assertArrayEquals(expected, elements.toArray());
	}
	
	@Test
	public void emptyHeap_InsertElements_ExtractAllElements_ShouldReturnElementsSorted() {

		// Arrange
        Integer[] arr = new Integer[] { 3, 4, -1, 15, 2, 77, -3, 4, 12 };

        // Act
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>();

        for(Integer num: arr)
        {
            heap.insert(num);
        }
        
        ArrayList<Integer> elements = new ArrayList<Integer>();
        while (heap.size() > 0)
        {
            int maxElement = heap.extractMax();
            elements.add(maxElement);
        }

        // Assert
        Integer[] expected = new Integer[] { 77, 15, 12, 4, 4, 3, 2, -1, -3 };
        assertArrayEquals(expected, elements.toArray());
	}

}
