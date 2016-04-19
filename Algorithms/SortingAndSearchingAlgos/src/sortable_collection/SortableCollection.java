package sortable_collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import interfaces.Sorter;

public class SortableCollection<T extends Comparable<T>> implements Iterable<T>{
    
	private List<T> items;
    
    public SortableCollection() {
    	
        this.items = new ArrayList<T>();
    }

    public SortableCollection(List<T> items) {
    	
        this.items = new ArrayList<T>(items);
    }

    public SortableCollection(@SuppressWarnings("unchecked") T... items) {
    	
    	this.items = new ArrayList<T>();
    	for (int i = 0; i < items.length; i++) {
			
        	this.items.add(items[i]);
		}
    }

    public List<T> getItems() { 

    	return this.items;
    }

    public int size() {
    	
    	return this.items.size();
    }

    public void sort(Sorter<T> sorter) {
    	
        sorter.sort(this.items);
    }

    public int binarySearch(T item) {
    	
        int result = this.binarySearchProcedure(0, this.items.size() - 1, item);

        return result;
    }

    public void shuffle() {
        
    	Random rnd = new Random();
        for (int i = 0; i < this.items.size(); i++) {
            int randomIndex = i + rnd.nextInt(this.items.size() - i);
            T temp = this.items.get(i);
            this.items.set(i, this.items.get(randomIndex));
            this.items.set(randomIndex, temp);
        }
    }

    @Override
	public String toString() {
		
    	StringBuilder output = new StringBuilder();
    	for (T item : items) {
			
    		output.append(item + "," + " ");
		}
    	
    	output.deleteCharAt(output.length() - 1);
    	return output.toString();
	}

    private int binarySearchProcedure(int lowBound, int highBound, T needle) {
        
    	if (highBound < lowBound) {
    		
            return -1;
        }

        int middle = lowBound + (highBound - lowBound) / 2;

        if (this.items.get(middle).compareTo(needle) == 0) {
        	
            return middle;
        } else if (this.items.get(middle).compareTo(needle) > 0) {
        	
            return this.binarySearchProcedure(lowBound, middle - 1, needle);
        } else {
        	
            return this.binarySearchProcedure(middle + 1, highBound, needle);
        }
    }

    @Override
    public Iterator<T> iterator() {
        
    	Iterator<T> it = new SortableCollectionIterator<T>(this);
        return it;
    }
}
