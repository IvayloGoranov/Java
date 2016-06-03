package hotelBookingSystem.data;

import java.util.LinkedHashMap;

import hotelBookingSystem.interfaces.IDbEntity;
import hotelBookingSystem.interfaces.IRepository;

public class Repository<T extends IDbEntity> implements IRepository<T> {

	private int nextAddId = 1;
    private LinkedHashMap<Integer, T> items;

    public Repository() {
    	
        this.items = new LinkedHashMap<Integer, T>();
    }

    public Iterable<T> getAll() {
    	
        return this.items.values();
    }

    public T get(int id) {
    	
        T item = this.items.get(id);

        return item;
    }

    public void add(T item) {
    	
        item.setId(this.nextAddId);
        this.items.put(this.nextAddId, item);
        this.nextAddId++;
    }

    public boolean update(int id, T newItem) {
    	
        T item = this.get(id);
        if (item == null) {
        	
            throw new IllegalArgumentException(String.format("No item with id %d in database", id));
        }

        this.items.put(id, newItem);
        
        return true;
    }

    public boolean delete(int id) {
    	
        T itemRemoved = this.items.remove(id);
        if (itemRemoved == null) {
			
        	return false;
		}
        
        return true;
    }
}
