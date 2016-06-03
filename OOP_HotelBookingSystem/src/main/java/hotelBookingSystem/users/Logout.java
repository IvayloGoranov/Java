package hotelBookingSystem.users;

import hotelBookingSystem.interfaces.IUser;
import hotelBookingSystem.views.View;

public class Logout extends View {

	public Logout(Object model) {
		
		super(model);
	}

	@Override
	protected void buildViewResult(StringBuilder viewResult) {
		
		IUser user = (IUser)this.getModel();
        viewResult.append(String.format("The user %s has logged out.\n", user.getUsername()));
	}

}
