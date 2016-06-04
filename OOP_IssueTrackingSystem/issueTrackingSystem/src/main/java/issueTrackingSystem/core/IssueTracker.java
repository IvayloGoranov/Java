package issueTrackingSystem.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import issueTrackingSystem.data.IssueTrackerData;
import issueTrackingSystem.interfaces.IIssueTracker;
import issueTrackingSystem.interfaces.IIssueTrackerData;
import issueTrackingSystem.models.Comment;
import issueTrackingSystem.models.Issue;
import issueTrackingSystem.models.IssuePriority;
import issueTrackingSystem.models.User;
import issueTrackingSystem.utilities.HashUtilities;

public class IssueTracker implements IIssueTracker {

	private IIssueTrackerData data;
	private User currentUser;
	
	public IssueTracker(IIssueTrackerData data) {
		
        this.data = data;
    }
    
    public IssueTracker() {
    	
    	this(new IssueTrackerData());
    }

    public User getCurrentUser() {
		
    	return this.currentUser;
	}

	protected void setCurrentUser(User currentUser) {
		
		this.currentUser = currentUser;
	}

	public IIssueTrackerData getData() {
		
		return this.data;
	}

	public String registerUser(String username, String password, String confirmPassword) {
		
        String viewResult = "";
        
        if (this.getCurrentUser() != null) {
        	
            viewResult = "There is already a logged in user";
            
            return viewResult;
        }

        if (password != confirmPassword) {
        	
            viewResult = "The provided passwords do not match";
            
            return viewResult;
        }

        if (this.getData().getUsersRepository().containsKey(username)) {
        	
            viewResult = String.format("A user with username %s already exists", username);
            
            return viewResult;
        }

        User user = new User(username, password);
        this.getData().getUsersRepository().put(username, user);

        viewResult = String.format("User %s registered successfully", username);
        
        return viewResult;
    }

    public String loginUser(String username, String password) {
    	
        String viewResult = "";

        if (this.getCurrentUser() != null) {
        	
            viewResult = "There is already a logged in user";
            
            return viewResult;
        }

        if (this.getData().getUsersRepository().containsKey(username)) {
        	
            viewResult = String.format("A user with username %s already exists", username);
            
            return viewResult;
        }
        
        User user = this.getData().getUsersRepository().get(username);
        String hashedPassword = HashUtilities.hashPassword(password);

        if (user.getPassword() != hashedPassword) {
        	
            viewResult = String.format("The password is invalid for user %s", username);
            
            return viewResult;
        }
        
        this.setCurrentUser(user);
        viewResult = String.format("User %s logged in successfully", username);
        
        return viewResult;
    }

    public String logoutUser() {
    	
        String viewResult = "";
        if (this.getCurrentUser() != null) {
        	
            viewResult = "There is already a logged in user";
            
            return viewResult;
        }

        String username = this.getCurrentUser().getUsername();
        
        this.setCurrentUser(null);

        viewResult = String.format("User %s logged out successfully", username);
        
        return viewResult;
    }

    public String createIssue(String title, String description, IssuePriority priority, String[] tags) {
    	
        String viewResult = "";
        if (this.getCurrentUser() == null)
        {
            viewResult = "There is no currently logged in user";
            
            return viewResult;
        }

        Issue issue = new Issue(title, description, priority, Arrays.asList(tags), this.getCurrentUser());
        this.getData().addIssue(issue);

        viewResult = String.format("Issue %s created successfully", issue.getId());
        
        return viewResult;
    }

    public String removeIssue(int issueId) {
    	
        String viewResult = "";
        if (this.getCurrentUser() == null) {
        	
            viewResult = "There is no currently logged in user";
            
            return viewResult;
        }

        if (!this.getData().getIssuesRepository().containsKey(issueId)) {
            
        	viewResult = String.format("There is no issue with ID %d", issueId);
            return viewResult;
        }
        
        Issue issue = this.getData().getIssuesRepository().get(issueId);
        if (issue.getAuthor().getUsername() != this.getCurrentUser().getUsername()) {
        	
            viewResult = String.format("The issue with ID %d does not belong to user %s", issueId, this.getCurrentUser().getUsername());
            return viewResult;
        }

        this.getData().removeIssue(issueId);

        viewResult = String.format("Issue %d removed", issueId);
        
        return viewResult;
    }

