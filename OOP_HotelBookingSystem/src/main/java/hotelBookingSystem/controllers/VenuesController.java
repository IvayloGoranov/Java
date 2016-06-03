package hotelBookingSystem.controllers;

import java.lang.reflect.InvocationTargetException;

import hotelBookingSystem.interfaces.IHotelBookingSystemData;
import hotelBookingSystem.interfaces.IUser;
import hotelBookingSystem.interfaces.IView;
import hotelBookingSystem.models.Role;
import hotelBookingSystem.models.Venue;

public class VenuesController extends Controller {

	public VenuesController(IHotelBookingSystemData data, IUser user) {
		
		super(data, user);
	}

	public IView all() 
			throws InstantiationException, IllegalAccessException, 
			IllegalArgumentException, InvocationTargetException, SecurityException {
		
	    Iterable<Venue> venues = this.getData().getRepositoryWithVenues().getAll();
	    IView view = this.view(venues);
	    
	    return view;
	}

	public IView details(int id) 
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, 
			InvocationTargetException, SecurityException {
		
	    if (this.hasCurrentUser() == false){
	    	
	        throw new IllegalArgumentException("There is no currently logged in user.");
	    }
	    
	    this.authorize(Role.USER, Role.VENUE_ADMIN);
	    
	    Venue venue = this.getData().getRepositoryWithVenues().get(id);
	    if (venue == null) {
	    	
	        throw new IllegalArgumentException(String.format("The venue with ID %d does not exist.", id));
	    }
	
	    IView view = this.view(venue);
	
	    return view;
	}

	public IView rooms(int id) 
			throws InstantiationException, IllegalAccessException, 
			IllegalArgumentException, InvocationTargetException, SecurityException {
		
		if (this.hasCurrentUser() == false){
	    	
	        throw new IllegalArgumentException("There is no currently logged in user.");
	    }
	    
	    this.authorize(Role.USER, Role.VENUE_ADMIN);
	
	    Venue venue = this.getData().getRepositoryWithVenues().get(id);
	    if (venue == null) {
	    	
	        throw new IllegalArgumentException(String.format("The venue with ID %d does not exist.", id));
	    }
	
	    IView view = this.view(venue);
	
	    return view;
	}

	public IView add(String name, String address, String description) 
			throws InstantiationException, IllegalAccessException, 
			IllegalArgumentException, InvocationTargetException, SecurityException {
		
		if (this.hasCurrentUser() == false){
	    	
	        throw new IllegalArgumentException("There is no currently logged in user.");
	    }
	    
	    this.authorize(Role.USER, Role.VENUE_ADMIN);
	    
	    Venue newVenue = new Venue(name, address, description, this.getCurrentUser());
	    this.getData().getRepositoryWithVenues().add(newVenue);
	    IView view = this.view(newVenue);
	
	    return view;
	}
}
