package tests;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

import interfaces.Sorter;
import sortable_collection.SortableCollection;
import sorters.*;

public class SortTests {

//	private static Sorter<Integer> testSorter = new HeapSorter<Integer>();
//	private static Sorter<Integer> testSorter = new BubbleSorter<Integer>();
//	private static Sorter<Integer> testSorter = new InsertionSorter<Integer>();
	private static Sorter<Integer> testSorter = new QuickSorter<Integer>();
//	private static Sorter<Integer> testSorter = new SelectionSorter<Integer>();
//	private static Sorter<Integer> testSorter = new MergeSorter<Integer>();

    private static Random random = new Random();
	
	@Test
	public void testSortWithNoElements() {

		// Arrange
		SortableCollection<Integer> emptyCollection = new SortableCollection<Integer>();

        // Act
        emptyCollection.sort(testSorter);

        // Assert
        TestUtilities.assertListEquals("Sorting empty collection should have no effect.", 
        		new Integer[0], emptyCollection);
	}

	@Test
	public void testSortWithOneElement() {

		// Arrange
		SortableCollection<Integer> collection = new SortableCollection<Integer>(1);

        // Act
		collection.sort(testSorter);

        // Assert
		TestUtilities.assertListEquals("Sorting collection with single element should have no effect.", 
        		new Integer[] { 1 }, collection);
	}
	
	@Test
	public void testSortWithTwoElements() {

		// Arrange
		SortableCollection<Integer> collection = new SortableCollection<Integer>(1, -5);

        // Act
		collection.sort(testSorter);

        // Assert
		TestUtilities.assertListEquals("Sorting collection with single element should have no effect.", 
        		new Integer[] { -5, 1 }, collection);
	}
	
	@Test
	public void testSortWithMultipleElements() {

		// Arrange
		SortableCollection<Integer> collection = new SortableCollection<Integer>(3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48);
        Integer[] copy = new Integer[] { 3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48 };

        // Act
     	collection.sort(testSorter);
        Arrays.sort(copy);

        // Assert
        TestUtilities.assertListEquals("Sort method should sort the elements in ascending order.", 
        		copy, collection);
	}
	
	@Test
	public void testSortWithMultipleElementsMultipleTimes() {

		// Arrange
		final int NumberOfAttempts = 10000;
        final int MaxNumberOfElements = 1000;

        for (int i = 0; i < NumberOfAttempts; i++) {
        	
            int numberOfElements = random.nextInt(MaxNumberOfElements + 1);

            Integer[] originalElements = new Integer[numberOfElements];

            for (int j = 0; j < numberOfElements; j++) {
            	
                originalElements[j] = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
            }

            SortableCollection<Integer> collection = new SortableCollection<Integer>(originalElements);

            // Act
            Arrays.sort(originalElements);
            collection.sort(testSorter);

            // Assert
            TestUtilities.assertListEquals("Sort method should sort the elements in ascending order.", 
            		originalElements, collection);
        }
	}
}
