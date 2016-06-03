package hotelBookingSystem.view.rooms;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import hotelBookingSystem.models.Booking;
import hotelBookingSystem.views.View;

public class ViewBooking extends View {

	public ViewBooking(Object model) {
		
		super(model);
	}

	@Override
	protected void buildViewResult(StringBuilder viewResult) {
		
		@SuppressWarnings("unchecked")
		Iterable<Booking> bookings = (Iterable<Booking>)this.getModel();
		ArrayList<Booking> bookingsCollection = this.makeCollection(bookings);
		if (bookingsCollection.size() == 0) {
        	
            viewResult.append("There are no bookings for this room.\n");
        } else {
        	
            viewResult.append("Room bookings:\n");
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        for (Booking booking : bookingsCollection) {
        	
            viewResult.append(String.format("* %s - %s ($%s)\n", 
                booking.getStartBookDate().format(formatter), booking.getEndBookDate().format(formatter), 
                booking.getTotalPrice().setScale(2).toString()));
        }
        
        BigDecimal sum = null;
        for (int i = 0; i < bookingsCollection.size() - 1; i++) {
			
        	sum = bookingsCollection.get(i).getTotalPrice().add(bookingsCollection.get(i + 1).getTotalPrice());
		}
        
        viewResult.append(String.format("Total booking price: $%s\n", sum));
	}

}
