package hotelBookingSystem.views.venues;

import hotelBookingSystem.models.Venue;
import hotelBookingSystem.views.View;

public class Add extends View {

	public Add(Object model) {
		
		super(model);
	}

	@Override
	protected void buildViewResult(StringBuilder viewResult) {
		
		Venue venue = (Venue)this.getModel();
        
		viewResult.append(String.format
				("The venue %s with ID %d has been created successfully.\n", venue.getName(), venue.getId()));
	}

}
