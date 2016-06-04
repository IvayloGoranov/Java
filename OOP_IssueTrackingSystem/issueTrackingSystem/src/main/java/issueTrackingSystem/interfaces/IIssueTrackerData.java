package issueTrackingSystem.interfaces;

import java.util.ArrayList;
import java.util.Map;

import issueTrackingSystem.models.Comment;
import issueTrackingSystem.models.Issue;
import issueTrackingSystem.models.User;

public interface IIssueTrackerData {

	Map<String, User> getUsersRepository();

    Map<Integer, Issue> getIssuesRepository();
    
    Map<String, ArrayList<Comment>> getCommentsRepository();

    int addIssue(Issue issue);

    boolean removeIssue(int id);
}
