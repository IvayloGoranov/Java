package hotelBookingSystem.views;

import java.util.ArrayList;

import hotelBookingSystem.interfaces.IView;

public abstract class View implements IView {

	private Object model;
	
	public View(Object model) {
		
        this.model = model;
    }

    public Object getModel() {
		
    	return this.model;
	}

	public String display() {
		
        StringBuilder viewResult = new StringBuilder();
        this.buildViewResult(viewResult);
        
        return viewResult.toString().trim();
    }

    protected abstract void buildViewResult(StringBuilder viewResult);
    
    protected <E> ArrayList<E> makeCollection(Iterable<E> iter) {
	    
		ArrayList<E> list = new ArrayList<E>();
	    for (E item : iter) {
	        list.add(item);
	    }
	    
	    return list;
	}
}
