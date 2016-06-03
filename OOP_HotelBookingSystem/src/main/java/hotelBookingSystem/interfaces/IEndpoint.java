package hotelBookingSystem.interfaces;

import java.util.Map;

public interface IEndpoint {

	String getControllerName();
	
    String getActionName();
    
    Map<String, String> getParameters();
}
