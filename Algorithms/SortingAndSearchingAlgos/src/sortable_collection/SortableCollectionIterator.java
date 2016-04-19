package sortable_collection;

import java.util.Iterator;

public class SortableCollectionIterator<T extends Comparable<T>> implements Iterator<T> {

	private int currentIndex = 0;
	private SortableCollection<T> collection;
	
	public SortableCollectionIterator(SortableCollection<T> collection) {
		
		this.collection = collection;
	}
	
	@Override
	public boolean hasNext() {

		return currentIndex < collection.size();
	}

	@Override
	public T next() {

		return this.collection.getItems().get(currentIndex++);
	}

    
}
