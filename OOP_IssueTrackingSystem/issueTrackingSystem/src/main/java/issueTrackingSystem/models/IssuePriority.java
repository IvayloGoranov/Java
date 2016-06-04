package issueTrackingSystem.models;

public enum IssuePriority {

	SHOWSTOPPER(4), 
	HIGH(3), 
	MEDIUM(2), 
	LOW(1);
	
	private int numVal;

	IssuePriority(int numVal) {
        
		this.numVal = numVal;
    }

    public int getNumVal() {
        
    	return this.numVal;
    }
}
