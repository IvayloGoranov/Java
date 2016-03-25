package arraystack.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import arraystack.ArrayStack;

public class TestArrayStack {

	@Test
	public void push_EmptyStack_ShouldAddElement() {

		// Arrange
        ArrayStack<Integer> stack = new ArrayStack<Integer>();

        // Act
        stack.push(5);

        // Assert
        assertEquals(1, stack.size());
	}
	
	@Test
	public void pushPop_ShouldWorkCorrectly() {

		// Arrange
		ArrayStack<String> stack = new ArrayStack<String>();
        String element = "some value";

        // Act
        stack.push(element);
        String elementFromStack = stack.pop();

        // Assert
        assertEquals(0, stack.size());
        assertEquals(element, elementFromStack);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void pop_EmptyStack_ThrowsException() {

		// Arrange
		ArrayStack<String> stack = new ArrayStack<String>();

        // Act
        stack.pop();

        // Assert: expect an exception
	}
	
	@Test
	public void pushPop1000Elements_ShouldWorkCorrectly() {

		// Arrange
		ArrayStack<Integer> stack = new ArrayStack<Integer>();
        int numberOfElements = 1000;

        // Act
        for (int i = 1; i <= numberOfElements; i++)
        {
            stack.push(i);
        }

        // Assert
        for (Integer i = numberOfElements, j = 0; i >= 1; i--, j++)
        {
        	assertEquals(numberOfElements - j, stack.size());
            Integer element = stack.pop();
            assertEquals(i, element);
            assertEquals(numberOfElements - j - 1, stack.size());
        }
	}
      
	@Test
	public void pushPopManyChunks_ShouldWorkCorrectly() {

		// Arrange
		ArrayStack<Integer> stack = new ArrayStack<Integer>();
        int chunks = 100;

        // Act & Assert in a loop
        int value = 1;
        for (int i = 0; i < chunks; i++)
        {
        	assertEquals(0, stack.size());
            int chunkSize = i + 1;
            for (int counter = 0; counter < chunkSize; counter++)
            {
            	assertEquals(value - 1, stack.size());
                stack.push(value);
                assertEquals(value, stack.size());
                value++;
            }

            for (int counter = 0; counter < chunkSize; counter++)
            {
                value--;
                assertEquals(value, stack.size());
                stack.pop();
                assertEquals(value - 1, stack.size());
            }

            assertEquals(0, stack.size());
        }
	}
	
	@Test
	public void pushPeek_ShouldWorkCorrectly() {

		// Arrange
		ArrayStack<String> stack = new ArrayStack<String>();
        String element = "some value";

        // Act
        stack.push(element);
        String elementFromStack = stack.peek();

        // Assert
        assertEquals(1, stack.size());
        assertEquals(element, elementFromStack);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void peek_EmptyStack_ThrowsException() {

		// Arrange
		ArrayStack<String> stack = new ArrayStack<String>();

        // Act
        stack.peek();

        // Assert: expect an exception
	}
}
