package sorters;

import java.util.List;

import interfaces.Sorter;

public class BubbleSorter<T extends Comparable<T>> implements Sorter<T> {
    public void sort(List<T> collection) {
    	
        this.bubbleSort(collection);
    }

    private void bubbleSort(List<T> collection) {
        
    	int index = collection.size() - 1;
        while (index > 0) {
        	
            for (int i = 0; i < index; i++) {
            	
                if (collection.get(i).compareTo(collection.get(i + 1)) > 0) {
                	
                    this.swap(collection, i, i + 1);
                }
            }

            index--;
        }
    }

    private void swap(List<T> collection, int firstIndex, int secondIndex) {
    	
        T oldValue = collection.get(firstIndex);
        collection.set(firstIndex, collection.get(secondIndex));
        collection.set(secondIndex, oldValue);
    }
}
