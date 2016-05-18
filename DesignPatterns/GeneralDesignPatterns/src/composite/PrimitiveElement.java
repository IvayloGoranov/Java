package composite;

public class PrimitiveElement extends DrawingElement {

	public PrimitiveElement(String name) {
		
		super(name);
	}

	@Override
	public void add(DrawingElement element) {
		
		System.out.println("Cannot add to a PrimitiveElement");
	}

	@Override
	public void remove(DrawingElement element) {
		
		System.out.println("Cannot remove from a PrimitiveElement");
	}
}
