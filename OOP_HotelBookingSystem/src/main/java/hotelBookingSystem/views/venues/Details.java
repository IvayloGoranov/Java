package hotelBookingSystem.views.venues;

import hotelBookingSystem.models.Room;
import hotelBookingSystem.models.Venue;
import hotelBookingSystem.views.View;

public class Details extends View {

	public Details(Object model) {
		
		super(model);
	}

	@Override
	protected void buildViewResult(StringBuilder viewResult) {

		Venue venue = (Venue)this.getModel();
        viewResult.append(venue.getName() + "\n")
            .append(String.format("Located at %s\n", venue.getAddress()));
        viewResult.append(String.format("Description: %s\n", venue.getDescription()));
        if (this.makeCollection(venue.getRooms()).size() == 0) {
        	
            viewResult.append("No rooms are currently available\n");
        } else {
        	
            viewResult.append("Available rooms:\n");
            for (Room room : venue.getRooms()) {
            	
                viewResult.append(String.format
                		(" * %d places ($%s per day)\n", room.getPlacesCount(), room.getPricePerDay().setScale(2).toString()));
            }
        }
	}
}
