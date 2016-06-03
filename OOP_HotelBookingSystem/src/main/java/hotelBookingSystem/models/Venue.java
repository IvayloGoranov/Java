package hotelBookingSystem.models;

import java.util.ArrayList;
import java.util.Collection;

import hotelBookingSystem.interfaces.IDbEntity;
import hotelBookingSystem.interfaces.IUser;

public class Venue implements IDbEntity {

	private String name = "";
    private String address;
    private String description;
    private IUser owner;
    private int id;
    private Collection<Room> rooms;
    
    public Venue(String name, String address, String description, IUser owner) {
    	
        this.setName(name);
        this.setAddress(address);
        this.setDescription(description);
        this.setOwner(owner);
        this.rooms = new ArrayList<Room>();
    }
    
    @Override
	public int getId() {

    	return this.id;
	}

	@Override
	public void setId(int id) {
		
		this.id = id;
	}
    
    public String getName() {
		
    	return this.name;
	}

    private void setName(String value) {
    	
    	if (value == null || value.equals("")) {
    		
            throw new IllegalArgumentException("The venue name cannot be empty.");
        }

        if (value.length() < 3) {
        	
            throw new IllegalArgumentException("The venue name must be at least 3 symbols long.");
        }

        this.name = value;
    }


    public String getAddress() {
		
    	return this.address;
	}


    private void setAddress(String value) {
    	
    	if (value == null || value.equals("")) {
    		
            throw new IllegalArgumentException("The venue address cannot be empty.");
        }

        if (value.length() < 3) {
        	
            throw new IllegalArgumentException("The venue address must be at least 3 symbols long.");
        }

        this.address = value;
    }

    public String getDescription() {
		
    	return this.description;
	}

	public void setDescription(String description) {
		
		this.description = description;
	}

	public IUser getOwner() {
		
		return this.owner;
	}

	public void setOwner(IUser owner) {
		
		this.owner = owner;
	}

	public Collection<Room> getRooms() {
		
		return this.rooms;
	}
}
