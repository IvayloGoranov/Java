package sorters;

import java.util.List;

import interfaces.Sorter;

public class InsertionSorter<T extends Comparable<T>> implements Sorter<T> {
    public void sort(List<T> collection){
    	
        this.insertionSort(collection);
    }

    private void insertionSort(List<T> collection) {
    	
        int index = 0;
        while (index < collection.size() - 1){
            
        	for (int i = index + 1; i > 0; i--) {
        		
                if (collection.get(i).compareTo(collection.get(i - 1)) < 0)
                {
                	this.swap(collection, i, i - 1);
                }
                else
                {
                    break;
                }
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