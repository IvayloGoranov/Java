package hotelBookingSystem.interfaces;

public interface IRepository<T> {

	Iterable<T> getAll();
	
    T get(int id);
    
    void add(T item);
    
    boolean update(int id, T newItem);
    
    boolean delete(int id);
}
