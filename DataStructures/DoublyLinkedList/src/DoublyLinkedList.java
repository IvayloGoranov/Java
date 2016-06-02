import java.util.Iterator;
import java.util.function.Consumer;

public class DoublyLinkedList<T> implements Iterable<T> {

	private ListNode<T> head;
    private ListNode<T> tail;
    private int size;
    
    
    public DoublyLinkedList() {
    }
    
    public int size() {
    	
    	return this.size;
    }

    public void addFirst(T element) {
    	
        if (this.size == 0) {
        	
            this.head = new ListNode<T>(element);
            this.tail = this.head;
        } else {
        	
            ListNode<T> newHead = new ListNode<T>(element);
            newHead.setNextNode(this.head);
            this.head.setPreviousNode(newHead);
            this.head = newHead;
        }

        this.size++;
    }

    public void addLast(T element) {
    	
        if (this.size == 0) {
        	
            this.tail = new ListNode<T>(element);
            this.head = this.tail;
        } else {
        	
            ListNode<T> newTail = new ListNode<T>(element);
            newTail.setPreviousNode(this.tail);
            this.tail.setNextNode(newTail);
            this.tail = newTail;
        }

        this.size++;
    }

    public T removeFirst() {
    	
        if (this.size == 0) {
        	
            throw new IllegalStateException("Cannot remove element from an emty list!");
        }

        T firstElement = this.head.getValue();
        this.head = this.head.getNextNode();
        if (this.head != null) {
        	
            this.head.setPreviousNode(null);
        } else {
        	
            this.tail = null;
        }

        this.size--;
        
        return firstElement;
    }

    public T removeLast() {
    	
    	if (this.size == 0) {
        	
            throw new IllegalStateException("Cannot remove element from an empty list!");
        }

        T lastElement = this.tail.getValue();
        this.tail = this.tail.getPreviousNode();
        if (this.tail != null) {
        	
            this.tail.setNextNode(null);
        } else {
        	
            this.head = null;
        }

        this.size--;
        
        return lastElement;
    }

    public void forEachValue(Consumer<T> action) {
    	
        ListNode<T> currentNode = this.head;
        while (currentNode != null) {
        	
            action.accept(currentNode.getValue());
            currentNode = currentNode.getNextNode();
        }
    }

    @Override
	public Iterator<T> iterator() {

    	Iterator<T> listIterator = new Iterator<T>() {

            private ListNode<T> currentNode;
            private int currentIndex;

            @Override
            public boolean hasNext() {
                
            	return this.currentIndex < size;
            }

            @Override
            public T next() {
                
            	if (this.currentNode == null) {
					
            		this.currentNode = head;
            		this.currentIndex = 1;
            		
				} else {
					
					this.currentNode = this.currentNode.getNextNode();
					this.currentIndex++;
				}
                
            	return this.currentNode.getValue();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        
        return listIterator;
	}
}
