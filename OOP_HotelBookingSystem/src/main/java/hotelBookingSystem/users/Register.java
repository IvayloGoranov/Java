package hotelBookingSystem.users;

import hotelBookingSystem.interfaces.IUser;
import hotelBookingSystem.views.View;

public class Register extends View {

	public Register(Object model) {
		
		super(model);
	}

	@Override
	protected void buildViewResult(StringBuilder viewResult) {
		
		IUser user = (IUser)this.getModel();
        viewResult.append(String.format("The user %s has been registered and may login.\n", 
        		user.getUsername()));
	}

}
