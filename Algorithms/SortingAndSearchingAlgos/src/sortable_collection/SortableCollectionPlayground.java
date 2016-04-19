package sortable_collection;

import java.util.ArrayList;
import java.util.List;

import sorters.QuickSorter;

public class SortableCollectionPlayground {
	
    public static void main(String[] args) {
    	
    	SortableCollection<Integer> collection = new SortableCollection<Integer>(2, -1, 5, 0, -3);
        System.out.println(collection);

        collection.sort(new QuickSorter<Integer>());
        System.out.println(collection);

        List<Integer> listToShuffle = new ArrayList<Integer>();
        for (int i = 1; i <= 100; i++) {
			
        	listToShuffle.add(i);
		}
        
        SortableCollection<Integer> collectionToShuflle = new SortableCollection<Integer>(listToShuffle);
        collectionToShuflle.shuffle();
        System.out.println(collectionToShuflle);
    }
}
