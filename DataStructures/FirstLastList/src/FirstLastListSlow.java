import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FirstLastListSlow<T extends Comparable<T>> implements IFirstLastList<T> {
	
	private ArrayList<T> elements = new ArrayList<T>();
	
	@Override
	public void add(T element) {
		
		this.elements.add(element);
	}
	
	@Override
	public int getCount() {
		
		return this.elements.size();
	}
	
	@Override
	public Iterable<T> first(int count) {
		
		this.validateCount(count);
		
		return this.elements.stream().limit(count)::iterator;
	}
	
	@Override
	public Iterable<T> last(int count) {
		
		this.validateCount(count);
		ArrayList<T> reversed = new ArrayList<T>(this.elements);
		Collections.reverse(reversed);
		
		return reversed.stream().limit(count)::iterator;
	}
	
	@Override
	public Iterable<T> min(int count) {
		
		this.validateCount(count);
		
		return this.elements.stream()
				.sorted(Comparator.naturalOrder())
				.limit(count)::iterator;
	}
	
	@Override
	public Iterable<T> max(int count) {
		
		this.validateCount(count);
		return this.elements.stream()
				.sorted(Comparator.reverseOrder())
				.limit(count)::iterator;
	}
	
	@Override
	public void clear() {
		
		this.elements.clear();
	}
	
	@Override
	public int removeAll(T element) {
		
		int oldCount = this.elements.size();
		this.elements.removeIf(p -> p.compareTo(element) == 0);
		int newCount = this.elements.size();
		return oldCount - newCount;
	}
	
	private void validateCount(int count) {
		
		if (count < 0 || count > this.getCount()) {
			throw new IllegalArgumentException(
					"The count must be within the range of the collection.");
		}
	}
}
