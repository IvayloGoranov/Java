package memento;

public class Memento {

	private String name;
	private String phone;
	private double budget;
	
	public Memento(String name, String phone, double budget) {

		this.setName(name);
		this.setPhone(phone);
		this.setBudget(budget);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}
}
