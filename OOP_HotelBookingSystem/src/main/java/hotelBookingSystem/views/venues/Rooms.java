package hotelBookingSystem.views.venues;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import hotelBookingSystem.models.AvailableDate;
import hotelBookingSystem.models.Room;
import hotelBookingSystem.models.Venue;
import hotelBookingSystem.views.View;

public class Rooms extends View {

	public Rooms(Object model) {
		
		super(model);
	}

	@Override
	protected void buildViewResult(StringBuilder viewResult) {
		
		Venue venue = (Venue)this.getModel();
        viewResult.append(String.format("Available rooms for venue %s:\n", venue.getName()));
        if (this.makeCollection(venue.getRooms()).size() == 0) {
        	
            viewResult.append("No rooms are currently available.\n");
        } else {
        	
            for (Room room : venue.getRooms())
            {
                viewResult.append(String.format(" *[%d] %d places, $%s per day\n",
                    room.getId(), room.getPlacesCount(), room.getPricePerDay().setScale(2).toString()));
                if (this.makeCollection(room.getAvailableDates()).size() == 0) {
                	
                    viewResult.append("This room is not currently available.\n");
                } else {
                	
                    viewResult.append("Available dates:\n");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    
                    for (AvailableDate datePair : room.getAvailableDates().
                    		stream().sorted((d1, d2) -> d1.getEndDate().compareTo(d2.getEndDate())).collect(Collectors.toList())) {
                    	
                        viewResult.append(String.format(" - %s - %s\n",
                            datePair.getStartDate().format(formatter), datePair.getEndDate().format(formatter)));
                    }
                }
            }
        }
	}
}
