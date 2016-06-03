package hotelBookingSystem.data;

import hotelBookingSystem.interfaces.IRepository;
import hotelBookingSystem.interfaces.IUserRepository;
import hotelBookingSystem.models.Booking;
import hotelBookingSystem.models.Room;
import hotelBookingSystem.models.Venue;

public class HotelBookingSystemData {

	private IUserRepository repositoryWithUsers;
	private IRepository<Venue> repositoryWithVenues;
	private IRepository<Room> repositoryWithRooms;
	private IRepository<Booking> repositoryWithBookings;
	
	public HotelBookingSystemData() {
		
        this.repositoryWithUsers = new UserRepository();
        this.repositoryWithVenues = new Repository<Venue>();
        this.repositoryWithRooms = new Repository<Room>();
        this.repositoryWithBookings = new Repository<Booking>();
    }

	public IUserRepository getRepositoryWithUsers() {
		
		return this.repositoryWithUsers;
	}

	public IRepository<Venue> getRepositoryWithVenues() {
		
		return this.repositoryWithVenues;
	}

	public IRepository<Room> getRepositoryWithRooms() {
		
		return this.repositoryWithRooms;
	}

	public IRepository<Booking> getRepositoryWithBookings() {
		
		return this.repositoryWithBookings;
	}
}
