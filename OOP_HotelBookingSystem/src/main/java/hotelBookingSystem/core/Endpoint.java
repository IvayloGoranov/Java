package hotelBookingSystem.core;

import java.util.LinkedHashMap;
import java.util.Map;

import hotelBookingSystem.interfaces.IEndpoint;

public class Endpoint implements IEndpoint {

	private final String ControllerSuffix = "Controller";
    
	private String actionName;
	private String controllerName;
	private Map<String, String> parameters;
	
    public Endpoint(String url) {
    	
        this.parse(url);
    }

    public String getActionName() {
		
    	return this.actionName;
	}

	public String getControllerName() {
		
		return this.controllerName;
	}

	public Map<String, String> getParameters() {
		
		return this.parameters;
	}

	private void parse(String url) {
		
        String[] endpointParts = url.split("//|/?");
        if (endpointParts.length < 2) {
        	
            throw new IllegalStateException("The provided route is invalid.");
        }

        String partialControllerName = endpointParts[0];
        this.controllerName = partialControllerName + ControllerSuffix;
        
        this.actionName = endpointParts[1];

        if (endpointParts.length >= 3) {
        	
            this.parameters = new LinkedHashMap<String, String>();
            
            String[] parameterPairs = endpointParts[2].split("&");
            
            for (String pair : parameterPairs) {
            	
                String[] nameAndValue = pair.split("=");
                String name = nameAndValue[0];
                String value = nameAndValue[1];

                this.parameters.put(name, value);
            }
        }
    }
}
