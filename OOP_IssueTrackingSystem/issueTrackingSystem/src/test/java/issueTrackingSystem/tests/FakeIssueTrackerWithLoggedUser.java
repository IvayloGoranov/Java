package issueTrackingSystem.tests;

import issueTrackingSystem.core.IssueTracker;
import issueTrackingSystem.interfaces.IIssueTrackerData;
import issueTrackingSystem.models.User;

public class FakeIssueTrackerWithLoggedUser extends IssueTracker {

	private User fakeUser = new User("pesho", "parola");

    public FakeIssueTrackerWithLoggedUser() {
    	
    	super();
        this.setCurrentUser(fakeUser);
    }
    
    public FakeIssueTrackerWithLoggedUser(IIssueTrackerData data) {
    	
    	super(data);
	    this.setCurrentUser(fakeUser);
	}
}
