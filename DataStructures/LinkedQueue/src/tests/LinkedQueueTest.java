package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import linkedQueue.LinkedQueue;

public class LinkedQueueTest {

	@Test
	public void offer_EmptyQueue_ShouldAddElement() {

		// Arrange
        LinkedQueue<Integer> queue = new LinkedQueue<Integer>();

        // Act
        queue.offer(5);

        // Assert
        assertEquals(1, queue.size());
	}

	
	@Test
	public void offerPoll_ShouldWorkCorrectly() {

		// Arrange
        LinkedQueue<String> queue = new LinkedQueue<String>();
        String element = "some value";

        // Act
        queue.offer(element);
        String elementFromQueue = queue.poll();

        // Assert
        assertEquals(0, queue.size());
        assertEquals(element, elementFromQueue);
	}
	
	@Test(expected = IllegalStateException.class)
	public void poll_EmptyQueue_ThrowsExceptiony() {

		// Arrange
        LinkedQueue<Integer> queue = new LinkedQueue<Integer>();

        // Act
        queue.poll();

        // Assert: expect and exception
	}
	
	@Test
	public void offerPoll1000Elements_ShouldWorkCorrectly() {

		// Arrange
        LinkedQueue<Integer> queue = new LinkedQueue<Integer>();
        int numberOfElements = 1000;

        // Act
        for (int i = 0; i < numberOfElements; i++) {
        	
            queue.offer(i);
        }

        // Assert
        for (int i = 0; i < numberOfElements; i++) {
        	
        	assertEquals(numberOfElements - i, queue.size());
            Integer element = queue.poll();
            assertEquals(Integer.valueOf(i), element);
            assertEquals(numberOfElements - i - 1, queue.size());
        }
	}
	
	@Test
	public void iterable_MultipleElements() {

		// Arrange
        LinkedQueue<String> queue = new LinkedQueue<String>();
        queue.offer("Five");
        queue.offer("Six");
        queue.offer("Seven");

        // Act
        List<String> items = new ArrayList<String>();
        for (String element : queue) {
        	
            items.add(element);
        }

        // Assert
        assertArrayEquals(items.toArray(), new String[] { "Five", "Six", "Seven" });
	}
	
	@Test
	public void offerPeek_ShouldWorkCorrectly() {

		// Arrange
        LinkedQueue<String> queue = new LinkedQueue<String>();
        String element = "some value";

        // Act
        queue.offer(element);
        String elementFromQueue = queue.peek();

        // Assert
        assertEquals(1, queue.size());
        assertEquals(element, elementFromQueue);
	}
	
	@Test(expected = IllegalStateException.class)
	public void peek_EmptyQueue_ThrowsException() {

		// Arrange
        LinkedQueue<Integer> queue = new LinkedQueue<Integer>();

        // Act
        queue.peek();

        // Assert: expect and exception
	}
}
