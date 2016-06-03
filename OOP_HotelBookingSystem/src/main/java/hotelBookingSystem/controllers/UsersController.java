package hotelBookingSystem.controllers;

import java.lang.reflect.InvocationTargetException;

import hotelBookingSystem.interfaces.IHotelBookingSystemData;
import hotelBookingSystem.interfaces.IUser;
import hotelBookingSystem.interfaces.IView;
import hotelBookingSystem.models.Role;
import hotelBookingSystem.models.User;
import hotelBookingSystem.utilities.HashUtilities;

public class UsersController extends Controller {

	public UsersController(IHotelBookingSystemData data, IUser user){
		
		super(data, user);
	}

	public IView register(String username, String password, String confirmPassword, String role) 
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, 
			InvocationTargetException, SecurityException {
		
	    if (password != confirmPassword) {
	    	
	        throw new IllegalArgumentException("The provided passwords do not match.");
	    }
	
	    this.ensureNoLoggedInUser();
	
	    IUser existingUser = this.getData().getRepositoryWithUsers().getByUsername(username);
	    if (existingUser != null) {
	    	
	        throw new IllegalArgumentException(String.format("A user with username %s already exists.", username));
	    }
	
	    Role userRole = Role.valueOf(role);
	    IUser user = new User(username, password, userRole);
	    this.getData().getRepositoryWithUsers().add(user);
	    IView view = this.view(user);
	    
	    return view;
	}

	public IView login(String username, String password) 
			throws InstantiationException, IllegalAccessException, 
			IllegalArgumentException, InvocationTargetException, SecurityException {
		
	    this.ensureNoLoggedInUser();
	
	    IUser existingUser = this.getData().getRepositoryWithUsers().getByUsername(username);
	    if (existingUser == null) {
	    	
	        throw new IllegalArgumentException(String.format("A user with username %s does not exist.", username));
	    }
	
	    if (existingUser.getPasswordHash() != HashUtilities.getSha256Hash(password)) {
	    	
	        throw new IllegalArgumentException("The provided password is wrong.");
	    }
	
	    this.setCurrentUser(existingUser);
	    IView view = this.view(existingUser);
	    
	    return view;
	}

	public IView myProfile() 
			throws InstantiationException, IllegalAccessException, 
			IllegalArgumentException, InvocationTargetException, SecurityException {
		
	    this.authorize(Role.USER, Role.VENUE_ADMIN);
	    IView view = this.view(this.getCurrentUser());
	
	    return view;
	}

	public IView logout() 
			throws InstantiationException, IllegalAccessException, 
			IllegalArgumentException, InvocationTargetException, SecurityException {
		
	    this.authorize(Role.USER, Role.VENUE_ADMIN);
	
	    IUser user = this.getCurrentUser();
	    this.setCurrentUser(null);
	    IView view = this.view(user);
	
	    return view;
	}
}
