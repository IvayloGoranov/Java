package issueTrackingSystem.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import issueTrackingSystem.core.IssueTracker;
import issueTrackingSystem.data.IssueTrackerData;
import issueTrackingSystem.interfaces.IIssueTracker;
import issueTrackingSystem.interfaces.IIssueTrackerData;
import issueTrackingSystem.models.Issue;
import issueTrackingSystem.models.IssuePriority;
import issueTrackingSystem.models.User;

public class IssueTrackerTests {

	@Test
	public void testRegisterUser_ExistingUser() {

		IIssueTracker tracker = new FakeIssueTrackerWithNoUser();
        String username = "pesho";
        String password = "parola";
        String confirmPassword = "parola";

        tracker.registerUser(username, password, confirmPassword);
        String result = tracker.registerUser(username, password, confirmPassword);

        assertEquals("RegisterUser() does not return proper outcome in case of duplicate user.", 
        		result, "A user with username pesho already exists");
	}
	
	@Test
	public void testRegisterUser_AlreadyLoggedInUser() {

		IIssueTracker tracker = new FakeIssueTrackerWithLoggedUser();
        String username = "pesho";
        String password = "parola";
        String confirmPassword = "parola";

        tracker.registerUser(username, password, confirmPassword);
        String result = tracker.registerUser(username, password, confirmPassword);

        assertEquals("RegisterUser() does not return proper outcome in case of duplicate user.", 
        		result, "There is already a logged in user");
	}
	
	@Test
	public void testRegisterUser_PasswordsDoNotMatch() {

		 IIssueTracker tracker = new FakeIssueTrackerWithNoUser();
         String username = "pesho";
         String password = "parola";
         String confirmPassword = "parol";

         String result = tracker.registerUser(username, password, confirmPassword);

         assertEquals("RegisterUser() does not return proper outcome in case of pasword mismatch.",
        		 result, "The provided passwords do not match");
	}
	
