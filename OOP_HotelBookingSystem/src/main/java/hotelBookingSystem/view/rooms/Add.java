package hotelBookingSystem.view.rooms;

import hotelBookingSystem.models.Room;
import hotelBookingSystem.views.View;

public class Add extends View {

	public Add(Object model) {
		
		super(model);
	}

	@Override
	protected void buildViewResult(StringBuilder viewResult) {

		Room room = (Room)this.getModel();
        viewResult.append(String.format("The room with ID %d has been created successfully.\n", room.getId()));
	}

}
