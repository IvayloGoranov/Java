package hotelBookingSystem.users;

import java.time.format.DateTimeFormatter;

import hotelBookingSystem.interfaces.IUser;
import hotelBookingSystem.models.Booking;
import hotelBookingSystem.views.View;

public class MyProfile extends View {

	public MyProfile(Object model) {
		
		super(model);
	}

	@Override
	protected void buildViewResult(StringBuilder viewResult) {

		IUser user = (IUser)this.getModel();
        viewResult.append(user.getUsername());
        if (this.makeCollection(user.getBookings()).size() == 0) {
        	
            viewResult.append("You have not made any bookings yet.\n");
        } else {
        	
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        	viewResult.append("Your bookings:\n");
            for (Booking booking : user.getBookings()) {
            	
                viewResult.append(String.format("* %s - %s ($%s)\n",
                    booking.getStartBookDate().format(formatter), booking.getEndBookDate().format(formatter),
                    booking.getTotalPrice().setScale(2).toString()));
            }
        }
	}

}