	@Test
	public void testRegisterUser_ValidUserDetails_ShouldAddUserToDatabase() {

		IIssueTrackerData data = new IssueTrackerData();
        IIssueTracker tracker = new IssueTracker(data);
        String username = "pesho";
        String password = "parola";
        String confirmPassword = "parola";

        String result = tracker.registerUser(username, password, confirmPassword);
        
        User expectedUser = new User(username, password);
        User actualUser = data.getUsersRepository().get(username);

        assertEquals("RegisterUser() does not return proper outcome in case of no logged in user.", 
        		result, "User pesho registered successfully");
        
        assertEquals("RegisterUser() does not add user to database properly.",
        		expectedUser.getUsername(), actualUser.getUsername());
        assertEquals("RegisterUser() does not add user to database properly.", 
        		expectedUser.getPassword(), actualUser.getPassword());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateIssue_InvalidTitleAndDescription_ShouldThrowUp() {

		IIssueTracker tracker = new FakeIssueTrackerWithLoggedUser();
        
        String title = "ab";
        String description = "abc";
        IssuePriority priority = IssuePriority.valueOf("low");
        String[] tags = new String[]{"new", "issue", "another"};

        tracker.createIssue(title, description, priority, tags);
	}
	
	@Test
	public void testCreateIssue_ValidIssueDetails_ShouldAddIssueToDatabase() {

		IIssueTracker tracker = new FakeIssueTrackerWithLoggedUser();

        String title = "title";
        String description = "description";
        IssuePriority priority = IssuePriority.valueOf("low");
        String[] tags = new String[] { "new", "issue", "another" };

        String result = tracker.createIssue(title, description, priority, tags);

        assertEquals("CreateIssue() does not return proper outcome after successful issue creation.", 
        		result, "Issue 1 created successfully");
	}
	
	@Test
	public void testCreateIssue_NoLoggedInUser() {

		IIssueTracker tracker = new FakeIssueTrackerWithNoUser();

        String title = "title";
        String description = "description";
        IssuePriority priority = IssuePriority.valueOf("low");
        String[] tags = new String[] { "new", "issue", "another" };

        String result = tracker.createIssue(title, description, priority, tags);

        assertEquals("CreateIssue() does not return proper outcome in case of no logged in user.", 
        		result, "There is no currently logged in user");
	}
	
	@Test
	public void testGetMyIssues_ShouldReturnCurrentUserIssues() {

		IIssueTrackerData data = new FakeIssueTrackerData();
        IIssueTracker tracker = new FakeIssueTrackerWithLoggedUser(data);

        String actualResult = tracker.getMyIssues();

        String title1 = "title1";
        String description1 = "description1";
        IssuePriority priority1 = IssuePriority.valueOf("low");
        String[] tags1 = new String[] { "new", "issue", "pink" };

        String title2 = "bigtitle";
        String description2 = "bigdescription";
        IssuePriority priority2 = IssuePriority.valueOf("medium");
        String[] tags2 = new String[] { "new", "issue", "yellow" };

        User pesho = new User("pesho", "parola");
        User gosho = new User("gosho", "parola");
        Issue issue1 = new Issue(title1, description1, priority1, Arrays.asList(tags1), pesho);
        Issue issue2 = new Issue(title2, description2, priority2, Arrays.asList(tags2), pesho);
        List<Issue> issues = new ArrayList<Issue>();
        issues.add(issue1);
        issues.add(issue2);
        List<Issue> sortedIssues = issues.stream().sorted((i1, i2) -> i1.getPriority().compareTo(i2.getPriority()))
        		.sorted((i1, i2) -> i1.getTitle().compareTo(i2.getTitle())).collect(Collectors.toList());

        String expectedResult = StringUtils.join(sortedIssues, "");

        assertEquals("GetMyIssues() does not get issues properly.", expectedResult, actualResult);
	}
	
	@Test
	public void testGetMyIssues_NoIssues() {

		IIssueTracker tracker = new FakeIssueTrackerWithLoggedUser();

        String actualResult = tracker.getMyIssues();
        String expectedResult = "No issues";

        assertEquals("GetMyIssues() does not show proper output in case of no issues.", expectedResult, actualResult);
	}
	
	@Test
	public void testGetMyIssues_NoUser() {

		IIssueTracker tracker = new FakeIssueTrackerWithNoUser();

        String actualResult = tracker.getMyIssues();
        String expectedResult = "There is no currently logged in user";

        assertEquals("GetMyIssues() does not show proper output in case of no logged in user.", 
        		expectedResult, actualResult);
	}
	
	@Test
	public void testSearchForIssues_MatchingTags_ShouldReturnIssuesasStringAndNoDuplicates_Test1() {

		IIssueTrackerData data = new FakeIssueTrackerData();
        IIssueTracker tracker = new FakeIssueTrackerWithLoggedUser(data);

        String actualResult1 = tracker.searchForIssues(new String[] {"new"});

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

        User pesho = new User("pesho", "parola");
        User gosho = new User("gosho", "parola");
        Issue issue1 = new Issue(title1, description1, priority1, Arrays.asList(tags1), pesho);
        Issue issue2 = new Issue(title2, description2, priority2, Arrays.asList(tags2), pesho);
        Issue issue3 = new Issue(title3, description3, priority3, Arrays.asList(tags2), gosho);
        ArrayList<Issue> issues = new ArrayList<Issue>();
        issues.add(issue1);
        issues.add(issue2);
        issues.add(issue3);
        List<Issue> sortedIssues = issues.stream().sorted((i1, i2) -> i1.getPriority().compareTo(i2.getPriority()))
        		.sorted((i1, i2) -> i1.getTitle().compareTo(i2.getTitle())).collect(Collectors.toList());

        String expectedResult1 = StringUtils.join(sortedIssues, "");

        assertEquals("SearchForIssues() does not get issues properly.", 
        		expectedResult1, actualResult1);
	}
	
	@Test
	public void testSearchForIssues_MatchingTags_ShouldReturnIssuesasStringAndNoDuplicates_Test2() {

		IIssueTrackerData data = new FakeIssueTrackerData();
        IIssueTracker tracker = new FakeIssueTrackerWithLoggedUser(data);

        String actualResult = tracker.searchForIssues(new String[] { "pink" });

        String title1 = "title1";
        String description1 = "description1";
        IssuePriority priority1 = IssuePriority.valueOf("low");
        String[] tags1 = new String[] { "new", "issue", "pink" };

        User pesho = new User("pesho", "parola");
        Issue issue1 = new Issue(title1, description1, priority1, Arrays.asList(tags1), pesho);
        List<Issue> issues = new ArrayList<Issue>();
        issues.add(issue1);
        List<Issue> sortedIssues = issues.stream().sorted((i1, i2) -> i1.getPriority().compareTo(i2.getPriority()))
        		.sorted((i1, i2) -> i1.getTitle().compareTo(i2.getTitle())).collect(Collectors.toList());

        String expectedResult = StringUtils.join(sortedIssues, "");

        assertEquals("SearchForIssues() does not get issues properly.", 
        		expectedResult, actualResult);
	}
	
	@Test
	public void testSearchForIssues_MatchingTags_ShouldReturnIssuesasStringAndNoDuplicates_Test3() {

		IIssueTrackerData data = new FakeIssueTrackerData();
        IIssueTracker tracker = new FakeIssueTrackerWithLoggedUser(data);

        String actualResult = tracker.searchForIssues(new String[] { "pink", "green" });

        String title1 = "title1";
        String description1 = "description1";
        IssuePriority priority1 = IssuePriority.valueOf("low");
        String[] tags1 = new String[] { "new", "issue", "pink" };

        String title3 = "smalltitle";
        String description3 = "smalldescription";
        IssuePriority priority3 = IssuePriority.valueOf("medium");
        String[] tags3 = new String[] { "green", "issue", "new" };

        User pesho = new User("pesho", "parola");
        User gosho = new User("gosho", "parola");
        Issue issue1 = new Issue(title1, description1, priority1, Arrays.asList(tags1), pesho);
        Issue issue3 = new Issue(title3, description3, priority3, Arrays.asList(tags3), gosho);
        List<Issue> issues = new ArrayList<Issue>();
        issues.add(issue1);
        issues.add(issue3);
        List<Issue> sortedIssues = issues.stream().sorted((i1, i2) -> i1.getPriority().compareTo(i2.getPriority()))
        		.sorted((i1, i2) -> i1.getTitle().compareTo(i2.getTitle())).collect(Collectors.toList());

        String expectedResult = StringUtils.join(sortedIssues, "");

        assertEquals("SearchForIssues() does not get issues properly.", 
        		expectedResult, actualResult);
	}
	
	@Test
	public void TestSearchForIssues_EmptyTagsArray_ShouldReturnMessageAsString() {

		IIssueTrackerData data = new FakeIssueTrackerData();
        IIssueTracker tracker = new FakeIssueTrackerWithLoggedUser(data);

        String actualResult = tracker.searchForIssues(new String[] {});

        String expectedResult = "There are no tags provided";

        assertEquals("SearchForIssues() does not show proper message in case of empty tags array.", 
        		expectedResult, actualResult);
	}
	
	@Test
	public void testSearchForIssues_NoIssuesMatchingTheTags_ShouldReturnMessageAsString() {

		IIssueTrackerData data = new FakeIssueTrackerData();
        IIssueTracker tracker = new FakeIssueTrackerWithLoggedUser(data);

        String actualResult = tracker.searchForIssues(new String[] { "brown", "grey", "black"});

        String expectedResult = "There are no issues matching the tags provided";

        assertEquals("SearchForIssues() does not show proper message in case of mismatching tags array.", 
        		expectedResult, actualResult);
	}
}
