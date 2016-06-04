package issueTrackingSystem.tests;

import issueTrackingSystem.core.IssueTracker;
import issueTrackingSystem.interfaces.IIssueTrackerData;

public class FakeIssueTrackerWithNoUser extends IssueTracker {

	public FakeIssueTrackerWithNoUser(){
		
		super();
	}

	public FakeIssueTrackerWithNoUser(IIssueTrackerData data){
		
		super(data);
	}
}
