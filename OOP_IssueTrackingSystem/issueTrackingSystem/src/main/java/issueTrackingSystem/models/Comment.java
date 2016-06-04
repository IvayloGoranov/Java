package issueTrackingSystem.models;

public class Comment {

	private final int TEXT_DEFAULT_LENGTH = 2;
    
    private String commentText;
    private User author;

    public Comment(User author, String text) {
    	
        this.author = author;
        this.setCommentText(text);
    }

    public User getAuthor() {
		
    	return this.author;
	}

	public String getCommentText() {
		
		return this.commentText;
	}

	private void setCommentText(String value) {

		if (value == null || value.equals("")) {
			
            throw new IllegalArgumentException("The comment text cannot be empty.");
        }

        if (value.length() < this.TEXT_DEFAULT_LENGTH) {
        	
            throw new IllegalArgumentException(String.format("The text must be at least %d symbols long", this.TEXT_DEFAULT_LENGTH));
        }

        this.commentText = value;
	}

	@Override
	public String toString() {

		StringBuilder output = new StringBuilder();
        output.append(String.format("%s\n", this.getCommentText()));
        output.append(String.format("-- %s", this.getAuthor().getUsername()));

        return output.toString();
	}

}
