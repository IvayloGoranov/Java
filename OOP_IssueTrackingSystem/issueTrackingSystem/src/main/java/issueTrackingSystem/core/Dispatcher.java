package issueTrackingSystem.core;

import issueTrackingSystem.interfaces.IDispatcher;
import issueTrackingSystem.interfaces.IEndpoint;
import issueTrackingSystem.interfaces.IIssueTracker;
import issueTrackingSystem.models.IssuePriority;

public class Dispatcher implements IDispatcher {

	private IIssueTracker tracker;
    
    Dispatcher(IIssueTracker tracker) {
    	
        this.tracker = tracker;
    }
    
    public Dispatcher(){
    	
    	this(new IssueTracker());
    }

    public String dispatchAction(IEndpoint endpoint) {
    	
        switch (endpoint.getActionName()) {
        
            case "RegisterUser":
                String username = endpoint.getParameters().get("username");
                String password = endpoint.getParameters().get("password");
                String confirmPassword = endpoint.getParameters().get("confirmPassword");
                
                return this.tracker.registerUser(username, password, confirmPassword);
            case "LoginUser":
                username = endpoint.getParameters().get("username");
                password = endpoint.getParameters().get("password");
                
                return this.tracker.loginUser(username, password);
            case "LogoutUser":
                return this.tracker.logoutUser();
            case "CreateIssue":
                String title = endpoint.getParameters().get("title");
                String description = endpoint.getParameters().get("description");
                IssuePriority priority = IssuePriority.valueOf(endpoint.getParameters().get("priority"));
                String[] tags = endpoint.getParameters().get("tags").split("|");
                
                return this.tracker.createIssue(title, description, priority, tags);
            case "RemoveIssue":
                int issueId = Integer.parseInt(endpoint.getParameters().get("id"));
                
                return this.tracker.removeIssue(issueId);
            case "AddComment":
                issueId = Integer.parseInt(endpoint.getParameters().get("id"));
                String commentText = endpoint.getParameters().get("text");
                
                return this.tracker.addComment(issueId, commentText);
            case "MyIssues": 
                return this.tracker.getMyIssues();
            case "MyComments": return this.tracker.getMyComments();
            case "Search":
                tags = endpoint.getParameters().get("tags").split("|");
                
                return this.tracker.searchForIssues(tags);
            default:
                throw new IllegalStateException(String.format("Invalid action: %s", endpoint.getActionName()));
        }
    }
}
