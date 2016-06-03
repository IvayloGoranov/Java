package hotelBookingSystem.exceptions;

import hotelBookingSystem.interfaces.IUser;

@SuppressWarnings("serial")
public class AuthorizationFailedException extends IllegalArgumentException {

	private IUser user;
	
	public AuthorizationFailedException(String message, IUser user) {
        
		super(message);
		this.setUser(user);
    }

	public IUser getUser() {
		
		return this.user;
	}

	public void setUser(IUser user) {
		
		this.user = user;
	}

    
}
