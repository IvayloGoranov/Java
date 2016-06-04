package issueTrackingSystem.models;

import issueTrackingSystem.utilities.HashUtilities;

public class User {

	private String username;
    private String password;
    
    public User(String username, String password) {
    	
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
		
    	return this.username;
	}
	
    private void setUsername(String value) {
		
    	 if (value == null || value.equals("")) {
    		 
             throw new IllegalArgumentException("The username cannot be empty.");
         }

         this.username = value;
	}
	
    public String getPassword() {
		
    	return this.password;
	}
    
	private void setPassword(String value) {

		if (value == null || value.equals("")) {
			
            throw new IllegalArgumentException("The password cannot be empty.");
        }

        this.password = HashUtilities.hashPassword(value);
	}
}
