package composite;

import java.util.ArrayList;
import java.util.List;

public class CompositeElement extends DrawingElement {

	private List<DrawingElement> elements;
	
	public CompositeElement(String name) {
		
		super(name);
		this.elements = new ArrayList<DrawingElement>();
	}

	@Override
	public void add(DrawingElement element) {
		
		this.elements.add(element);
	}

	@Override
	public void remove(DrawingElement element) {

		this.elements.remove(element);
	}

	@Override
	public void display(int indent) {
		
		super.display(indent);
		 
		// Display each child element on this node
	    for (DrawingElement element : this.elements) {
	    	
	    	element.display(indent + 2);
	    }     
	}
}
