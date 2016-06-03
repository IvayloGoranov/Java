package hotelBookingSystem.views.venues;

import hotelBookingSystem.models.Venue;
import hotelBookingSystem.views.View;

public class All extends View {

	public All(Object model) {
		
		super(model);
	}

	@Override
	protected void buildViewResult(StringBuilder viewResult) {

		@SuppressWarnings("unchecked")
		Iterable<Venue> venues = (Iterable<Venue>) this.getModel();
        if (this.makeCollection(venues).size() == 0){
        	
            viewResult.append("There are currently no venues to show.\n");
        } else {
        	
            for (Venue venue : venues) {
            	
                viewResult.append(String.format
                		("*[%d] %s, located at %s\n", venue.getId(), venue.getName(), venue.getAddress()));
                viewResult.append(String.format("Free rooms: %d\n", venue.getRooms().size()));
            }
        }
	}
}
