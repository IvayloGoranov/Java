package sorters;

import java.util.List;

import interfaces.Sorter;

public class SelectionSorter<T extends Comparable<T>> implements Sorter<T> {
    
	public void sort(List<T> collection) {
    	
        this.selectionSort(collection);
    }

    private void selectionSort(List<T> collection) {
        
    	int index = 0;
        while (index < collection.size() - 1) {
            
        	int currentMinimum = index;
            for (int j = index + 1; j < collection.size(); j++) {
            	
                if (collection.get(currentMinimum).compareTo(collection.get(j)) > 0) {
                    
                	currentMinimum = j;
                }
            }

            if (index != currentMinimum) {
            	
                this.swap(collection, index, currentMinimum);
            }

            index++;
        }
    }

    private void swap(List<T> collection, int firstIndex, int secondIndex) {
    	
        T oldValue = collection.get(firstIndex);
        collection.set(firstIndex, collection.get(secondIndex));
        collection.set(secondIndex, oldValue);
    }
}
