package hotelBookingSystem.view.rooms;

import java.time.format.DateTimeFormatter;

import hotelBookingSystem.models.Booking;
import hotelBookingSystem.views.View;

public class Book extends View {

	public Book(Object model) {
		
		super(model);
	}

	@Override
	protected void buildViewResult(StringBuilder viewResult) {

		Booking booking = (Booking)this.getModel();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		viewResult.append(String.format("Room booked from %s to %s for $%s.\n",
            booking.getStartBookDate().format(formatter), booking.getEndBookDate().format(formatter), 
            booking.getTotalPrice().setScale(2).toString()));
	}

}
