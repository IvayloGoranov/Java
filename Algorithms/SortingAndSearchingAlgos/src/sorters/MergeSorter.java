package sorters;

import java.util.List;

import interfaces.Sorter;

public class MergeSorter<T extends Comparable<T>> implements Sorter<T> {
    public void sort(List<T> collection) {
    	
        this.mergeSort(collection, 0, collection.size() - 1);
    }

    private void mergeSort(List<T> collection, int left, int right) {
    	
        if (left < right) {
        	
            int middle = (left + right) / 2;
            
            this.mergeSort(collection, left, middle);
            
            this.mergeSort(collection, middle + 1, right);
            
            this.merge(collection, left, middle, right);
        }
    }

    private void merge(List<T> collection, int left, int middle, int right)
    {
    	@SuppressWarnings("unchecked")
		T[] tempArray = (T[])new Object[right - left + 1];
        int leftMinIndex = left;
        int rightMinIndex = middle + 1;
        int tempIndex = 0;
        
        while (leftMinIndex <= middle && rightMinIndex <= right) {
        	
            if (collection.get(leftMinIndex).compareTo(collection.get(rightMinIndex)) < 0) {
            	
                tempArray[tempIndex] = collection.get(leftMinIndex);
                tempIndex++;
                leftMinIndex++;
            } else {
                tempArray[tempIndex] = collection.get(rightMinIndex);
                tempIndex++;
                rightMinIndex++;
            }
        }

        while (leftMinIndex <= middle) {
        	
            tempArray[tempIndex] = collection.get(leftMinIndex);
            tempIndex++;
            leftMinIndex++;
        }

        while (rightMinIndex <= right) {
        	
            tempArray[tempIndex] = collection.get(rightMinIndex);
            tempIndex++;
            rightMinIndex++;
        }

        tempIndex = 0;
        leftMinIndex = left;
       
        while (tempIndex < tempArray.length && leftMinIndex <= right) {
        	
            collection.set(leftMinIndex, tempArray[tempIndex]);
            leftMinIndex++;
            tempIndex++;
        }
    }
}