    public String addComment(int issueId, String commentText) {
    	
        String viewResult = "";
        if (this.getCurrentUser() == null) {
        	
            viewResult = String.format("There is no currently logged in user");
            
            return viewResult;
        }

        if (!this.getData().getIssuesRepository().containsKey(issueId)) {
        	
            viewResult = String.format("There is no issue with ID %d", issueId);
            
            return viewResult;
        }

        Issue issue = this.getData().getIssuesRepository().get(issueId);
        Comment comment = new Comment(this.getCurrentUser(), commentText);
        issue.getComments().add(comment);

        if (!this.getData().getCommentsRepository().containsKey(this.getCurrentUser().getUsername())) {
        	
            this.getData().getCommentsRepository().put(this.getCurrentUser().getUsername(), new ArrayList<Comment>());
        } else {
        	
        	this.getData().getCommentsRepository().get(this.getCurrentUser().getUsername()).add(comment);
        }

        viewResult = String.format("Comment added successfully to issue %d", issue.getId());
        
        return viewResult;
    }

    public String getMyIssues() {
    	
        String viewResult = "";
        if (this.getCurrentUser() == null) {
        	
            viewResult = "There is no currently logged in user";
            
            return viewResult;
        }

        List<Issue> currentUserIssues = this.getData().getIssuesRepository().values().stream().
            filter(issue -> issue.getAuthor().getUsername().equals(this.getCurrentUser().getUsername())).
            		collect(Collectors.toList());

        if (currentUserIssues.size() == 0) {
        	
            viewResult = "No issues";
            
            return viewResult;
        }
        
        List<Issue> sortedIssues = currentUserIssues.stream().
        		sorted((issue1, issue2) -> issue1.getPriority().compareTo(issue2.getPriority())).
        		sorted((issue1, issue2) -> issue1.getTitle().compareTo(issue2.getTitle())).collect(Collectors.toList());

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < sortedIssues.size(); i++) {
        	
            if (i == sortedIssues.size() - 1) {
            	
                output.append(sortedIssues.get(i));
                break;
            }

            output.append(sortedIssues.get(i) + "\n");
        }
        
        viewResult = output.toString();
        
        return viewResult;
    }

    public String getMyComments() {
    	
        String viewResult = "";
        if (this.getCurrentUser() == null) {
        	
            viewResult = "There is no currently logged in user";
            
            return viewResult;
        }

        if (!this.getData().getCommentsRepository().containsKey(this.getCurrentUser().getUsername())) {
        	
            viewResult = "No comments";
            
            return viewResult;
        }
        
        List<Comment> currentUserComments = this.getData().getCommentsRepository().get(this.getCurrentUser().getUsername());

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < currentUserComments.size(); i++) {
        	
            if (i == currentUserComments.size() - 1) {
            	
                output.append(currentUserComments.get(i));
                break;
            }

            output.append(currentUserComments.get(i) + "\n");
        }
        
        viewResult = output.toString();

        return viewResult;
    }

    public String searchForIssues(String[] tags) {
    	
        String viewResult = "";
        if (tags.length == 0) {
        	
            viewResult = "There are no tags provided";
            
            return viewResult;
        }

        Collection<Issue> allIssues = this.getData().getIssuesRepository().values();
        List<Issue> matchingIssues = new ArrayList<Issue>();
        for (Issue issue : allIssues) {
        	
            for (String tag : tags) {
            	
                if (issue.getTags().contains(tag)) {
                	
                    matchingIssues.add(issue);
                    break;
                }
            }
        }

        if (matchingIssues.size() == 0) {
        	
            viewResult = "There are no issues matching the tags provided";
            
            return viewResult;
        }

        List<Issue> sortedIssues = matchingIssues.stream().
        		sorted((issue1, issue2) -> issue1.getPriority().compareTo(issue2.getPriority())).
        		sorted((issue1, issue2) -> issue1.getTitle().compareTo(issue2.getTitle())).collect(Collectors.toList());

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < sortedIssues.size(); i++) {
        	
            if (i == sortedIssues.size() - 1) {
            	
                output.append(sortedIssues.get(i));
                break;
            }

            output.append(sortedIssues.get(i) + "\n");
        }

        viewResult = output.toString();
        
        return viewResult;
    }
}
