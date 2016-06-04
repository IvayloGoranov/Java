package issueTrackingSystem.tests;

import java.util.Arrays;

import issueTrackingSystem.data.IssueTrackerData;
import issueTrackingSystem.models.Issue;
import issueTrackingSystem.models.IssuePriority;
import issueTrackingSystem.models.User;

public class FakeIssueTrackerData extends IssueTrackerData {

	public FakeIssueTrackerData() {
		
	    super();
		this.fillIssueRepositoryWithFakeData();
	}

	private void fillIssueRepositoryWithFakeData() {
		
	    User pesho = new User("pesho", "parola");
	    User gosho = new User("gosho", "parola");
	
	    String title1 = "title1";
	    String description1 = "description1";
	    IssuePriority priority1 = IssuePriority.valueOf("low");
	    String[] tags1 = new String[] { "new", "issue", "pink" };
	
	    String title2 = "bigtitle";
	    String description2 = "bigdescription";
	    IssuePriority priority2 = IssuePriority.valueOf("medium");
	    String[] tags2 = new String[] { "new", "issue", "yellow" };
	
	    String title3 = "smalltitle";
	    String description3 = "smalldescription";
	    IssuePriority priority3 = IssuePriority.valueOf("medium");
	    String[] tags3 = new String[] { "green", "issue", "new" };
	
	    Issue issue1 = new Issue(title1, description1, priority1, Arrays.asList(tags1), pesho);
	    Issue issue2 = new Issue(title2, description2, priority2, Arrays.asList(tags2), pesho);
	    Issue issue3 = new Issue(title3, description3, priority3, Arrays.asList(tags3), gosho);
	    super.addIssue(issue1);
	    super.addIssue(issue2);
	    super.addIssue(issue3);
	}
}
