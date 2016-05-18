package factoryMethod;

public abstract class Product {

	private String description;
	
	public Product(String description) {
		
        this.setDescription(description);
    }

    public String getDescription() {
		return description;
	}
    
	public void setDescription(String description) {
		this.description = description;
	}
}
