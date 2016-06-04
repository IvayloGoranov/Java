package issueTrackingSystem.core;

import java.util.LinkedHashMap;
import java.util.Map;

import issueTrackingSystem.interfaces.IEndpoint;

public class Endpoint implements IEndpoint {

	private String actionName;
	private Map<String, String> parameters;
	
	public Endpoint(String url){
		
        this.parse(url);
    }

    public String getActionName() {
		
    	return this.actionName;
	}

	public Map<String, String> getParameters() {
		
		return this.parameters;
	}

	private void parse(String url) {
		
        int questionMark = url.indexOf('?');

        if (questionMark != -1) {
        	
            this.actionName = url.substring(0, questionMark);
            this.parameters = new LinkedHashMap<String, String>();

            String[] parameterPairs = url.substring(questionMark + 1).split("&");

            for (String pair : parameterPairs) {
            	
                String[] nameAndValue = pair.split("=");
                String name = nameAndValue[0];
                String value = nameAndValue[1];

                this.parameters.put(name, value);
            }
        } else {
        	
            this.actionName = url;
        }
    }
}
