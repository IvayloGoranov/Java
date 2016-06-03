package hotelBookingSystem.data;

import java.util.LinkedHashMap;

import hotelBookingSystem.interfaces.IUser;
import hotelBookingSystem.interfaces.IUserRepository;

public class UserRepository extends Repository<IUser> implements IUserRepository {

	private LinkedHashMap<String, IUser> usersByUsername;

    public UserRepository() {
    	
    	super();
        this.usersByUsername = new LinkedHashMap<String, IUser>();
    }

    public IUser getByUsername(String username) {
    	
        if (!this.usersByUsername.containsKey(username)) {
        	
            return null;
        }

        return this.usersByUsername.get(username);
    }

    public void add(IUser user) {
    	
        this.usersByUsername.put(user.getUsername(), user);
        super.add(user);
    }

    public boolean update(int id, IUser newUser) {
    	
        IUser user = this.get(id);
        if (user.getUsername() != newUser.getUsername()) {
        	
            throw new IllegalStateException("A user's username cannot be changed.");
        }

        if (super.update(id, newUser)) {
        	
            this.usersByUsername.put(newUser.getUsername(), newUser);
            
            return true;
        }

        return false;
    }

    public boolean delete(int id) {
    	
        IUser user = this.get(id);
        this.usersByUsername.remove(user.getUsername());
        
        return super.delete(id);
    }
}
