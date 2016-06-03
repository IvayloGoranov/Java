package hotelBookingSystem.interfaces;

import java.util.Collection;

import hotelBookingSystem.models.Booking;
import hotelBookingSystem.models.Role;

public interface IUser extends IDbEntity {

	String getUsername();
	
    String getPasswordHash();
    
    Role getRole();
    
    Collection<Booking> getBookings();
    
    boolean isInRole(Role role);
}
