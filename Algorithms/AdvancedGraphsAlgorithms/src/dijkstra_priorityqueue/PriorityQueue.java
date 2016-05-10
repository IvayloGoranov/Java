//priority queue has bugs.

package dijkstra_priorityqueue;

import java.util.ArrayList;

public class PriorityQueue<T extends Comparable> {

	private ArrayList<T> heap;

    public PriorityQueue() {
    	
        this.heap = new ArrayList<>();
    }

    public int size() {
    	
    	return this.heap.size();
    }

    public void offer(T element) {
    	
        this.heap.add(element);

        int childIndex = this.heap.size() - 1;
        int parentIndex = childIndex / 2;

        while (parentIndex >= 1 && this.heap.get(childIndex).compareTo(this.heap.get(parentIndex)) < 0) {
        	
            T swapValue = this.heap.get(parentIndex);
            this.heap.set(parentIndex, this.heap.get(childIndex));
            this.heap.set(childIndex, swapValue);

            childIndex = parentIndex;
            parentIndex = childIndex / 2;
        }
    }

    public T poll() {
    	
        T result = this.heap.get(0);

        this.heap.set(0, this.heap.get(this.heap.size() - 1));

        int rootIndex = 0;

        while (true) {
        	
            int leftChildIndex = rootIndex * 2 + 1;
            int rightChildIndex = (rootIndex * 2) + 2;

            if (leftChildIndex > this.heap.size()) {
            	
                break;
            }

            int minChild;
            if (rightChildIndex > this.heap.size()) {
            	
                minChild = leftChildIndex;
            } else {
            	
                if (this.heap.get(leftChildIndex).compareTo(this.heap.get(rightChildIndex)) < 0) {
                	
                    minChild = leftChildIndex;
                } else {
                	
                    minChild = rightChildIndex;
                }
            }

            if (this.heap.get(minChild).compareTo(this.heap.get(rootIndex)) < 0)
            {
                T swapValue = this.heap.get(rootIndex);
                this.heap.set(rootIndex, this.heap.get(minChild));
                this.heap.set(minChild, swapValue);

                rootIndex = minChild;
            } else {
            	
                break;
            }
        }

        return result;
    }

    public T Peek() {
    	
        return this.heap.get(0);
    }
}
