package hotelBookingSystem.models;

import java.util.ArrayList;
import java.util.Collection;

import hotelBookingSystem.interfaces.IUser;
import hotelBookingSystem.utilities.HashUtilities;

public class User implements IUser {

	private String username;
    private String passwordHash;
    private int id;
    private Role role;
    private Collection<Booking> bookings;

    public User(String username, String password, Role role) {
    	
        this.setUserName(username);
        this.setPasswordHash(password);
        this.role = role;
        this.bookings = new ArrayList<Booking>();
    }

    @Override
	public int getId() {

    	return this.id;
	}
	@Override
	public void setId(int id) {

		this.id = id;
	}

    @Override
	public String getUsername() {

    	return this.username;
	}
	
    private void setUserName(String value) {
    	
    	if (value == null || value.equals("")) {
    		
            throw new IllegalArgumentException("The username cannot be empty.");
        }

        if (value.length() < 5) {
        	
            throw new IllegalArgumentException("The username must be at least 5 symbols long.");
        }

        this.username = value;
    }
    
    @Override
	public String getPasswordHash() {

    	return this.passwordHash;
	}

    private void setPasswordHash(String value) {
    	
    	if (value == null || value.equals("")) {
    		
            throw new IllegalArgumentException("The password cannot be empty.");
        }

        if (value.length() < 6) {
        	
            throw new IllegalArgumentException("The password must be at least 6 symbols long.");
        }

        this.passwordHash = HashUtilities.getSha256Hash(value);
    }

    @Override
	public Role getRole() {

    	return this.role;
	}
    
    @Override
	public Collection<Booking> getBookings() {

    	return this.bookings;
	}
    
    @Override
    public boolean isInRole(Role role) {
    	
        boolean isInRole = (this.getRole() == role);
        return isInRole;
    }
}
