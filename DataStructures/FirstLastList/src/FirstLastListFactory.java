
public class FirstLastListFactory {
	
    public static <T extends Comparable<T>> IFirstLastList<T> create() {
    	
    	return new FirstLastListSlow<T>();
    }
}
