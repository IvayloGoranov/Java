package sorters;

import java.util.List;

import interfaces.Sorter;
import utils.BinaryHeap;

public class HeapSorter<T extends Comparable<T>> implements Sorter<T> {
    public void sort(List<T> collection) {
    	
        BinaryHeap<T> heap = new BinaryHeap<T>(collection);
        int index = collection.size() - 1;
        while (heap.size() != 0) {
        	
            T item = heap.extractMax();
            collection.set(index, item);
            index--;
        }
    }
}
