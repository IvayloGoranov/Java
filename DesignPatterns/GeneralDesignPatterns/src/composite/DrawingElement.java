package composite;

public abstract class DrawingElement {

	private String name;
	 
    public DrawingElement(String name) {
    	
      this.setName(name);
    }
    
    public String getName() {
		
    	return this.name;
	}

	public void setName(String name) {
		
		this.name = name;
	}

	public void display(int indent) {
    	
    	StringBuilder output = new StringBuilder();
		for (int i = 0; i < indent; i++) {
		
			output.append("-");
		}
		
		output.append(" " + this.getName());
		
		System.out.println(output);
    }
	
	public abstract void add(DrawingElement element);
	
    public abstract void remove(DrawingElement element);
}
