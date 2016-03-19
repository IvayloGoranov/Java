package binary_heap;

import java.util.ArrayList;
import java.util.Collection;

public class BinaryHeap<T extends Comparable<T>> {
	
	private ArrayList<T> heap;
	
	public BinaryHeap() {

		this.heap = new ArrayList<T>();
	}
	
	public BinaryHeap(Collection<T> elements) {

		this.heap = new ArrayList<T>(elements);
		for (int i = this.heap.size() / 2; i >= 0; i--)
        {
            this.heapifyDown(i);
        }
	}
	
	public int size() {
		
		return this.heap.size();
	}

	public T extractMax() {
		
		T maxElement = this.heap.get(0);
        this.heap.set(0, this.heap.get(this.heap.size() - 1));
        this.heap.remove(this.heap.size() - 1);
        if (this.heap.size() > 0)
        {
            this.heapifyDown(0);
        }

        return maxElement;
	}
	
	public T peekMax() {
		
		T maxElement = this.heap.get(0);

		return maxElement;
	}
	
	public void insert(T node) {
		
		this.heap.add(node);
        this.heapifyUp(this.heap.size() - 1);
	}
	
	private void heapifyUp(int childIndex) {

		int parentIndex = (childIndex - 1) / 2;
        while (childIndex > 0 && this.heap.get(childIndex).compareTo(this.heap.get(parentIndex)) > 0)
        {
            swap(childIndex, parentIndex);
            childIndex = parentIndex;
            parentIndex = (childIndex - 1) / 2;
        }
	}

	private void heapifyDown(int parentIndex) {

		int leftChildIndex = 2 * parentIndex + 1;
        int rightChildIndex = 2 * parentIndex + 2;
        int largestElementIndex = parentIndex;
        if (leftChildIndex < this.heap.size()
            && this.heap.get(leftChildIndex).compareTo(this.heap.get(largestElementIndex)) > 0)
        {
            largestElementIndex = leftChildIndex;
        }

        if (rightChildIndex < this.heap.size()
            && this.heap.get(rightChildIndex).compareTo(this.heap.get(largestElementIndex)) > 0)
        {
            largestElementIndex = rightChildIndex;
        }

        if (largestElementIndex != parentIndex)
        {
            swap(parentIndex, largestElementIndex);

            this.heapifyDown(largestElementIndex);
        }
	}

	private void swap(int firstIndex, int secondIndex) {

		T old = this.heap.get(firstIndex);
        this.heap.set(firstIndex, this.heap.get(secondIndex));
        this.heap.set(secondIndex, old);
	}
}
