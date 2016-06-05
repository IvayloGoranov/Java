package customers;

public abstract class Customer {
	
    private String name;
	
	public Customer(String name) {
    	
        this.setName(name);
    }

	public String getName() {
		
		return this.name;
	}

	public void setName(String name) {
		
		this.name = name;
	}
    
    
}
