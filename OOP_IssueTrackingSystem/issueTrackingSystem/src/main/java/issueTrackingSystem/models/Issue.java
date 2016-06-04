package issueTrackingSystem.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class Issue {

	private final int TITLE_DEFAULT_LENGTH = 3;
    private final int DESCRIPTION_DEFAULT_LENGTH = 5;
    
    private String title;
    private String description;
    private int id;
    private int myProperty;
    private IssuePriority priority;
    private Collection<String> tags;
    private List<Comment> comments;
    private User author;

    public Issue(String title, String description, IssuePriority priority, Collection<String> tags, User author) {
    	
        this.setTitle(title);
        this.setDescription(description);
        this.priority = priority;
        this.tags = new HashSet<String>(tags);
        this.author = author;
        this.comments = new ArrayList<Comment>();
    }

    public int getId() {
		
    	return this.id;
	}

	public void setId(int id) {
		
		this.id = id;
	}

    public int getMyProperty() {
		
    	return this.myProperty;
	}

	public void setMyProperty(int myProperty) {
		
		this.myProperty = myProperty;
	}

	public String getTitle() {
		
		return this.title;
	}

	private void setTitle(String value) {
		
		if (value == null || value.equals("")) {
			
            throw new IllegalArgumentException("The title cannot be empty.");
        }

        if (value.length() < this.TITLE_DEFAULT_LENGTH) {
        	
            throw new IllegalArgumentException(String.format("The title must be at least %d symbols long", 
                this.TITLE_DEFAULT_LENGTH));
        }

        this.title = value;
	}

	public String getDescription() {
		
		return this.description;
	}

	private void setDescription(String value) {
		
		if (value == null || value.equals("")) {
			
            throw new IllegalArgumentException("The description cannot be empty.");
        }

        if (value.length() < this.DESCRIPTION_DEFAULT_LENGTH) {
        	
            throw new IllegalArgumentException(String.format("The description must be at least %d symbols long", 
                this.DESCRIPTION_DEFAULT_LENGTH));
        }
        
        this.description = value;
	}

    public IssuePriority getPriority() {
		
	   return this.priority;
	}

    public Collection<String> getTags() {
		
    	return this.tags;
	}

    public List<Comment> getComments() {
		
    	return this.comments;
	}

    public User getAuthor() {
		
    	return this.author;
	}

    @SuppressWarnings("unchecked")
	@Override
	public String toString() {

    	StringBuilder output = new StringBuilder();
        output.append(String.format("%s\n", this.getTitle()));
        output.append(String.format("Priority: %s\n", this.getPriorityAsString()));
        output.append(String.format("%s{1}", this.getDescription()));
        if (this.getTags().size() > 0) {
        	
            output.append(String.format("Tags: %s", 
            		StringUtils.join(this.getTags().stream().sorted().collect(Collectors.toList()))));
        }
        
        if (this.getComments().size() > 0) {
        	
            output.append("\n");
            output.append("Comments:\n");
            for (int i = 0; i < this.getComments().size(); i++ ) {
            	
                if (i == this.getComments().size() - 1) {
                	
                    output.append(String.format("%s", this.getComments().get(i)));
                    break;
                }
                
                output.append(String.format("%s{1}", this.getComments().get(i)));
            }
        }

        return output.toString();
	}

	private String getPriorityAsString() {
		
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.getPriority().getNumVal(); i++) {
			
        	output.append('*');
		}
		
        return output.toString();
    }
}
