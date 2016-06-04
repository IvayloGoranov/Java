package issueTrackingSystem.interfaces;

import issueTrackingSystem.models.IssuePriority;

public interface IIssueTracker {

	String registerUser(String username, String password, String confirmPassword);
    
    String loginUser(String username, String password);
    
    String logoutUser();

    String createIssue(String title, String description, IssuePriority priority, String[] tags);

    String removeIssue(int issueId);

    String addComment(int issueId, String text);

    String getMyIssues();

    String getMyComments();

    String searchForIssues(String[] tags);
}
