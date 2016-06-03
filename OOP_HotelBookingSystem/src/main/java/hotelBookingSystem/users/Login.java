package hotelBookingSystem.users;

import hotelBookingSystem.interfaces.IUser;
import hotelBookingSystem.views.View;

public class Login extends View {

	public Login(Object model) {
		
		super(model);
	}

	@Override
	protected void buildViewResult(StringBuilder viewResult) {

		IUser user = (IUser)this.getModel();
        viewResult.append(String.format("The user %s has logged in.\n", user.getUsername()));
	}

}
