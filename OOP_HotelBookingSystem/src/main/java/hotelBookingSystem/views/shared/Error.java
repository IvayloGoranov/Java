package hotelBookingSystem.views.shared;

import hotelBookingSystem.views.View;

public class Error extends View {

	public Error(Object model) {
		
		super(model);
	}

	@Override
	protected void buildViewResult(StringBuilder viewResult) {

		String message = (String)this.getModel();
        viewResult.append(message + "\n");
	}

}
