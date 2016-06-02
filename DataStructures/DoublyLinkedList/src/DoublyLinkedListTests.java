import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class DoublyLinkedListTests {

	@Test
	public void addFirst_EmptyList_ShouldAddElement() {

		// Arrange
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();

        // Act
        list.addFirst(5);

        // Assert
        assertEquals(1, list.size());
        
        ArrayList<Integer> items = new ArrayList<Integer>();
        list.forEachValue(x -> items.add(x));
        assertArrayEquals(items.stream().toArray(Integer[]::new), new Integer[] { 5 });
	}
	
	@Test
	public void addFirst_SeveralElements_ShouldAddElementsCorrectly() {

		// Arrange
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();

        // Act
        list.addLast(10);
        list.addFirst(5);
        list.addFirst(3);

        // Assert
        assertEquals(3, list.size());

        ArrayList<Integer> items = new ArrayList<Integer>();
        list.forEachValue(x -> items.add(x));
        assertArrayEquals(items.stream().toArray(Integer[]::new), new Integer[] { 3, 5, 10 });
	}
	
	@Test
	public void addLast_EmptyList_ShouldAddElement() {

		// Arrange
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();

		// Act
        list.addLast(5);

        // Assert
        assertEquals(1, list.size());

        ArrayList<Integer> items = new ArrayList<Integer>();
        list.forEachValue(x -> items.add(x));
        assertArrayEquals(items.stream().toArray(Integer[]::new), new Integer[] { 5 });
	}
	
	@Test
	public void addLast_SeveralElements_ShouldAddElementsCorrectly() {

		// Arrange
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();

		// Act
        list.addFirst(5);
        list.addLast(10);
        list.addLast(15);

        // Assert
        assertEquals(3, list.size());

        ArrayList<Integer> items = new ArrayList<Integer>();
        list.forEachValue(x -> items.add(x));
        assertArrayEquals(items.stream().toArray(Integer[]::new), new Integer[] { 5, 10, 15 });
	}
	
	@Test
	public void removeFirst_OneElement_ShouldMakeListEmpty() {

		// Arrange
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.addLast(5);
		
		// Act
        Integer element = list.removeFirst();

        // Assert
        assertEquals(Integer.valueOf(5), element);
        assertEquals(0, list.size());

        ArrayList<Integer> items = new ArrayList<Integer>();
        list.forEachValue(x -> items.add(x));
        assertArrayEquals(items.stream().toArray(Integer[]::new), new Integer[] { });
	}
	
	@Test(expected = IllegalStateException.class)
	public void removeFirst_EmptyList_ShouldThrowException() {

		// Arrange
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();

        // Act
        Integer element = list.removeFirst();
	}
	
	@Test
	public void removeFirst_SeveralElements_ShouldRemoveElementsCorrectly() {

		// Arrange
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.addLast(5);
        list.addLast(6);
        list.addLast(7);

        // Act
        Integer element = list.removeFirst();

        // Assert
        assertEquals(Integer.valueOf(5), element);
        assertEquals(2, list.size());

        ArrayList<Integer> items = new ArrayList<Integer>();
        list.forEachValue(x -> items.add(x));
        assertArrayEquals(items.stream().toArray(Integer[]::new), new Integer[] { 6, 7 });
	}
	
	@Test
	public void removeLast_OneElement_ShouldMakeListEmpty() {

		// Arrange
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.addFirst(5);

        // Act
        Integer element = list.removeLast();

        // Assert
        assertEquals(Integer.valueOf(5), element);
        assertEquals(0, list.size());

        ArrayList<Integer> items = new ArrayList<Integer>();
        list.forEachValue(x -> items.add(x));
        assertArrayEquals(items.stream().toArray(Integer[]::new), new Integer[] { });
	}
	
	@Test(expected = IllegalStateException.class)
	public void removeLast_EmptyList_ShouldThrowException() {

		// Arrange
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();

		// Act
        Integer element = list.removeLast();
	}
	
	@Test
	public void removeLast_SeveralElements_ShouldRemoveElementsCorrectly() {

		// Arrange
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.addFirst(10);
        list.addFirst(9);
        list.addFirst(8);

        // Act
        Integer element = list.removeLast();

        // Assert
        assertEquals(Integer.valueOf(10), element);
        assertEquals(2, list.size());

        ArrayList<Integer> items = new ArrayList<Integer>();
        list.forEachValue(x -> items.add(x));
        assertArrayEquals(items.stream().toArray(Integer[]::new), new Integer[] { 8, 9 });
	}
	
	@Test
	public void forEach_EmptyList_ShouldEnumerateElementsCorrectly() {

		// Arrange
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		
		// Act
		ArrayList<Integer> items = new ArrayList<Integer>();
        list.forEachValue(x -> items.add(x));

        assertArrayEquals(items.stream().toArray(Integer[]::new), new Integer[] { });
	}
	
	@Test
	public void forEach_SingleElement_ShouldEnumerateElementsCorrectly() {

		// Arrange
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		list.addLast(5);

		// Act
		ArrayList<Integer> items = new ArrayList<Integer>();
		list.forEachValue(x -> items.add(x));

        assertArrayEquals(items.stream().toArray(Integer[]::new), new Integer[] { 5 });
	}
	
	@Test
	public void forEach_MultipleElements_ShouldEnumerateElementsCorrectly() {

		// Arrange
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.addLast("Five");
        list.addLast("Six");
        list.addLast("Seven");

		// Act
		ArrayList<String> items = new ArrayList<String>();
		list.forEachValue(x -> items.add(x));

        assertArrayEquals(items.stream().toArray(String[]::new), new String[] { "Five", "Six", "Seven" });
	}
	
	@Test
	public void ITerable_foreach_MultipleElements() {

		// Arrange
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.addLast("Five");
        list.addLast("Six");
        list.addLast("Seven");

		// Act
		ArrayList<String> items = new ArrayList<String>();
		for (String value : list) {
			
			items.add(value);
		}

        assertArrayEquals(items.stream().toArray(String[]::new), new String[] { "Five", "Six", "Seven" });
	}
}
