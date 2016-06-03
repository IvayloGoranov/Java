package hotelBookingSystem.interfaces;

import hotelBookingSystem.models.Booking;
import hotelBookingSystem.models.Room;
import hotelBookingSystem.models.Venue;

public interface IHotelBookingSystemData {

	IUserRepository getRepositoryWithUsers();
	
    IRepository<Venue> getRepositoryWithVenues();
    
    IRepository<Room> getRepositoryWithRooms();
    
    IRepository<Booking> getRepositoryWithBookings();
}
