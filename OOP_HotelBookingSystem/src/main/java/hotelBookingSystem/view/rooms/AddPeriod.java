package hotelBookingSystem.view.rooms;

import hotelBookingSystem.models.Room;
import hotelBookingSystem.views.View;

public class AddPeriod extends View {

	public AddPeriod(Object model) {
		
		super(model);
	}

	@Override
	protected void buildViewResult(StringBuilder viewResult) {

		Room room = (Room)this.getModel();
        viewResult.append(String.format("The period has been added to room with ID %d.\n", room.getId()));
	}

}
