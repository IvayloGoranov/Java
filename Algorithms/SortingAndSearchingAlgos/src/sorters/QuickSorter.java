package sorters;

import java.util.List;

import interfaces.Sorter;

public class QuickSorter<T extends Comparable<T>> implements Sorter<T> {
    public void sort(List<T> collection) {
    	
        this.quickSort(collection, 0, collection.size() - 1);
    }

    private void quickSort(List<T> collection, int start, int end) {
        
    	if (start >= end) {
    		
            return;
        }

        T pivot = collection.get(start);
        int storeIndex = start + 1;

        for (int i = start + 1; i <= end; i++){
        	
            if (collection.get(i).compareTo(pivot) < 0) {
            	
                this.swap(collection, i, storeIndex);
                storeIndex++;
            }
        }

        storeIndex--;
        this.swap(collection, start, storeIndex);

        this.quickSort(collection, start, storeIndex);
        this.quickSort(collection, storeIndex + 1, end);
    }

    private void swap(List<T> collection, int firstIndex, int secondIndex) {
    	
        T oldValue = collection.get(firstIndex);
        collection.set(firstIndex, collection.get(secondIndex));
        collection.set(secondIndex, oldValue);
    }
}
