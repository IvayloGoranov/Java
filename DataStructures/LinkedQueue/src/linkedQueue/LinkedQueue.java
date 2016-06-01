package linkedQueue;

import java.util.Iterator;

public class LinkedQueue<T> implements Iterable<T> {

	private Node<T> firstNode;
    private Node<T> lastNode;
    private int count;
    
    public LinkedQueue() {
    	
        this.count = 0;
        this.firstNode = null;
        this.lastNode = null;
    }


    public int size() {
		
    	return this.count;
	}

	public void offer(T element) {
		
        if (this.size() == 0) {
        	
            this.firstNode = new Node<T>(element);
            this.lastNode = this.firstNode;
        }
        else {
        	
            Node<T> newNode = new Node<T>(element);
            this.lastNode.setNextNode(newNode);
            this.lastNode = newNode;
        }

        this.count++;
    }

    public T poll() {
    	
        if (this.size() == 0) {
        	
            throw new IllegalStateException("The queue is empty!");
        }

        T result = this.firstNode.getValue();
        this.firstNode = this.firstNode.getNextNode();
        this.count--;

        return result;
    }

    public T peek() {
    	
    	if (this.size() == 0) {
        	
            throw new IllegalStateException("The queue is empty!");
        }

    	T result = this.firstNode.getValue();

        return result;
    }

    public void clear() {
    	
    	while (this.count != 0) {
        	
            this.poll();
        }
    }

	@Override
	public Iterator<T> iterator() {
		
		Iterator<T> queueIterator = new Iterator<T>() {

            private Node<T> currentNode;
            private int currentIndex;

            @Override
            public boolean hasNext() {
                
            	return this.currentIndex < count;
            }

            @Override
            public T next() {
                
            	if (this.currentNode == null) {
					
            		this.currentNode = firstNode;
            		this.currentIndex = 1;
            		
				}else {
					
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
        
        return queueIterator;
	}
}
