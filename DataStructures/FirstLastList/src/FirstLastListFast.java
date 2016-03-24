
import java.util.*;

public class FirstLastListFast<T extends Comparable<T>> implements IFirstLastList<T> {

	//Unfinished!!!
	
	private TreeMap<T, List<Integer>> elements = new TreeMap<T, List<Integer>>();
    private TreeMap<Integer, T> elementsByOrderOfAddition = new TreeMap<Integer, T>();
    private int nextAddIndex = 0;

    @Override
    public void add(T element) {
        
    	nextAddIndex++;
        if (!this.elements.containsKey(element)) {
            this.elements.put(element, new ArrayList<>());
        }

        this.elements.get(element).add(nextAddIndex);
        this.elementsByOrderOfAddition.put(nextAddIndex, element);
    }

    @Override
    public int getCount() {
        
    	return this.elements.size();
    }

    @Override
    public Iterable<T> first(int count) {
        
    	this.validateCount(count);
        
    	return this.elementsByOrderOfAddition
                .values()
                .stream()
                .map(e -> e)
                .limit(count)::iterator;
    }

    @Override
    public Iterable<T> last(int count) {
        
    	this.validateCount(count);

    	return null;
    }

    @Override
    public Iterable<T> min(int count) {
        
    	this.validateCount(count);
        return null;
    }

    @Override
    public Iterable<T> max(int count) {
        
    	this.validateCount(count);
        return null;
    }

    @Override
    public void clear() {
        
    	this.elements.clear();
        this.elementsByOrderOfAddition.clear();
    }

    @Override
    public int removeAll(T element) {
        
    	return 0;
    }

    private void validateCount(int count) {
        
    	if (count < 0 || count > this.getCount()) {
            
    		throw new IllegalArgumentException("The count must be within the range of the collection.");
        }
    }
}

