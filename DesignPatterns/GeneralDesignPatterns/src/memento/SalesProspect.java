package memento;

public class SalesProspect extends Memento {

	public SalesProspect(String name, String phone, double budget) {
		
		super(name, phone, budget);
	}

	public Memento saveStateToMemento(){
	      
		return new Memento(this.getName(), this.getPhone(), this.getBudget());
	}

	   
	public void getStateFromMemento(Memento memento){
	
		this.setName(memento.getName());
		this.setPhone(memento.getPhone());
		this.setBudget(memento.getBudget());
	}
}
