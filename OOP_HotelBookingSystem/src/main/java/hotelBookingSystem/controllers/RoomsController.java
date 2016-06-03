package hotelBookingSystem.controllers;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.NoSuchElementException;

import hotelBookingSystem.interfaces.IHotelBookingSystemData;
import hotelBookingSystem.interfaces.IUser;
import hotelBookingSystem.interfaces.IView;
import hotelBookingSystem.models.AvailableDate;
import hotelBookingSystem.models.Booking;
import hotelBookingSystem.models.Role;
import hotelBookingSystem.models.Room;
import hotelBookingSystem.models.Venue;

import java.time.temporal.ChronoUnit;

public class RoomsController extends Controller {

	public RoomsController(IHotelBookingSystemData data, IUser user) {
		
		super(data, user);
	}

	/*Adds a new bookable room to a system venue (e.g. hotel).
	 * @param venueId The id of the venue in the database.
	 * @param places The places count in the the venue (hotel) room.
	 * @param pricePerDay The daily booking rate.
	 * @return IView Views contain the presentation logic in the system. They contain the results which are given to the user.
	 */
	public IView add(int venueId, int places, BigDecimal pricePerDay) 
			throws InstantiationException, IllegalAccessException, 
			IllegalArgumentException, InvocationTargetException, SecurityException {
		
	    this.authorize(Role.VENUE_ADMIN);
	    Venue venue = this.getData().getRepositoryWithVenues().get(venueId);
	    if (venue == null) {
	    	
	        throw new IllegalArgumentException(String.format("The venue with ID %d does not exist.", venueId));
	    }
	
	    Room newRoom = new Room(places, pricePerDay);
	    venue.getRooms().add(newRoom);
	    this.getData().getRepositoryWithRooms().add(newRoom);
	    
	    return this.view(newRoom);
	}

	public IView addPeriod(int roomId, LocalDate startDate, LocalDate endDate) 
			throws InstantiationException, IllegalAccessException, 
			IllegalArgumentException, InvocationTargetException, SecurityException {
		
	    this.authorize(Role.VENUE_ADMIN);
	    Room room = this.getData().getRepositoryWithRooms().get(roomId);
	    if (room == null) {
	    	
	        throw new IllegalArgumentException(String.format("The room with ID %d does not exist.", roomId));
	    }
	
	    if (endDate.compareTo(startDate) < 0) {
	    	
	        throw new IllegalArgumentException("The date range is invalid.");
	    }
	
	    room.getAvailableDates().add(new AvailableDate(startDate, endDate));
	    
	    return this.view(room);
	}

	public IView ViewBookings(int id) 
			throws InstantiationException, IllegalAccessException, 
			IllegalArgumentException, InvocationTargetException, SecurityException {
		
	    this.authorize(Role.VENUE_ADMIN);
	    Room room = this.getData().getRepositoryWithRooms().get(id);
	    if (room == null) {
	    	
	    	throw new IllegalArgumentException(String.format("The room with ID %d does not exist.", id));
	    }
	
	    return this.view(room.getBookings());
	}

	public IView Book(int roomId, LocalDate startDate, LocalDate endDate, String comments) 
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, 
			InvocationTargetException, SecurityException{
		
	    this.authorize(Role.USER, Role.VENUE_ADMIN);
	    Room room = this.getData().getRepositoryWithRooms().get(roomId);
	    if (room == null) {
	    	
	        throw new IllegalArgumentException(String.format("The room with ID %d does not exist.", roomId));
	    }
	
	    if (endDate.compareTo(startDate) < 0) {
	    	
	        throw new IllegalArgumentException("The date range is invalid.");
	    }
	
	    AvailableDate availablePeriod;
	    try {
			
	    	availablePeriod = room.getAvailableDates().stream().
		    		filter(d -> d.getStartDate().compareTo(startDate) <= 0 || d.getEndDate().compareTo(endDate) >= 0).findFirst().get();
		} catch (NoSuchElementException e) {

			throw new IllegalArgumentException(
		            String.format("The room is not available to book in the period {0:dd.MM.yyyy} - {1:dd.MM.yyyy}.",
		            startDate, endDate));
		}

	    BigDecimal totalPrice = new BigDecimal(ChronoUnit.DAYS.between(endDate, startDate)).
	    		multiply(room.getPricePerDay());
	    Booking booking = new Booking(this.getCurrentUser(), startDate, endDate, totalPrice, comments);
	    room.getBookings().add(booking);
	    this.getCurrentUser().getBookings().add(booking);
	    
	    this.updateRoomAvailability(startDate, endDate, room, availablePeriod);
	    
	    return this.view(booking);
	}

	private void updateRoomAvailability(LocalDate startDate, LocalDate endDate, Room room, AvailableDate availablePeriod) {
		
	    room.getAvailableDates().remove(availablePeriod);
	    long periodBeforeBooking = ChronoUnit.DAYS.between(startDate, availablePeriod.getStartDate());
	    if (periodBeforeBooking > 0) {
	    	
	        room.getAvailableDates().add(
	            new AvailableDate(availablePeriod.getStartDate(), availablePeriod.getStartDate().plusDays(periodBeforeBooking)));
	    }
	
	    long periodAfterBooking = ChronoUnit.DAYS.between(availablePeriod.getEndDate(), endDate);
	    if (periodAfterBooking > 0) {
	    	
	        room.getAvailableDates().add(
	            new AvailableDate(availablePeriod.getEndDate().minusDays(periodAfterBooking), availablePeriod.getEndDate()));
	    }
	}
}
