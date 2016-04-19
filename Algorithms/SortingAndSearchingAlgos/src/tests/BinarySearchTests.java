package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

import sortable_collection.SortableCollection;

public class BinarySearchTests {
	
	@Test
	public void testWithEmptyCollectionShouldReturnMissingElement() {

		// Arrange
		SortableCollection<Integer> emptyCollection = new SortableCollection<Integer>();

        // Act
        int actualResult = emptyCollection.binarySearch(0);
        int expectedResult = -1;

        // Assert
        assertEquals("No elements are present in an empty collection; method should return -1.", 
        		actualResult, expectedResult);
	}

	@Test
	public void testWithMissingElement() {

		// Arrange
		SortableCollection<Integer> collection = new SortableCollection<Integer>(-1, 1, 5, 12, 50);

        // Act
		int actualResult = collection.binarySearch(0);
		int expectedResult = -1;

        // Assert
        assertEquals("Missing element should return -1.", 
        		actualResult, expectedResult);
	}
	
	@Test
	public void testWithItemAtMidpoint() {

		// Arrange
		SortableCollection<Integer> collection = new SortableCollection<Integer>(1, 2, 3, 4, 5);

        // Act
		int actualResult = collection.binarySearch(3);
		int expectedResult = 2;

		// Assert
        assertEquals("Did not return correct index.", 
        		actualResult, expectedResult);
	}
	
	@Test
	public void testWithItemToTheLeftOfMidpoint() {

		// Arrange
		SortableCollection<Integer> collection = new SortableCollection<Integer>(1, 2, 3, 4, 5);

        // Act
		int actualResult = collection.binarySearch(2);
		int expectedResult = 1;

		// Assert
        assertEquals("Did not return correct index.", 
        		actualResult, expectedResult);
	}
	
	@Test
	public void testWithItemToTheRightOfMidpoint() {

		// Arrange
		SortableCollection<Integer> collection = new SortableCollection<Integer>(1, 2, 3, 4, 5);

        // Act
		int actualResult = collection.binarySearch(4);
		int expectedResult = 3;

		// Assert
        assertEquals("Did not return correct index.", 
        		actualResult, expectedResult);
	}
	
	@Test
	public void testWithMultipleMissingKeysSmallerThanMinimum() {

		final int NumberOfChecks = 10000;
        final int NumberOfElements = 1000;

        Integer[] elements = new Integer[NumberOfElements];

        for (int i = 0; i < NumberOfElements; i++){
        	
            elements[i] = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE / 2, Integer.MAX_VALUE / 2);
        }

        Arrays.sort(elements);

        SortableCollection<Integer> collection = new SortableCollection<Integer>(elements);

        for (int i = 0; i < NumberOfChecks; i++){
        	
            Integer item = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, collection.getItems().get(0));

            int result = collection.binarySearch(item);

            assertEquals(-1, result);
        }
	}

	@Test
	public void testWithMultipleMissingKeysLargerThanMaximum() {

		final int NumberOfChecks = 10000;
        final int NumberOfElements = 1000;

        Integer[] elements = new Integer[NumberOfElements];

        for (int i = 0; i < NumberOfElements; i++){
        	
            elements[i] = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE / 2, Integer.MAX_VALUE / 2);
        }

        Arrays.sort(elements);

        SortableCollection<Integer> collection = new SortableCollection<Integer>(elements);

        for (int i = 0; i < NumberOfChecks; i++){
        	
            Integer item = ThreadLocalRandom.current().
            		nextInt(collection.getItems().get(collection.size() - 1), Integer.MAX_VALUE);

            int result = collection.binarySearch(item);

            assertEquals(-1, result);
        }
	}
	
	@Test
	public void testWithMultipleKeys() {

		final int NumberOfElements = 10000;

		Integer[] elements = new Integer[NumberOfElements];

        for (int i = 0; i < NumberOfElements; i++) {
            
        	elements[i] = ThreadLocalRandom.current().nextInt(-100, 100);
        }

        Arrays.sort(elements);

        SortableCollection<Integer> collection = new SortableCollection<Integer>(elements);

        for (Integer element : elements) {
        	
            int expected = Arrays.binarySearch(elements, element);
            int result = collection.binarySearch(element);

            assertEquals(expected, result);
        }
	}
	
	@Test
	public void testWithRepeatingItemShouldReturnFirstDiscoveredIndex() {

		SortableCollection<Integer> collection = new SortableCollection<Integer>(0, 3, 3, 3, 3, 7, 7, 7, 7, 7, 7);
        int result = collection.binarySearch(3);

        assertEquals(2, result);
	}
}
