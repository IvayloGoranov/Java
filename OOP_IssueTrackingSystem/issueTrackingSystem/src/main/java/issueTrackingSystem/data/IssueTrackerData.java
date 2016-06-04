package issueTrackingSystem.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import issueTrackingSystem.interfaces.IIssueTrackerData;
import issueTrackingSystem.models.Comment;
import issueTrackingSystem.models.Issue;
import issueTrackingSystem.models.User;

public class IssueTrackerData implements IIssueTrackerData {

	private int nextAddIssueId = 1;
    
	private Map<String, User> usersRepository;
	private Map<Integer, Issue> issuesRepository;
	private Map<String, ArrayList<Comment>> commentsRepository;
	
    public IssueTrackerData() {
    
        this.usersRepository = new LinkedHashMap<String, User>();
        this.issuesRepository = new LinkedHashMap<Integer, Issue>();
        this.commentsRepository = new LinkedHashMap<String, ArrayList<Comment>>();
    }

    public Map<String, User> getUsersRepository() {
		
    	return this.usersRepository;
	}



	public Map<Integer, Issue> getIssuesRepository() {
		
		return this.issuesRepository;
	}

	public Map<String, ArrayList<Comment>> getCommentsRepository() {
		
		return this.commentsRepository;
	}

	public int addIssue(Issue issue) {
		
        issue.setId(this.nextAddIssueId);
        this.issuesRepository.put(Integer.valueOf(this.nextAddIssueId), issue);
        this.nextAddIssueId++;

        return this.nextAddIssueId;
    }

    public boolean removeIssue(int id) {
    	
        Issue result = this.issuesRepository.remove(Integer.valueOf(id));
        if (result == null) {
			
        	return false;
		}
        
        return true;
    }
}
