package issueTrackingSystem.interfaces;

import java.util.Map;

public interface IEndpoint {

	String getActionName();

    Map<String, String> getParameters();
}
