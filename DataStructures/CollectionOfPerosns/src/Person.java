
public class Person implements Comparable<Person> {

	private String email;

    private String name;
    
    private int age;
    
    private String town;

	public Person(String email, String name, int age, String town) {

		this.setEmail(email);
		this.setName(name);
		this.setAge(age);
		this.setTown(town);
	}

	public String getEmail() {
		
		return this.email;
	}

	public void setEmail(String email) {
		
		this.email = email;
	}

	public String getName() {
		
		return this.name;
	}

	public void setName(String name) {
		
		this.name = name;
	}

	public int getAge() {
		
		return this.age;
	}

	public void setAge(int age) {
		
		this.age = age;
	}

	public String getTown() {
		
		return this.town;
	}

	public void setTown(String town) {
		
		this.town = town;
	}

	@Override
	public int compareTo(Person otherPerson) {

		if (otherPerson == null) {
			
            return -1;   
        }

        return this.getEmail().compareTo(otherPerson.getEmail());
	}

	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			
			return true;
		}
		
		if (obj == null) {
			
			return false;
		}
		
		if (this.getClass() != obj.getClass()) {
			
			return false;
		}
		
		Person other = (Person) obj;
		if (this.email == null) {
			
			if (other.email != null) {
				
				return false;
			}
		} else if (!this.email.equals(other.email)) {
			
			return false;
		}
		
		return true;
	}
    
    
}
